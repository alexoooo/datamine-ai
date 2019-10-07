package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryScoreClass;
import ao.ai.ml.model.theory.BinaryScoreClassifier;

import java.io.Serializable;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 2:27:26 PM
 */
public class LinearBinaryClassifier
        implements BinaryScoreClassifier<RealList>//, Serializable
{
    //------------------------------------------------------------------------
    private final double[] weights;


    //------------------------------------------------------------------------
    public LinearBinaryClassifier(double[] weights)
    {
        this.weights = weights.clone();
    }


    //------------------------------------------------------------------------
    /**
     * No bias term, it can be simulated by adding an extra dimension
     *  with constant value = 1.
     *
     * @param input x vector
     * @return w transpose * x, s.t. class = sign(score)
     */
    @Override
    public BinaryScoreClass classify(RealList input)
    {
        if (input.size() != weights.length) {
            throw new IllegalArgumentException(
                    "Input size " + input.size() +
                    " should be " + weights.length);
        }

        double score = 0;
        for (int i = 0; i < input.size(); i++)
        {
            score += weights[ i ] * input.get( i );
        }

        return score == 0
               ? BinaryScoreClass.createScored(true)
               : BinaryScoreClass.createScored(score, -score);
    }
}
