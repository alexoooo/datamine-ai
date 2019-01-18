package ao.ai.model.ml.regress;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.ml.supervised.algo.weight.SupervisedWeightedLearner;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 6:57:26 AM
 */
public interface RegressionWeightedLearner
        extends SupervisedWeightedLearner<
            NumericalFeatureList, SingleNumericalFeature>
{

}
