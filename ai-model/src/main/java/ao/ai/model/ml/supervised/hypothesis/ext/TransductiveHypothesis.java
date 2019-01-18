package ao.ai.model.ml.supervised.hypothesis.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.hypothesis.SupervisedHypothesis;

import java.util.List;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 6:57:14 PM
 */
public interface TransductiveHypothesis
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends SupervisedHypothesis<I, O>
{
    //--------------------------------------------------------------------
    public List<O> predict(List<I> input);
}
