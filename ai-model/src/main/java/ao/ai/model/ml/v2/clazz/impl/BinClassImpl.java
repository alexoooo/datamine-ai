package ao.ai.model.ml.v2.clazz.impl;

import ao.ai.model.ml.v2.clazz.BinClass;

/**
 * User: alex
 * Date: 15-May-2010
 * Time: 11:37:19 AM
 */
public class BinClassImpl
        implements BinClass
{
    //-------------------------------------------------------------------------
    public static final BinClass POSITIVE = new BinClassImpl( true  );
    public static final BinClass NEGATIVE = new BinClassImpl( false );

    public static BinClass create(
            boolean isPositive)
    {
        return isPositive
               ? POSITIVE
               : NEGATIVE;
    }

    public static BinClass create(
            int bestIndex)
    {
        assert 0 <= bestIndex && bestIndex <= 1;
        return create(bestIndex == 1);
    }


    //-------------------------------------------------------------------------
    private final boolean best;


    //-------------------------------------------------------------------------
    protected BinClassImpl(boolean best)
    {
        this.best = best;
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean isPositive()
    {
        return best;
    }


    @Override
    public int best()
    {
        return best ? 1 : 0;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return best
               ? "positive"
               : "negative";
    }
}
