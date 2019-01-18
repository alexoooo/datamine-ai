package ao.ai.model.ml.regress.example;

import ao.ai.model.ml.regress.hypothesis.regression.MultiRegression;

/**
 * User: alex
 * Date: 18-Apr-2010
 * Time: 7:56:29 PM
 */
public interface MultiRegressed<I>
{
    //-------------------------------------------------------------------------
    public I input();


    //-------------------------------------------------------------------------
    public MultiRegression regression();
}