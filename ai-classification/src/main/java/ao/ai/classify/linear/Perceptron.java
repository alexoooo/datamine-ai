package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 3:42:00 PM
 */
public class Perceptron
        extends LinearBinaryOnlineLearner
{
    //------------------------------------------------------------------------
    private static final double mistakeThreshold = 0.001;


    //------------------------------------------------------------------------
    @Override
    protected double[] updateDelta(
            double[] previousWeights,
            RealList input,
            double   positiveScore,
            double   observedLabel)
    {
        if (observedLabel * positiveScore >= 0)
        {
            // correctly predicted
            return null;
        }

        return increaseMarginDelta(
                previousWeights, input, observedLabel, 1.0);
    }
}
