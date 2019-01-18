package ao.ai.ml.model.algo;

import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.theory.BinaryClassifier;

/**
 * Date: Sep 6, 2010
 * Time: 2:43:02 PM
 */
public interface OfflineBinaryLearner<I>
{
    //------------------------------------------------------------------------
    public BinaryClassifier<I> learn(
//    public Hypothesis<I, BinaryClass> learn(
            Sample<I, BinaryClass> trainingSet);
}
