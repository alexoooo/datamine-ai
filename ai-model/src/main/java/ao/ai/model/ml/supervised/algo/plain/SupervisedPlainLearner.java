package ao.ai.model.ml.supervised.algo.plain;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.algo.SupervisedGeneralLearner;
import ao.ai.model.ml.supervised.example.Example;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 3:40:36 PM
 */
public interface SupervisedPlainLearner
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends SupervisedGeneralLearner<I, O,
                    Example<? extends I, ? extends O>>
{
    //--------------------------------------------------------------------
}
