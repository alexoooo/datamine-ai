package ao.ai.model.ml.supervised.validation;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.fitness.FitnessMeasure;
import ao.ai.model.ml.supervised.algo.plain.SupervisedPlainLearner;
import ao.ai.model.ml.supervised.example.Example;

import java.util.Collection;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 9:48:02 AM
 */
public interface Validation
        <I extends FeatureList<? extends FeatureValue>,
         O extends FeatureList<? extends FeatureValue>>
{
    //--------------------------------------------------------------------
    public <II extends I, OO extends O>
                FitnessMeasure
            validate(SupervisedPlainLearner<II, OO> learner,
                     Collection<? extends Example<II, OO>> data);
}
