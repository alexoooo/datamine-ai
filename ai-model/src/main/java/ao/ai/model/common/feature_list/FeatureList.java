package ao.ai.model.common.feature_list;

import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.value.FeatureValue;

import java.util.List;

/**
 * User: aostrovsky
 * Date: 23-Jan-2010
 * Time: 9:00:36 PM
 */
public interface FeatureList
        <V extends FeatureValue>
{
    //--------------------------------------------------------------------
    public List<FeatureType> types();
    public FeatureType       type(int index);

    public List<V>           values();
    public V                 value(int index);

    public int size();
}
