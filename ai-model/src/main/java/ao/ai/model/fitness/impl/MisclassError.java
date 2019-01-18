package ao.ai.model.fitness.impl;

import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;
import ao.ai.model.fitness.ext.BinaryFitnessMeasure;
import ao.util.misc.Factories;
import ao.util.misc.Factory;

/**
 * User: aostrovsky
 * Date: 15-Feb-2010
 * Time: 3:33:15 PM
 */
public class MisclassError
        implements BinaryFitnessMeasure
{
    //-------------------------------------------------------------------------
    private static final Factory<MisclassError> factory =
            Factories.newDefault( MisclassError.class );

    public static Factory<MisclassError> factory()
    {
        return factory;
    }


    //--------------------------------------------------------------------
    private int truePredictions;
    private int totalPredictions;


    //--------------------------------------------------------------------
    @Override
    public void add(
            SingleBinaryFeature predicted,
            SingleBinaryFeature actual)
    {
        if (predicted.binaryCategory() ==
                actual.binaryCategory())
        {
            truePredictions++;
        }

        totalPredictions++;
    }


    //--------------------------------------------------------------------
    @Override
    public double loss()
    {
        return ((double) truePredictions)
                / totalPredictions;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Math.round(loss() * 1000) / 10.0 + "%";
    }
}
