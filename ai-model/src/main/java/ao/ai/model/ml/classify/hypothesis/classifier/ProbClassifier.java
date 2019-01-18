package ao.ai.model.ml.classify.hypothesis.classifier;

import ao.ai.model.ml.classify.hypothesis.classification.ProbClassification;

/**
 * User: Alex Ostrovsky
 * Date: Mar 28, 2010
 * Time: 4:17:24 PM
 */
public interface ProbClassifier<I>
        extends ScoredClassifier<I>
{
    //-------------------------------------------------------------------------
    @Override
    public ProbClassification classify(I input);
}
