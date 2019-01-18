package ao.ai.model.ml.supervised.example.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.Example;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 6:01:35 PM
 */
public interface OrderedExample
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        extends Example<I, O>
{

}

