package ao.ai.ml.model.input.coll;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 12:44:55 AM
 */
public final class Example<I, O>
{
    //-------------------------------------------------------------------------
    private final I input;
    private final O output;


    //-------------------------------------------------------------------------
    public Example(
            I input,
            O output)
    {
        assert input  != null;
        assert output != null;

        this.input  = input;
        this.output = output;
    }


    //-------------------------------------------------------------------------
    public I input()
    {
        return input;
    }

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
        if (!(o instanceof Example)) return false;

        Example example = (Example) o;

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
