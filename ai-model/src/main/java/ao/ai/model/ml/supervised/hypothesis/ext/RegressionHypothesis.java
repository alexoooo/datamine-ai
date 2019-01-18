package ao.ai.model.ml.supervised.hypothesis.ext;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;
import ao.ai.model.ml.supervised.hypothesis.SupervisedHypothesis;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 6:54:46 PM
 */
public interface RegressionHypothesis
        extends SupervisedHypothesis<
                  NumericalFeatureList,
                  SingleNumericalFeature>
{

}
