package ao.ai.model.ml.regress.example;

import ao.ai.model.ml.regress.hypothesis.regression.Regression;

/**
 * User: alex
 * Date: 14-Apr-2010
 * Time: 11:10:18 PM
 */
public interface Regressed<I>
{
    //-------------------------------------------------------------------------
    public I input();


    //-------------------------------------------------------------------------
    public Regression regression();
}
