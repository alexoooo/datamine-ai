package ao.ai.ml.model.algo;

import ao.ai.ml.model.output.ScalarTarget;
import ao.ai.ml.model.theory.ScalarRegressor;

/**
 * Date: Sep 6, 2010
 * Time: 2:40:37 PM
 */
public interface OnlineScalarLearner<I>
        extends ScalarRegressor<I>
{
    //------------------------------------------------------------------------
    public void learn(I input, ScalarTarget output);
}
