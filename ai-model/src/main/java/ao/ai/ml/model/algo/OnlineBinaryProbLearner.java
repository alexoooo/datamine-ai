package ao.ai.ml.model.algo;

import ao.ai.ml.model.theory.BinaryProbClassifier;

/**
 * Date: Sep 2, 2010
 * Time: 12:22:08 AM
 */
public interface OnlineBinaryProbLearner<I>
        extends OnlineBinaryScoreLearner<I>,
                BinaryProbClassifier<I>
{
    //------------------------------------------------------------------------
//    @Override
//    public BinaryProbClass classify(I input);
}
