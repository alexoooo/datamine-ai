package ao.ai.model.ml.classify.hypothesis.classification.bin;

import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.ml.classify.hypothesis.classification.ProbClassification;

/**
 * User: Mable
 * Date: Apr 3, 2010
 * Time: 2:30:01 PM
 */
public interface BinProbClassification
        extends BinScoredClassification,
                ProbClassification
{
    //-------------------------------------------------------------------------
    public double probabilityOf(BinaryValue category);
}
