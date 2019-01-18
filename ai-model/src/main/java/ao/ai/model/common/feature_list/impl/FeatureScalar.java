package ao.ai.model.common.feature_list.impl;

import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.value.NumericalValue;
import ao.ai.model.common.value.impl.NumericalValueImpl;

import java.util.Arrays;
import java.util.List;

/**
 * User: aostrovsky
 * Date: 31-Jan-2010
 * Time: 10:12:02 AM
 */
public class FeatureScalar
        implements SingleNumericalFeature
{
    //--------------------------------------------------------------------
    private final FeatureType type;
    private final double      value;


    //--------------------------------------------------------------------
    public FeatureScalar(
            double      featureValue,
            FeatureType featureType)
    {
        type  = featureType;
        value = featureValue;
    }


    //--------------------------------------------------------------------
    @Override
    public double doubleValue()
    {
        return value;
    }

    @Override
    public FeatureType type()
    {
        return type;
    }

    @Override
    public NumericalValue value()
    {
        return new NumericalValueImpl(value);
    }

    @Override
    public double[] doubleValues()
    {
        return new double[]{ value };
    }

    @Override
    public double doubleValue(int index)
    {
        assert index == 0;
        return value;
    }

    @Override
    public List<FeatureType> types()
    {
        return Arrays.asList( type );
    }

    @Override
    public FeatureType type(int index)
    {
        assert index == 0;
        return type;
    }

    @Override
    public List<NumericalValue> values()
    {
        return Arrays.asList( value() );
    }

    @Override
    public NumericalValue value(int index)
    {
        assert index == 0;
        return value();
    }

    @Override
    public int size()
    {
        return 1;
    }

//    @Override
//    public Map<FeatureType, NumericalValue> map()
//    {
//        return new HashMap<FeatureType, NumericalValue>() {{
//            put(type, value());
//        }};
//    }
}
