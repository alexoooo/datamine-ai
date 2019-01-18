package ao.ai.model.common.feature_list.ext.cat.bin;

import ao.ai.model.common.feature_list.ext.cat.SingleCategoricalFeature;
import ao.ai.model.common.value.ext.BinaryValue;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 8:50:49 AM
 */
public interface SingleBinaryFeature
        extends BinaryFeatureList,
                SingleCategoricalFeature<BinaryValue>
{
    //--------------------------------------------------------------------
    public boolean binaryCategory();
}
