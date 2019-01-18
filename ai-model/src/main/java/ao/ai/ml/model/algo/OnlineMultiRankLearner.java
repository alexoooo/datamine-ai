package ao.ai.ml.model.algo;

import ao.ai.ml.model.theory.MultiRankClassifier;

/**
 * Date: Sep 6, 2010
 * Time: 2:37:58 PM
 */
public interface OnlineMultiRankLearner<I>
        extends OnlineMultiLearner<I>,
                MultiRankClassifier<I>
{
    //------------------------------------------------------------------------
}
