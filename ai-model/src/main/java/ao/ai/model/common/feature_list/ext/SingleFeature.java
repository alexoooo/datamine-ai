package ao.ai.model.common.feature_list.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.value.FeatureValue;

/**
 * User: aostrovsky
 * Date: 23-Jan-2010
 * Time: 9:02:02 PM
 */
public interface SingleFeature
            <V extends FeatureValue>
        extends FeatureList<V>
{
    //--------------------------------------------------------------------
    public FeatureType type();
    public V           value();
}
