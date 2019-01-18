package ao.ai.model.ml.supervised.algo.weight;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.weight.WeightedExample;
import ao.ai.model.ml.supervised.hypothesis.SupervisedHypothesis;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 3:49:41 PM
 */
public interface SupervisedWeightedOnlineLearner
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends SupervisedWeightedLearner<I, O>
{
    //--------------------------------------------------------------------
    public SupervisedHypothesis<I, O> learn(
            Iterable<? extends WeightedExample<? extends I, ? extends O>>
                    trainingPoints);
}
