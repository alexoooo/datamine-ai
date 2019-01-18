package ao.ai.model.ml.supervised.validation.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.validation.Validation;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 10:15:56 AM
 */
public interface BinaryValidation
            <I extends FeatureList<? extends FeatureValue>>
        extends Validation<I, SingleBinaryFeature>
{
    //--------------------------------------------------------------------
}