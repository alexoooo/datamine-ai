package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiScoreClass;
import ao.ai.ml.model.theory.MultiScoreClassifier;

import java.util.Arrays;

/**
 * User: AO
 * Date: 10/24/10
 * Time: 3:20 AM
 */
public class LinearMultiClassifier
        implements MultiScoreClassifier<RealList>
{
    //------------------------------------------------------------------------
    private final double[][] weights;


    //------------------------------------------------------------------------
    public LinearMultiClassifier(double[][] weights)
    {
        double[][] weightsCopy = new double[ weights.length ][];

        for (int i = 0; i < weightsCopy.length; i++)
        {
            weightsCopy[ i ] = Arrays.copyOf(
                    weights[i], weights[i].length);
        }

        this.weights = weightsCopy;
    }


    //------------------------------------------------------------------------
    @Override
    public MultiScoreClass classify(RealList input)
    {
        if (input.size() != weights[ 0 ].length)
        {
            throw new IllegalArgumentException(
                    "Input size " + input.size() +
                    " should be " + weights[ 0 ].length);
        }

        double[] classScores = new double[ weights.length ];
        for (int i = 0; i < weights.length; i++)
        {
            double[] classWeights = weights[ i ];

            double score = 0;
            for (int j = 0; j < input.size(); j++)
            {
                score += classWeights[ j ] * input.get( j );
            }

            classScores[ i ] = score;
        }

        return MultiScoreClass.createScored( classScores );
    }
}
