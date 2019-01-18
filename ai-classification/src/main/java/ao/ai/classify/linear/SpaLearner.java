package ao.ai.classify.linear;

import ao.ai.ml.model.algo.OnlineMultiScoreLearner;
import ao.ai.ml.model.common.IndexedReal;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.output.MultiScoreClass;
import ao.ai.ml.model.theory.MultiScoreClassifier;
import ao.util.math.Calc;
import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * User: AO
 * Date: 10/23/10
 * Time: 9:21 PM
 *
 * SupportClassPassiveAggressive
 */
public class SpaLearner
        implements OnlineMultiScoreLearner<RealList>
{
    //------------------------------------------------------------------------
    private double[][]                     weights;
    private MultiScoreClassifier<RealList> classifier =
            new MultiScoreClassifier.ConstantDummy<RealList>();


    //------------------------------------------------------------------------
    @Override
    public MultiScoreClass classify(RealList input)
    {
        Preconditions.checkNotNull(input);
        return classifier.classify( input );
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, MultiClass output)
    {
        checkParameters(input, output);
        if (weights.length < 2) {
            return;
        }

        MultiScoreClass prediction = classifier.classify(input);

        double bestScore = prediction.scoreOfClass( prediction.best() );

//        double     [] loss        = new double     [ weights.length     ];
        IndexedReal[] classLosses = new IndexedReal[ weights.length - 1 ];

        for (int k = 0, v = 0; k < weights.length; k++)
        {
            if (k == output.best())
            {
                // ???
//                loss[ k ] = Double.NEGATIVE_INFINITY;
            }
            else
            {
                double kOverScore =
                        bestScore - prediction.scoreOfClass( k );
                double kLoss = Math.max(0, 1.0 - kOverScore);
//                loss[ k ] = kLoss;

                classLosses[ v++ ] = new IndexedReal(k, kLoss);
            }
        }

        Arrays.sort(classLosses, IndexedReal.DESCENDING_REAL_ORDER);
        if (classLosses[ 0 ].real() == 0) {
            return;
        }

        int    nSupportClasses = 0;
        double supportLossSum  = 0;

        for (int j = 0; j < classLosses.length; j++)
        {
            double classLoss = classLosses[ j ].real();
            double supportLossCeiling =
                    (j + 1) * classLoss;

            if (supportLossSum >= supportLossCeiling)
            {
                break;
            }

            nSupportClasses++;
            supportLossSum += classLoss;
        }

        double inverseSquaredInputNorm = 1.0 / Calc.square(
                LinearClassificationUtilities.norm( input ));

        double supportLossFactor =
                (1.0 / (nSupportClasses + 1)) * supportLossSum;

//        double nonTargetUpdateSum = 0;
        double[] targetClassWeights = weights[ output.best() ];
        for (IndexedReal classLoss : classLosses)
        {
            double stepSize =
                    inverseSquaredInputNorm *
                    (classLoss.real() - supportLossFactor);

            double[] subWeights = weights[ classLoss.index() ];
            for (int i = 0; i < subWeights.length; i++)
            {
                double update = stepSize * input.get( i );
                subWeights[ i ] -= update;
                targetClassWeights[ i ] += update;
            }
        }

        classifier = new LinearMultiClassifier( weights );
    }


    //------------------------------------------------------------------------
    private void checkParameters(RealList input, MultiClass output)
    {
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(output);

        if (weights == null)
        {
            weights = new double[ output.best() + 1 ][ input.size() ];
        }
        else
        {
            Preconditions.checkArgument(
                    input.size() == weights[ 0 ].length);

            if (output.best() >= weights.length)
            {
                int existingSize = weights.length;

                weights = Arrays.copyOf(weights, output.best() + 1);

                for (int i = existingSize; i < weights.length; i++)
                {
                    weights[ i ] = new double[ input.size() ];
                }
            }
        }
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.deepToString( weights );
    }
}
