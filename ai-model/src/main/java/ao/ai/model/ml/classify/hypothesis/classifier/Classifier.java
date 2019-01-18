package ao.ai.model.ml.classify.hypothesis.classifier;

import ao.ai.model.ml.classify.hypothesis.classification.Classification;

/**
 * User: Mable
 * Date: Mar 28, 2010
 * Time: 11:46:48 AM
 */
public interface Classifier<I>
{
    //-------------------------------------------------------------------------
    public Classification classify(I input);
}
