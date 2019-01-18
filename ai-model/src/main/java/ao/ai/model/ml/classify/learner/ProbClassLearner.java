package ao.ai.model.ml.classify.learner;

import ao.ai.model.ml.classify.example.Classified;
import ao.ai.model.ml.classify.hypothesis.classifier.ScoredClassifier;

import java.util.List;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:49:21 PM
 */
public interface ProbClassLearner<I>
        extends ScoredClassLearner<I>
{
    //-------------------------------------------------------------------------
    @Override
    public ScoredClassifier<I> learn(
            List<Classified<I>> trainingPoints);
}
