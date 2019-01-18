package ao.ai.model.ml.classify.hypothesis.classifier;

import ao.ai.model.ml.classify.hypothesis.classification.ScoredClassification;

/**
 * User: Alex Ostrovsky
 * Date: Mar 28, 2010
 * Time: 4:16:23 PM
 */
public interface ScoredClassifier<I>
        extends RankedClassifier<I>
{
    //-------------------------------------------------------------------------
    @Override
    public ScoredClassification classify(I input);
}
