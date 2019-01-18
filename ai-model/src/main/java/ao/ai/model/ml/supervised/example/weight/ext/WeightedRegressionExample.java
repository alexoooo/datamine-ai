package ao.ai.model.ml.supervised.example.weight.ext;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.ml.supervised.example.ext.RegressionExample;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:11:52 AM
 */
public interface WeightedRegressionExample
        extends WeightedTargetedExample<
        NumericalFeatureList, SingleNumericalFeature>,
                RegressionExample
{

}
