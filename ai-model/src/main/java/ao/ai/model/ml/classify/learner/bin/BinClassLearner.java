package ao.ai.model.ml.classify.learner.bin;

import ao.ai.model.ml.classify.example.BinClassified;
import ao.ai.model.ml.classify.hypothesis.classifier.bin.BinClassifier;

import java.util.List;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:50:46 PM
 */
public interface BinClassLearner<I>
{
    //-------------------------------------------------------------------------
    public BinClassifier<I> learn(
            List<? extends BinClassified<I>> trainingPoints);
}
