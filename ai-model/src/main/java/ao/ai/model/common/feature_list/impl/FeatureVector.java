package ao.ai.model.common.feature_list.impl;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.feature_type.FeatureTypeSet;
import ao.ai.model.common.value.NumericalValue;
import ao.ai.model.common.value.impl.NumericalValueImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 8:33:11 PM
 */
public class FeatureVector
        implements NumericalFeatureList
{
    //--------------------------------------------------------------------
    private final double[]       values;
    private final FeatureTypeSet types;


    //--------------------------------------------------------------------
    public FeatureVector(
            FeatureTypeSet featureTypeSet,
            double...      features)
    {
        values = features;
        types  = featureTypeSet;
    }


    //--------------------------------------------------------------------
    @Override
    public int size()
    {
        return values.length;
    }


    //--------------------------------------------------------------------
    @Override
    public double[] doubleValues()
    {
        return values.clone();
    }

    @Override
    public double doubleValue(int index)
    {
        return values[ index ];
    }


    //--------------------------------------------------------------------
    @Override
    public List<FeatureType> types()
    {
        return types.types();
    }

    @Override
    public FeatureType type(int index)
    {
        return types.type( index );
    }


    //--------------------------------------------------------------------
    @Override
    public List<NumericalValue> values()
    {
        List<NumericalValue> valueList =
                new ArrayList<NumericalValue>( values.length );
        for (int i = 0; i < size(); i++)
        {
            valueList.add( value(i) );
        }
        return valueList;

//        return new AbstractList<RationalValue>() {
//            @Override
//            public RationalValue get(int index)
//            {
//                return new RationalValueImpl( values[index] );
//            }
//
//            @Override
//            public int size()
//            {
//                return values.length;
//            }
//        };
    }

    @Override
    public NumericalValue value(int index)
    {
        return new NumericalValueImpl( values[index] );
    }


//    //--------------------------------------------------------------------
//    @Override
//    public Map<FeatureType, NumericalValue> map()
//    {
//        Map<FeatureType, NumericalValue> featureMap =
//                new HashMap<FeatureType, NumericalValue>();
//
//        for (int i = 0; i < values.length; i++)
//        {
//            featureMap.put(type(i), value(i));
//        }
//
//        return featureMap;
//    }
}
