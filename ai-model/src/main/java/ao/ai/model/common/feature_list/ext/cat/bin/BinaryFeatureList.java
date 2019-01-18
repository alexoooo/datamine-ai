package ao.ai.model.common.feature_list.ext.cat.bin;

import ao.ai.model.common.feature_list.ext.cat.CategoricalFeatureList;
import ao.ai.model.common.value.ext.BinaryValue;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 8:49:34 AM
 */
public interface BinaryFeatureList
        extends CategoricalFeatureList<BinaryValue>
{
    //--------------------------------------------------------------------
    public boolean[] binaryCategories();

    public boolean   binaryCategory(int index);
}
