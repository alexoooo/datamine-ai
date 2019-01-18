package ao.ai.model.ml.supervised.hypothesis.ext;

import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;
import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:36:36 AM
 */
public interface BinaryClassificationHypothesis
        extends ClassificationHypothesis<
                  SingleBinaryFeature>
{
    //-------------------------------------------------------------------------
    public double probabilityOfPositive(
            NumericalFeatureList input);
}