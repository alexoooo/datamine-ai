package ao.ai.ml.model.algo;

import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.theory.MultiClassifier;

/**
 * Date: Sep 6, 2010
 * Time: 2:14:35 PM
 */
public interface OnlineMultiLearner<I>
        extends MultiClassifier<I>
{
    //------------------------------------------------------------------------
//    @Override
    public void learn(I input, MultiClass output);
}
