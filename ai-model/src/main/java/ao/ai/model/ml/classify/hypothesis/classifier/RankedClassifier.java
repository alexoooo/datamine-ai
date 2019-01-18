package ao.ai.model.ml.classify.hypothesis.classifier;

import ao.ai.model.ml.classify.hypothesis.classification.RankedClassification;

/**
 * User: Alex Ostrovsky
 * Date: Mar 28, 2010
 * Time: 4:13:32 PM
 */
public interface RankedClassifier<I>
        extends Classifier<I>
{
    //-------------------------------------------------------------------------
    @Override
    public RankedClassification classify(I input);
}
