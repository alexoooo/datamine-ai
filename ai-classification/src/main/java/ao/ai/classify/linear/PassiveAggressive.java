package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;
import ao.util.math.Calc;

import static ao.ai.classify.linear.LinearClassificationUtilities.hingeLoss;
import static ao.ai.classify.linear.LinearClassificationUtilities.norm;

/**
 * User: AO
 * Date: Oct 16, 2010
 * Time: 12:41:00 PM
 */
public class PassiveAggressive
        extends LinearBinaryOnlineLearner
{
    //------------------------------------------------------------------------
    private final double PA2_C;


    //------------------------------------------------------------------------
    public PassiveAggressive()
    {
        this(0.001);
    }

    public PassiveAggressive(double c)
    {
//        this.C = c;

        this.PA2_C = 1.0 / (2.0 * c);
    }


    //------------------------------------------------------------------------
    @Override
    protected double[] updateDelta(
            double[] previousWeights,
            RealList input,
            double   positiveScore,
            double   observedLabel)
    {
        double margin = observedLabel * positiveScore;
        double loss   = hingeLoss( margin );

        if (loss == 0)
        {
            // no loss taken
            return null;
        }

        double eta = loss /
                (Calc.square(norm( input )) + PA2_C);

        return increaseMarginDelta(
                previousWeights, input, observedLabel, eta);
    }
}
