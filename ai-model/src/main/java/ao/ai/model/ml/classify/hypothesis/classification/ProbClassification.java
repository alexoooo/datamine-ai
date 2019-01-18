package ao.ai.model.ml.classify.hypothesis.classification;

import ao.ai.model.common.value.CategoricalValue;

/**
 * User: Alex Ostrovsky
 * Date: Mar 28, 2010
 * Time: 4:10:39 PM
 */
public interface ProbClassification
        extends ScoredClassification
{
    //-------------------------------------------------------------------------
    public double probabilityOf(int rank);
    public double probabilityOf(CategoricalValue category);
}
