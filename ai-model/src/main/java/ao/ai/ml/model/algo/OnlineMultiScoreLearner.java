package ao.ai.ml.model.algo;

import ao.ai.ml.model.theory.MultiScoreClassifier;

/**
 * User: Mable
 * Date: Sep 6, 2010
 * Time: 2:39:04 PM
 */
public interface OnlineMultiScoreLearner<I>
        extends OnlineMultiRankLearner<I>,
                MultiScoreClassifier<I>
{
    //------------------------------------------------------------------------
}
