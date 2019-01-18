package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.ScalarTarget;

/**
 * Date: Sep 1, 2010
 * Time: 11:40:25 PM
 */
public interface ScalarRegressor<I>
{
    //------------------------------------------------------------------------
    public ScalarTarget regress(I input);
}
