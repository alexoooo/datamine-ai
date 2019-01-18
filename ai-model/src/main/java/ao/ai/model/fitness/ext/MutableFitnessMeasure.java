package ao.ai.model.fitness.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.fitness.FitnessMeasure;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 1:14:30 PM
 */
public interface MutableFitnessMeasure
            <V extends FeatureList<? extends FeatureValue>>
        extends FitnessMeasure
{
    //--------------------------------------------------------------------
    public void add(V predicted, V actual);
}
