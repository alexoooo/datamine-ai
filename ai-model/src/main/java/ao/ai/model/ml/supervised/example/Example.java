package ao.ai.model.ml.supervised.example;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 5:57:31 PM
 */
public interface Example
        <I extends FeatureList<? extends FeatureValue>,
         O extends FeatureList<? extends FeatureValue>>
{
    //--------------------------------------------------------------------
    public I input();


    //--------------------------------------------------------------------
    public O output();
}
