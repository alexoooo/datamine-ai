package ao.ai.model.ml.supervised.algo;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.Example;
import ao.ai.model.ml.supervised.hypothesis.SupervisedHypothesis;

import java.util.List;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 1:57:41 PM
 */
public interface SupervisedGeneralLearner
        <I extends FeatureList<? extends FeatureValue>,
         O extends FeatureList<? extends FeatureValue>,
         E extends Example    <? extends I, ? extends O>>
{
    //--------------------------------------------------------------------
    public SupervisedHypothesis<I, O> learn(
            List<? extends E> trainingPoints);
}
