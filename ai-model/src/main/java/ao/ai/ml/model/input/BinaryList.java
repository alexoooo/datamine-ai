package ao.ai.ml.model.input;

import java.io.Serializable;
import java.util.BitSet;

/**
 * User: Mable
 * Date: Sep 2, 2010
 * Time: 12:30:10 AM
 */
public final class BinaryList
        implements Serializable
{
    //-------------------------------------------------------------------------
    private final BitSet bits = new BitSet();
    private final int    size;


    //-------------------------------------------------------------------------
    public BinaryList(
            boolean... binList)
    {
        size = binList.length;

        for (int i = 0; i < binList.length; i++)
        {
            bits.set(i, binList[ i ]);
        }
    }

    public BinaryList(
            BitSet bits, int size)
    {
        this.size = size;
        this.bits.or( bits );
    }


    //-------------------------------------------------------------------------
    public int size()
    {
        return size;
    }

    public boolean get(int index)
    {
        return bits.get( index );
    }


    //-------------------------------------------------------------------------
//    public RealList toRealList()
//    {
//        return null;
//    }
}