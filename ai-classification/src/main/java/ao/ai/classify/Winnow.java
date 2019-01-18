package ao.ai.classify;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.BinaryList;
import ao.ai.ml.model.output.BinaryClass;

import java.util.Arrays;

/**
 * Date: Sep 2, 2010
 * Time: 12:23:46 AM
 *
 * See: http://en.wikipedia.org/wiki/Winnow_(algorithm)
 */
public class Winnow
        implements OnlineBinaryLearner<BinaryList>
{
    //------------------------------------------------------------------------
    public static void main(String[] args)
    {
        OnlineBinaryLearner<BinaryList>
                algo = new Winnow();

        boolean[][] data = new boolean[][]{
                // not (not separable)
//                {false, true},
//                {true, false}

                // or
                {false, false, false},
                {false, true, true},
                {true, false, true},
                {true, true, true}

//                // and
//                {false, false, false},
//                {false, true, false},
//                {true, false, false},
//                {true, true, true}

//                // xor (not separable)
//                {false, false, false},
//                {false, true, true},
//                {true, false, true},
//                {true, true, false}
        };

        for (boolean[] example : data)
        {
            algo.learn(
                    new BinaryList(Arrays.copyOf(
                            example, example.length - 1)),
                    BinaryClass.create(
                            example[ example.length - 1 ] ));
        }

        // not
//        algo.learn(new BinaryList(false), BinaryClass.createScored( true ));
//        algo.learn(new BinaryList(true ), BinaryClass.createScored( false ));

//        BinaryClass negPredition = algo.predict(new BinaryList(false));
//        System.out.println("Predicted (false): " + negPredition);
//
//        BinaryClass posPrediction = algo.predict(new BinaryList(true));
//        System.out.println("Predicted (true): " + posPrediction);

        for (boolean[] example : data)
        {
            BinaryClass prediction = algo.classify(
                    new BinaryList(Arrays.copyOf(
                            example, example.length - 1)));

            System.out.println(
                    Arrays.toString( example ) + " -> " +
                    prediction + " vs " + example[ example.length - 1 ]);
        }
    }


    //------------------------------------------------------------------------
    private static final double thresholdFactor = 0.5;
    private static final double initialWeight   = 1.0;

    private static final double alpha = 2;
    private static final double beta  = 1.0 / alpha;
//    private static final double beta  = 0;


    //------------------------------------------------------------------------
    private double[] weights;


    //------------------------------------------------------------------------
    public Winnow()
    {

    }


    //------------------------------------------------------------------------
    @Override
    public void learn(BinaryList input, BinaryClass output)
    {
        initWeightsIfRequired( input.size() );

        BinaryClass predicted = classify( input );
        if (predicted.isPositive() ==
                output.isPositive())
        {
            // predicted correctly
            return;
        }

        double positiveMultiplier =
                predicted.isPositive()
                ? beta    // false-pos: demote
                : alpha;  // false-neg: promote

        for (int i = 0; i < weights.length; i++)
        {
            if (input.get( i ))
            {
                weights[ i ] *= positiveMultiplier;
            }
        }
    }

    private void initWeightsIfRequired(int size)
    {
        if (weights == null)
        {
            weights = new double[ size ];
            Arrays.fill(weights, initialWeight);
        }
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClass classify(BinaryList input)
    {
        initWeightsIfRequired( input.size() );

        double weightedInput = 0;
        for (int i = 0; i < weights.length; i++)
        {
            weightedInput +=
                    weights[ i ] *
                    indicator(input.get( i ));
        }

        double threshold =
                thresholdFactor * input.size();

        return BinaryClass.create(
                weightedInput > threshold);
    }

    private int indicator(boolean isTrue)
    {
        return isTrue ? 1 : 0;
    }
}
