package ao.ai.ml.model.algo;

import ao.ai.ml.model.theory.MultiProbClassifier;

/**
 * Date: Sep 6, 2010
 * Time: 2:39:50 PM
 */
public interface OnlineMultiProbLearner<I>
        extends OnlineMultiScoreLearner<I>,
                MultiProbClassifier<I>
{
    //------------------------------------------------------------------------
}
