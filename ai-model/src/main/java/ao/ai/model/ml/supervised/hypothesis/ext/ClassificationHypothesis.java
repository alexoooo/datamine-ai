package ao.ai.model.ml.supervised.hypothesis.ext;

import ao.ai.model.common.feature_list.ext.cat.SingleCategoricalFeature;
import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.value.CategoricalValue;
import ao.ai.model.ml.supervised.hypothesis.SupervisedHypothesis;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:36:36 AM
 */
public interface ClassificationHypothesis
            <O extends SingleCategoricalFeature<? extends CategoricalValue>>
        extends SupervisedHypothesis<
                    NumericalFeatureList, O>
{
    //-------------------------------------------------------------------------
    public double probabilityOf(
            NumericalFeatureList input, int categoryIndex);

//    public O mostProbable(
//            NumericalFeatureList input);
}
