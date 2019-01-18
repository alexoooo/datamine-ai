package ao.ai.model.ml.classify.hypothesis.classification;

import ao.ai.model.common.value.CategoricalValue;


/**
 * User: Alex Ostrovsky
 * Date: Mar 28, 2010
 * Time: 2:14:54 PM
 */
public interface RankedClassification
        extends Classification
{
    //-------------------------------------------------------------------------
    public CategoricalValue ranked(int rank);
    public int              size();
}
