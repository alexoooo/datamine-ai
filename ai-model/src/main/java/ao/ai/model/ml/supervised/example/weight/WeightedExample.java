package ao.ai.model.ml.supervised.example.weight;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.common.weight.Weighted;
import ao.ai.model.ml.supervised.example.Example;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:06:06 AM
 */
public interface WeightedExample
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends Example<I, O>, Weighted
{

}
