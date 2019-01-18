package ao.ai.model.ml.supervised.algo.plain;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.Example;
import ao.ai.model.ml.supervised.hypothesis.SupervisedHypothesis;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 3:44:21 PM
 */
public interface SupervisedPlainOnlineLearner
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends SupervisedPlainLearner<I, O>
{
    //--------------------------------------------------------------------
    public SupervisedHypothesis<I, O> learn(
            Iterable<? extends Example<? extends I, ? extends O>>
                    trainingPoints);
}
