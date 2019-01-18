package ao.ai.model.ml.supervised.algo.weight;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.algo.SupervisedGeneralLearner;
import ao.ai.model.ml.supervised.example.weight.WeightedExample;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 2:18:44 PM
 */
public interface SupervisedWeightedLearner
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends SupervisedGeneralLearner<I, O,
                            WeightedExample<? extends I, ? extends O>>
{
    //--------------------------------------------------------------------
}
