package ao.ai.model.ml.supervised.hypothesis;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 5:58:39 PM
 */
public interface SupervisedHypothesis
        <I extends FeatureList<? extends FeatureValue>,
          O extends FeatureList<? extends FeatureValue>>
{
    //--------------------------------------------------------------------
//    public O regress(I input);
    
}
