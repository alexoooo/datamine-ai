package ao.ai.model.ml.classify.hypothesis.classification.bin.impl;

import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.common.value.impl.BinaryValueImpl;
import ao.ai.model.ml.classify.hypothesis.classification.bin.BinClassification;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:20:37 PM
 */
public class BinClassImpl
        implements BinClassification
{
    //-------------------------------------------------------------------------
    public static final BinClassification POSITIVE = new BinClassImpl(true );
    public static final BinClassification NEGATIVE = new BinClassImpl(false);

    public static BinClassification newInstance(boolean isPositive)
    {
        return isPositive ? POSITIVE : NEGATIVE;
    }


    //-------------------------------------------------------------------------
    private final BinaryValue best;


    //-------------------------------------------------------------------------
    public BinClassImpl(
            boolean bestClassification)
    {
        this(BinaryValueImpl.valueOf( bestClassification ));
    }

    public BinClassImpl(
            BinaryValue bestClassification)
    {
        best = bestClassification;
    }


    //-------------------------------------------------------------------------
    @Override
    public BinaryValue best()
    {
        return best;
    }

    @Override
    public boolean bestValue()
    {
        return best.getBin();
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return String.valueOf( best );
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinClassImpl that = (BinClassImpl) o;
        return !(best != null
                ? !best.equals(that.best)
                : that.best != null);
    }

    @Override
    public int hashCode()
    {
        return best != null ? best.hashCode() : 0;
    }
}
