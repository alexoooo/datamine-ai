package ao.ai.model.common.feature_list.ext.cat.multi;

import ao.ai.model.common.feature_list.ext.cat.SingleCategoricalFeature;
import ao.ai.model.common.value.CategoricalValue;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:43:04 AM
 */
public interface SingleNaryFeature
        extends NaryFeatureList,
                SingleCategoricalFeature<CategoricalValue>
{
    //--------------------------------------------------------------------
    public int category();
}
