package ao.ai.ml.model.algo;

import ao.ai.ml.model.output.VectorTarget;
import ao.ai.ml.model.theory.VectorRegressor;

/**
 * Date: Sep 6, 2010
 * Time: 2:41:37 PM
 */
public interface OnlineVectorLearner<I>
        extends VectorRegressor<I>
{
    //------------------------------------------------------------------------
    public void learn(I input, VectorTarget output);
}
