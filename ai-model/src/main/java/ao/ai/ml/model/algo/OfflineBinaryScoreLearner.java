package ao.ai.ml.model.algo;

import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.theory.BinaryScoreClassifier;

/**
 * User: AO
 * Date: Sep 22, 2010
 * Time: 9:28:47 PM
 */
public interface OfflineBinaryScoreLearner<I>
        extends OfflineBinaryLearner<I>
{
    //------------------------------------------------------------------------
    @Override
    public BinaryScoreClassifier<I> learn(
            Sample<I, BinaryClass> trainingSet);
}
