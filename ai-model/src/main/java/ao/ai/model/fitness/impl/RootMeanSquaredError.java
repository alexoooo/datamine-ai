package ao.ai.model.fitness.impl;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.NumericalValue;
import ao.ai.model.fitness.ext.NumericFitnessMeasure;
import ao.util.misc.Factories;
import ao.util.misc.Factory;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 9:17:07 AM
 */
public class RootMeanSquaredError
        implements NumericFitnessMeasure
{
    //-------------------------------------------------------------------------
    private static final Factory<RootMeanSquaredError> factory =
            Factories.newDefault( RootMeanSquaredError.class );

    public static Factory<RootMeanSquaredError> factory()
    {
        return factory;
    }


    //-------------------------------------------------------------------------
    private double sumSquaredError;
    private int    errorCount;


    //-------------------------------------------------------------------------
    @Override
    public void add(
            FeatureList<NumericalValue> predicted,
            FeatureList<NumericalValue> actual)
    {
        assert predicted.size() == actual.size();

        double squaredComponentSum = 0;
        for (int i = 0; i < predicted.size(); i++)
        {
            double componentError =
                    predicted.value(i).getNum() -
                    actual   .value(i).getNum();

            squaredComponentSum +=
                    componentError * componentError;
        }

        double error = Math.sqrt(squaredComponentSum);
        sumSquaredError += error * error;
        errorCount++;
    }


    //-------------------------------------------------------------------------
    @Override
    public double loss()
    {
        return (errorCount == 0)
               ? 0 : Math.sqrt(sumSquaredError / errorCount);
    }

    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "RMSE=" + loss();
    }
}
