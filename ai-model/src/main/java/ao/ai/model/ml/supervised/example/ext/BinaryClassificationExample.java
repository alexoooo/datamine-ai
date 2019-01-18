package ao.ai.model.ml.supervised.example.ext;

import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;
import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;

/**
 * User: aostrovsky
 * Date: 12-Feb-2010
 * Time: 11:20:17 PM
 */
public interface BinaryClassificationExample
        extends TargetedExample<
                    NumericalFeatureList,
                    SingleBinaryFeature>
{
    //-------------------------------------------------------------------------
}
