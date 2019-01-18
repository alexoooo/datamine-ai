package ao.ai.model.ml.regress.hypothesis.regressor;

import ao.ai.model.ml.regress.hypothesis.regression.MultiRegression;

/**
 * User: alex
 * Date: 18-Apr-2010
 * Time: 7:49:46 PM
 */
public interface MultiRegressor<I>
{
    //-------------------------------------------------------------------------
    public MultiRegression regress(I input);
}
