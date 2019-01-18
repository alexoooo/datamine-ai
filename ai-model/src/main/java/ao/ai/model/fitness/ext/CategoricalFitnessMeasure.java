package ao.ai.model.fitness.ext;

import ao.ai.model.common.feature_list.ext.cat.SingleCategoricalFeature;
import ao.ai.model.common.value.CategoricalValue;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 9:15:37 AM
 */
public interface CategoricalFitnessMeasure
            <O extends SingleCategoricalFeature<? extends CategoricalValue>>
        extends MutableFitnessMeasure<O>
{
//    //-------------------------------------------------------------------------
//    public void add(
//            O predicted,
//            O actual);
}