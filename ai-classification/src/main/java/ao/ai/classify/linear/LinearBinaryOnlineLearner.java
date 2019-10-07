package ao.ai.classify.linear;

import ao.ai.ml.model.algo.OnlineBinaryScoreLearner;
import ao.ai.ml.model.common.AiModelUtils;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.output.BinaryScoreClass;
import ao.ai.ml.model.theory.BinaryScoreClassifier;
import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.Arrays;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 3:07:17 PM
 */
public abstract class LinearBinaryOnlineLearner
        implements OnlineBinaryScoreLearner<RealList>//, Serializable
{
    //-------------------------------------------------------------------------
    protected double[]                        weights;
    private BinaryScoreClassifier<RealList> classifier =
            new ConstantDummy<RealList>();
//            new RandomDummy<RealList>();


//    private double[]                        weightSums;
//    private int learnCount = 0;
//    private BinaryScoreClassifier<RealList> meanClassifier =
//            new ConstantDummy<RealList>();


    //-------------------------------------------------------------------------
    protected LinearBinaryOnlineLearner() {}


    protected LinearBinaryOnlineLearner(double[] weights)
    {
        this.weights = weights;

        if (weights != null) {
            classifier = new LinearBinaryClassifier( weights );
        }
    }


    //-------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass output)
    {
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(output);
        if (weights == null) {
            weights    = new double[ input.size() ];
//            weightSums = new double[ input.size() ];
        }

        double positiveScore =
                classifier.classify( input ).positiveScore();

        double observedLabel = AiModelUtils.sgn(output.isPositive());

        double[] updateDelta = updateDelta(
                weights, input, positiveScore, observedLabel);

        if (updateDelta != null)
        {
            Preconditions.checkState(
                    updateDelta.length == weights.length);

            for (int i = 0; i < weights.length; i++)
            {
                weights[ i ] += updateDelta[ i ];
            }

            classifier = new LinearBinaryClassifier( weights );
        }

//        if (! meanClassifier.classify( input ).equals( output ))
//        {
//            learnCount++;
//            double[] meanWeights = new double[ weightSums.length ];
//
//            for (int i = 0; i < weights.length; i++)
//            {
//                weightSums [ i ] += weights    [ i ];
//                meanWeights[ i ] =  weightSums [ i ] / learnCount;
//            }
//
//            System.out.println(
//                    "weights: "     + Arrays.toString( weights     ) + " | " +
//                    "meanWeights: " + Arrays.toString( meanWeights ) + " | " +
//                    "updateCount: " + learnCount);
//
//            meanClassifier = new LinearBinaryClassifier( meanWeights );
//        }
    }

    protected abstract double[] updateDelta(
            double[] previousWeights,
            RealList input,
            double   positiveScore,
            double   observedLabel);


    //-------------------------------------------------------------------------
    @Override
    public BinaryScoreClass classify(RealList input)
    {
        return classifier.classify( input );
//        return meanClassifier.classify( input );
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString( weights );
    }


    //-------------------------------------------------------------------------
    protected static double[] increaseMarginDelta(
            double[] previousWeights,
            RealList input,
            double   observedLabel,
            double   eta)
    {
        double[] deltas = new double[ previousWeights.length ];
        for (int i = 0; i < deltas.length; i++)
        {
            deltas[ i ] = eta * observedLabel * input.get( i );
        }
        return deltas;
    }
}
