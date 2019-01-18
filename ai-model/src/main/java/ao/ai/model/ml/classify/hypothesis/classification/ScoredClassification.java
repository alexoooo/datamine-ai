package ao.ai.model.ml.classify.hypothesis.classification;

import ao.ai.model.common.value.CategoricalValue;

/**
 * User: Alex Ostrovsky
 * Date: Mar 28, 2010
 * Time: 4:09:16 PM
 */
public interface ScoredClassification
        extends RankedClassification
{
    //-------------------------------------------------------------------------
    public double score(int rank);
//
//    public double score(CategoricalValue of);
}
