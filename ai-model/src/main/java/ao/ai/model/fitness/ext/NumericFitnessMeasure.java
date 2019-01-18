package ao.ai.model.fitness.ext;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.NumericalValue;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 9:15:37 AM
 */
public interface NumericFitnessMeasure
        extends MutableFitnessMeasure<
                  FeatureList<NumericalValue>>
{
//    //-------------------------------------------------------------------------
//    public void add(
//            FeatureList<NumericalValue> predicted,
//            FeatureList<NumericalValue> actual);
}