package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.VectorTarget;

/**
 * Date: Sep 1, 2010
 * Time: 11:44:52 PM
 */
public interface VectorRegressor<I>
{
    //------------------------------------------------------------------------
    public VectorTarget regress(I input);
}
