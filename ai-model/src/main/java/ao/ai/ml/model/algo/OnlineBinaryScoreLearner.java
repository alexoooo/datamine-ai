package ao.ai.ml.model.algo;

import ao.ai.ml.model.theory.BinaryScoreClassifier;

/**
 * Date: Sep 2, 2010
 * Time: 12:20:55 AM
 */
public interface OnlineBinaryScoreLearner<I>
        extends OnlineBinaryLearner<I>,
                BinaryScoreClassifier<I>
{
    //------------------------------------------------------------------------
//    @Override
//    public BinaryScoreClass classify(I input);
}
