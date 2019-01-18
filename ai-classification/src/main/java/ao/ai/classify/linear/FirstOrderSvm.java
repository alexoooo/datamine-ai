package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;
import ao.util.math.Calc;

import static ao.ai.classify.linear.LinearClassificationUtilities.hingeLoss;
import static ao.ai.classify.linear.LinearClassificationUtilities.norm;

/**
 * User: AO
 * Date: Oct 11, 2010
 * Time: 8:58:58 PM
 */
public class FirstOrderSvm
        extends LinearBinaryOnlineLearner
{
    //------------------------------------------------------------------------
//    private static final double mistakeThreshold = 0.001;


    //------------------------------------------------------------------------
    @Override
    protected double[] updateDelta(
            double[] previousWeights,
            RealList input,
            double   positiveScore,
            double   observedLabel)
    {
        double margin = observedLabel * positiveScore;
        if (margin >= 1)
        {
            // sufficient margin
            return null;
        }

        double eta = hingeLoss( margin ) /
                        Calc.square(norm( input ));

        return increaseMarginDelta(
                previousWeights, input, observedLabel, eta);
    }
}