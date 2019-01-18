package ao.ai.model.common.feature_type;

import java.util.List;

/**
 * User: aostrovsky
 * Date: 23-Jan-2010
 * Time: 7:00:04 PM
 */
public interface FeatureTypeSet
{
    //--------------------------------------------------------------------
    public int indexOf(FeatureType type);
    
    public int featureCount();

    public List<FeatureType> types();

    public FeatureType       type(int index);
}
