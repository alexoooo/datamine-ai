package ao.ai.model.common.feature_list.impl;

import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;
import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.common.value.impl.BinaryValueImpl;

import java.util.Arrays;
import java.util.List;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 8:51:41 AM
 */
public class BinaryScalar
        implements SingleBinaryFeature
{
    //--------------------------------------------------------------------
    private final boolean     value;
    private final FeatureType type;


    //--------------------------------------------------------------------
    public BinaryScalar(
            boolean     binaryValue,
            FeatureType featureType)
    {
        value = binaryValue;
        type  = featureType;
    }


    //--------------------------------------------------------------------
    @Override
    public boolean binaryCategory()
    {
        return value;
    }

    @Override
    public FeatureType type()
    {
        return type;
    }

    @Override
    public BinaryValue value()
    {
        return BinaryValueImpl.valueOf(value);
    }

    @Override
    public boolean[] binaryCategories()
    {
        return new boolean[]{ value };
    }

    @Override
    public boolean binaryCategory(int index)
    {
        assert index == 0;
        return value;
    }

    @Override
    public int category()
    {
        return value ? 1 : 0;
    }

    @Override
    public int[] categories()
    {
        return new int[]{ category() };
    }

    @Override
    public int category(int index)
    {
        assert index == 0;
        return category();
    }

    @Override
    public List<FeatureType> types()
    {
        return Arrays.asList( type() );
    }

    @Override
    public FeatureType type(int index)
    {
        assert index == 0;
        return type();
    }

    @Override
    public List<BinaryValue> values()
    {
        return Arrays.asList( value() );
    }

    @Override
    public BinaryValue value(int index)
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
//    public Map<FeatureType, BinaryValue> map()
//    {
//        return new HashMap<FeatureType, BinaryValue>(){{
//            put(type(), value());
//        }};
//    }
}
