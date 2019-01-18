package ao.ai.model.common.feature_list.ext.num;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.NumericalValue;

/**
 * User: aostrovsky
 * Date: 23-Jan-2010
 * Time: 9:05:03 PM
 */
public interface NumericalFeatureList
        extends FeatureList<NumericalValue>
{
    //--------------------------------------------------------------------
    public double[] doubleValues();

    public double   doubleValue(int index);
}
