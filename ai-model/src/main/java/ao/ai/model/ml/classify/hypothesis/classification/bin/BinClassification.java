package ao.ai.model.ml.classify.hypothesis.classification.bin;

import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.ml.classify.hypothesis.classification.Classification;

/**
 * User: Mable
 * Date: Mar 29, 2010
 * Time: 11:59:35 PM
 */
public interface BinClassification
        extends Classification
{
    //-------------------------------------------------------------------------
    @Override
    public BinaryValue best();


    //-------------------------------------------------------------------------
    public boolean bestValue();
}
