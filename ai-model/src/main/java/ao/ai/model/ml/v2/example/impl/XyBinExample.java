package ao.ai.model.ml.v2.example.impl;

import ao.ai.model.common.data.NumList;
import ao.ai.model.common.data.impl.IntNumList;
import ao.ai.model.ml.v2.clazz.BinClass;
import ao.ai.model.ml.v2.clazz.impl.BinClassImpl;
import ao.ai.model.ml.v2.example.Example;

/**
 * User: alex
 * Date: 15-May-2010
 * Time: 12:23:45 PM
 */
public class XyBinExample
        implements Example<NumList, BinClass>
{
    //-------------------------------------------------------------------------
    private final int     X;
    private final int     Y;
    private final BinClass IS_POSITIVE;



    //-------------------------------------------------------------------------
    public XyBinExample(
            int x, int y, boolean isPositive)
    {
        X           = x;
        Y           = y;
        IS_POSITIVE = BinClassImpl.create( isPositive );
    }


    //-------------------------------------------------------------------------
    public int x() {  return X;  }
    public int y() {  return Y;  }


    //-------------------------------------------------------------------------
    @Override
    public NumList input()
    {
        return new IntNumList(X, Y);
    }

    @Override
    public BinClass output()
    {
        return IS_POSITIVE;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return "(" + X + ", " + Y + ") -> " +
               IS_POSITIVE;
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof XyBinExample)) return false;

        XyBinExample that = (XyBinExample) o;

        if (X != that.X) return false;
        if (Y != that.Y) return false;
        if (!IS_POSITIVE.equals(that.IS_POSITIVE)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = X;
        result = 31 * result + Y;
        result = 31 * result + IS_POSITIVE.hashCode();
        return result;
    }
}
