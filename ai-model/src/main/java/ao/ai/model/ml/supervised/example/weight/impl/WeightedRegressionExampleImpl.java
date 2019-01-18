package ao.ai.model.ml.supervised.example.weight.impl;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.ml.supervised.example.impl.RegressionExampleImpl;
import ao.ai.model.ml.supervised.example.weight.ext.WeightedRegressionExample;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:12:54 AM
 */
public class WeightedRegressionExampleImpl
        extends    RegressionExampleImpl
        implements WeightedRegressionExample
{
    //--------------------------------------------------------------------
    private final double weight;


    //--------------------------------------------------------------------
    public WeightedRegressionExampleImpl(
            double                   exampleWeight,
            NumericalFeatureList input,
            SingleNumericalFeature output)
    {
        super(input, output);

        weight = exampleWeight;
    }


    //--------------------------------------------------------------------
    @Override
    public double weight()
    {
        return weight;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return super.toString() + " (" + weight + ")";
    }
}
