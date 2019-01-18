package ao.ai.model.ml.regress;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.ml.supervised.algo.plain.SupervisedPlainLearner;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 3:52:36 PM
 */
public interface RegressionLearner
        extends SupervisedPlainLearner<
        NumericalFeatureList, SingleNumericalFeature>
{

}
