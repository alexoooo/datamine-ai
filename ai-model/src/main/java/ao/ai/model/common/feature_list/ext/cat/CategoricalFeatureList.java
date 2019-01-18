package ao.ai.model.common.feature_list.ext.cat;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.CategoricalValue;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:43:22 AM
 */
public interface CategoricalFeatureList
            <V extends CategoricalValue>
        extends FeatureList<V>
{
    //--------------------------------------------------------------------
    public int[] categories();

    public int   category(int index);
}