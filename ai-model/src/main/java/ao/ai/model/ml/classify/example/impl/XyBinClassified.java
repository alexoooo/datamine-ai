package ao.ai.model.ml.classify.example.impl;

import ao.ai.model.common.data.NumList;
import ao.ai.model.common.data.impl.IntNumList;
import ao.ai.model.ml.classify.example.ext.NumBinClassified;
import ao.ai.model.ml.classify.hypothesis.classification.bin.BinClassification;
import ao.ai.model.ml.classify.hypothesis.classification.bin.impl.BinClassImpl;

/**
 * User: alex
 * Date: 27-Apr-2010
 * Time: 8:27:34 PM
 */
public class XyBinClassified
        implements NumBinClassified
{
    //-------------------------------------------------------------------------
    private final int     X;
    private final int     Y;
    private final boolean IS_POSITIVE;


    //-------------------------------------------------------------------------
    public XyBinClassified(
            int x, int y, boolean isPositive)
    {
        X           = x;
        Y           = y;
        IS_POSITIVE = isPositive;
    }


    //-------------------------------------------------------------------------
    public int x() {  return X;  }
    public int y() {  return Y;  }


    //-------------------------------------------------------------------------
    @Override
    public NumList input()
    {
//        return new DoubleList(new double[]{X, Y});
        return new IntNumList(
                X, Y);
    }

    @Override
    public BinClassification classification()
    {
        return BinClassImpl.newInstance( IS_POSITIVE );
    }

    @Override
    public boolean isPositive()
    {
        return IS_POSITIVE;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return "XyBinClassified{" +
                  "X=" + X +
                ", Y=" + Y +
                ", IS_POSITIVE=" + IS_POSITIVE +
               '}';
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof XyBinClassified)) return false;

        XyBinClassified that = (XyBinClassified) o;

        if (IS_POSITIVE != that.IS_POSITIVE) return false;
        if (X != that.X) return false;
        if (Y != that.Y) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = X;
        result = 31 * result + Y;
        result = 31 * result + (IS_POSITIVE ? 1 : 0);
        return result;
    }
}
