package ao.ai.model.ml.classify.learner.bin;

import ao.ai.model.ml.classify.example.BinClassified;
import ao.ai.model.ml.classify.hypothesis.classifier.bin.BinRankedClassifier;

import java.util.List;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:52:20 PM
 */
public interface BinRankedClassLearner<I>
        extends BinClassLearner<I>
{
    //-------------------------------------------------------------------------
    @Override
    public BinRankedClassifier<I> learn(
            List<? extends BinClassified<I>> trainingPoints);
}
