package ao.ai.model.ml.classify.hypothesis.classification.bin;

import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.ml.classify.hypothesis.classification.RankedClassification;

/**
 * User: Mable
 * Date: Apr 3, 2010
 * Time: 2:27:47 PM
 */
public interface BinRankedClassification
        extends BinClassification,
                RankedClassification
{
    //-------------------------------------------------------------------------
    @Override
    public BinaryValue ranked(int rank);
}
