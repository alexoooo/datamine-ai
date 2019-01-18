package ao.ai.ml.model.output;

/**
 * User: AO
 * Date: Sep 1, 2010
 * Time: 11:01:22 PM
 */
public class BinaryClass
{
    //------------------------------------------------------------------------
    private static final BinaryClass POSITIVE = new BinaryClass( true  );
    private static final BinaryClass NEGATIVE = new BinaryClass( false );

    public static BinaryClass create(boolean isPositive)
    {
        return isPositive ? POSITIVE : NEGATIVE;
    }


    //------------------------------------------------------------------------
    private final boolean isPositive;


    //------------------------------------------------------------------------
    /*package-private*/ BinaryClass(boolean isPositive)
    {
        this.isPositive = isPositive;
    }


    //------------------------------------------------------------------------
    public boolean isPositive()
    {
        return isPositive;
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return isPositive()
               ? "positive"
               : "negative";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryClass binClass = (BinaryClass) o;
        return isPositive == binClass.isPositive;
    }

    @Override
    public int hashCode()
    {
        return (isPositive ? 1 : 0);
    }
}
