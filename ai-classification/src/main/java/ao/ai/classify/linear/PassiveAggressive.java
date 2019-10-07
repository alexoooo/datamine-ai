package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;
import ao.util.math.Calc;

import java.io.*;

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
    private static final double defaultPa3c = 1.0 / (2.0 * 0.001);


    public static void persist(
            PassiveAggressive instance, ObjectOutputStream out) throws IOException
    {
        if (instance.PA2_C != defaultPa3c) {
            throw new UnsupportedOperationException();
        }

        if (instance.weights == null) {
            out.writeInt(-1);
        }
        else {
            out.writeInt(instance.weights.length);

            for (double weight : instance.weights) {
                out.writeDouble(weight);
            }
        }
    }


    public static PassiveAggressive restore(ObjectInputStream in) throws IOException
    {
        int length = in.readInt();

        double[] weights;
        if (length == -1) {
            weights = null;
        }
        else {
            weights = new double[length];

            for (int i = 0; i < length; i++) {
                weights[i] = in.readDouble();
            }
        }

        return new PassiveAggressive(defaultPa3c, weights);
    }


    //------------------------------------------------------------------------
    private final double PA2_C;


    //------------------------------------------------------------------------
    public PassiveAggressive()
    {
        PA2_C = defaultPa3c;
    }

    public PassiveAggressive(double c)
    {
        PA2_C = 1.0 / (2.0 * c);
    }

    protected PassiveAggressive(double pa2c, double[] weights)
    {
        super(weights);
        PA2_C = pa2c;
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
