package ao.ai.model.ml.supervised.example.impl;

import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;
import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.ml.supervised.example.ext.BinaryClassificationExample;

/**
 * User: aostrovsky
 * Date: 12-Feb-2010
 * Time: 11:20:17 PM
 */
public class BinaryClassificationExampleImpl
        implements BinaryClassificationExample
{
    //--------------------------------------------------------------------
    private final NumericalFeatureList in;
    private final SingleBinaryFeature  out;


    //--------------------------------------------------------------------
    public BinaryClassificationExampleImpl(
            NumericalFeatureList input,
            SingleBinaryFeature output)
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
    public SingleBinaryFeature output()
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