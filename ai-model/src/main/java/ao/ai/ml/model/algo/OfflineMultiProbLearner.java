package ao.ai.ml.model.algo;

import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.theory.MultiProbClassifier;

/**
 * User: AO
 * Date: Sep 22, 2010
 * Time: 9:33:01 PM
 */
public interface OfflineMultiProbLearner<I>
        extends OfflineMultiScoreLearner<I>
{
    //------------------------------------------------------------------------
    @Override
    public MultiProbClassifier<I> learn(
            Sample<I, MultiClass> trainingSet);
}
