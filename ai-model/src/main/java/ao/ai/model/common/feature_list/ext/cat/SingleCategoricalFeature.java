package ao.ai.model.common.feature_list.ext.cat;

import ao.ai.model.common.feature_list.ext.SingleFeature;
import ao.ai.model.common.value.CategoricalValue;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:43:04 AM
 */
public interface SingleCategoricalFeature
            <V extends CategoricalValue>
        extends CategoricalFeatureList<V>,
                SingleFeature<V>
{
    //--------------------------------------------------------------------
    public int category();
}