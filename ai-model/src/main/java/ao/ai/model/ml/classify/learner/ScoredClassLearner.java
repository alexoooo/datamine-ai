package ao.ai.model.ml.classify.learner;

import ao.ai.model.ml.classify.example.Classified;
import ao.ai.model.ml.classify.hypothesis.classifier.ScoredClassifier;

import java.util.List;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:47:10 PM
 */
public interface ScoredClassLearner<I>
        extends RankedClassLearner<I>
{
    //-------------------------------------------------------------------------
    @Override
    public ScoredClassifier<I> learn(
            List<Classified<I>> trainingPoints);
}
