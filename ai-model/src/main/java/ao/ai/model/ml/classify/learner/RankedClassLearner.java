package ao.ai.model.ml.classify.learner;

import ao.ai.model.ml.classify.example.Classified;
import ao.ai.model.ml.classify.hypothesis.classifier.RankedClassifier;

import java.util.List;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:46:26 PM
 */
public interface RankedClassLearner<I>
        extends ClassLearner<I>
{
    //-------------------------------------------------------------------------
    @Override
    public RankedClassifier<I> learn(
            List<Classified<I>> trainingPoints);
}
