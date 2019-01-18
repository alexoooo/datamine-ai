package ao.ai.model.ml.supervised.example.ext;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.ext.num.SingleNumericalFeature;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 6:47:42 PM
 */
public interface RegressionExample
        extends TargetedExample<
                NumericalFeatureList, SingleNumericalFeature>
{

}
