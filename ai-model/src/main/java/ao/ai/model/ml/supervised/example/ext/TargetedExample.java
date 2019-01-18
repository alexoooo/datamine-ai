package ao.ai.model.ml.supervised.example.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.feature_list.ext.SingleFeature;
import ao.ai.model.common.value.FeatureValue;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 6:43:41 PM
 */
public interface TargetedExample
            <I extends FeatureList<? extends FeatureValue>,
             O extends SingleFeature<? extends FeatureValue>>
        extends OrderedExample<I, O>
{

}
