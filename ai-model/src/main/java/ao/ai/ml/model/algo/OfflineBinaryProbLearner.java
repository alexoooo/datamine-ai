package ao.ai.ml.model.algo;

import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.theory.BinaryProbClassifier;

/**
 * User: AO
 * Date: Sep 22, 2010
 * Time: 9:28:47 PM
 */
public interface OfflineBinaryProbLearner<I>
        extends OfflineBinaryScoreLearner<I>
{
    //------------------------------------------------------------------------
    @Override
    public BinaryProbClassifier<I> learn(
            Sample<I, BinaryClass> trainingSet);
}
