package ao.ai.model.ml.regress.hypothesis.regressor;

import ao.ai.model.ml.regress.hypothesis.regression.Regression;

/**
 * User: alex
 * Date: 18-Apr-2010
 * Time: 7:46:05 PM
 */
public interface Regressor<I>
{
    //-------------------------------------------------------------------------
    public Regression regress(I input);
}
