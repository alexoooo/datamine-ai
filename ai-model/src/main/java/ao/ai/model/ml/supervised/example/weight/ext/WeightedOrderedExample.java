package ao.ai.model.ml.supervised.example.weight.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.ext.OrderedExample;
import ao.ai.model.ml.supervised.example.weight.WeightedExample;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:09:06 AM
 */
public interface WeightedOrderedExample
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends OrderedExample<I, O>, WeightedExample<I, O>
{
    
}
