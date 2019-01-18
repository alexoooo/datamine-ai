package ao.ai.model.ml.classify.learner;

import ao.ai.model.ml.classify.example.Classified;
import ao.ai.model.ml.classify.hypothesis.classifier.Classifier;

import java.util.List;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 9:54:17 PM
 */
public interface ClassLearner<I>
{
    //-------------------------------------------------------------------------
    public Classifier<I> learn(
            List<Classified<I>> trainingPoints);
}
