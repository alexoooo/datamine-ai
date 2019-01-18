package ao.ai.model.ml.regress.example.impl;

import ao.ai.model.ml.regress.example.Regressed;
import ao.ai.model.ml.regress.hypothesis.regression.Regression;

/**
 * User: alex
 * Date: 18-Apr-2010
 * Time: 9:52:54 PM
 */
public class RegressedImpl<I>
        implements Regressed<I>
{
    //-------------------------------------------------------------------------
    public static <I> Regressed<I> newInstance(
            I input, Regression output)
    {
        return new RegressedImpl<I>(input, output);
    }


    //-------------------------------------------------------------------------
    private final I          input;
    private final Regression regression;


    //-------------------------------------------------------------------------
    public RegressedImpl(
            I          exampleInput,
            Regression exampleOutput)
    {
        input      = exampleInput;
        regression = exampleOutput;
    }


    //-------------------------------------------------------------------------
    @Override
    public I input()
    {
        return input;
    }

    @Override
    public Regression regression()
    {
        return regression;
    }
}
