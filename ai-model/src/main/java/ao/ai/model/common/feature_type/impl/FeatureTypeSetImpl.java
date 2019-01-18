package ao.ai.model.common.feature_type.impl;

import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.feature_type.ext.MutableFeatureSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 9:07:55 PM
 */
public class FeatureTypeSetImpl
        implements MutableFeatureSet
{
    //--------------------------------------------------------------------
    private       int                       nextIndex;
    private final List<FeatureType>         typeList;
    private final Map<FeatureType, Integer> typeMap;


    //--------------------------------------------------------------------
    public FeatureTypeSetImpl()
    {
        typeList  = new ArrayList<FeatureType>();
        typeMap   = new HashMap<FeatureType, Integer>();
        nextIndex = 0;
    }

    public FeatureTypeSetImpl(FeatureType... types)
    {
        this();

        for (FeatureType type : types)
        {
            add( type );
        }
    }


    //--------------------------------------------------------------------
    @Override
    public int add(FeatureType type)
    {
        int index = indexOf(type);
        if (index == -1)
        {
            index = nextIndex++;
            typeList.add(type);
            typeMap.put(type, index);
        }
        return index;
    }


    //--------------------------------------------------------------------
    @Override
    public int indexOf(FeatureType type)
    {
        return typeMap.containsKey( type )
               ? typeMap.get( type ) : -1;
    }

    @Override
    public int featureCount()
    {
        return typeList.size();
    }

    @Override
    public List<FeatureType> types()
    {
        return typeList;
    }

    @Override
    public FeatureType type(int index)
    {
        return typeList.get( index );
    }
}
