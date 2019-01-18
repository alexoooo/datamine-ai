package ao.ai.model.ml.classify.hypothesis.classifier.bin;

import ao.ai.model.ml.classify.hypothesis.classification.bin.BinClassification;
import ao.ai.model.ml.classify.hypothesis.classifier.Classifier;

/**
 * User: Mable
 * Date: Apr 3, 2010
 * Time: 2:36:50 PM
 */
public interface BinClassifier<I>
        extends Classifier<I>
{
    //-------------------------------------------------------------------------
    @Override
    public BinClassification classify(I input);
}
