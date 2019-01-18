package ao.ai.model.common.feature_type.ext;

import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.feature_type.FeatureTypeSet;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 3:33:28 PM
 */
public interface MutableFeatureSet
        extends FeatureTypeSet
{
    //--------------------------------------------------------------------
    public int add(FeatureType type);
}
