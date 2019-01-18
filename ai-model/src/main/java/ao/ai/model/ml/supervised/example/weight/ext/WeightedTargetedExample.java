package ao.ai.model.ml.supervised.example.weight.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.feature_list.ext.SingleFeature;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.ext.TargetedExample;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:10:26 AM
 */
public interface WeightedTargetedExample
            <I extends FeatureList<? extends FeatureValue>,
             O extends SingleFeature<? extends FeatureValue>>
        extends TargetedExample<I, O>, WeightedOrderedExample<I, O>
{
    
}
