package ao.ai.model.common.feature_list.ext.num;

import ao.ai.model.common.feature_list.ext.SingleFeature;
import ao.ai.model.common.value.NumericalValue;

/**
 * User: aostrovsky
 * Date: 23-Jan-2010
 * Time: 9:21:59 PM
 */
public interface SingleNumericalFeature
        extends NumericalFeatureList,
                SingleFeature<NumericalValue>
{
    //--------------------------------------------------------------------
    public double doubleValue();
}
