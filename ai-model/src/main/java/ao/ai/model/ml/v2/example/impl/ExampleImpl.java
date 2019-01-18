package ao.ai.model.ml.v2.example.impl;

import ao.ai.model.ml.v2.example.Example;

/**
 * User: alex
 * Date: 15-May-2010
 * Time: 12:13:07 PM
 */
public class ExampleImpl<I, O>
        implements Example<I, O>
{
    //-------------------------------------------------------------------------
    private final I input;
    private final O output;


    //-------------------------------------------------------------------------
    public ExampleImpl(
            I input,
            O output)
    {
        assert input  != null;
        assert output != null;

        this.input  = input;
        this.output = output;
    }


    //-------------------------------------------------------------------------
    @Override
    public I input()
    {
        return input;
    }

    @Override
    public O output()
    {
        return output;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return input + " -> " + output;
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ExampleImpl)) return false;

        ExampleImpl example = (ExampleImpl) o;

        if (!input.equals(example.input)) return false;
        if (!output.equals(example.output)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = input.hashCode();
        result = 31 * result + output.hashCode();
        return result;
    }
}
