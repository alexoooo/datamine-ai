package ao.ai.model.ml.supervised.example.impl;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.ml.supervised.example.ext.RegressionExample;

/**
 * User: aostrovsky
 * Date: 31-Jan-2010
 * Time: 10:08:08 AM
 */
public class RegressionExampleImpl
        implements RegressionExample
{
    //--------------------------------------------------------------------
    private final NumericalFeatureList   in;
    private final SingleNumericalFeature out;


    //--------------------------------------------------------------------
    public RegressionExampleImpl(
            NumericalFeatureList input,
            SingleNumericalFeature output)
    {
        in  = input;
        out = output;
    }

    
    //--------------------------------------------------------------------
    @Override
    public NumericalFeatureList input()
    {
        return in;
    }

    @Override
    public SingleNumericalFeature output()
    {
        return out;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return in + " -> " + out;
    }
}
