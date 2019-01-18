package ao.ai.model.common.data;

import java.io.Serializable;

/**
 * User: alex
 * Date: 27-Apr-2010
 * Time: 10:14:45 PM
 */
public interface NumList
        extends Serializable
{
    //-------------------------------------------------------------------------
    public byte   getByte  (int index);
    public short  getShort (int index);
    public char   getChar  (int index);
    public int    getInt   (int index);
    public long   getLong  (int index);
    public float  getFloat (int index);
    public double getDouble(int index);


    //-------------------------------------------------------------------------
    public byte  [] getBytes  ();
    public short [] getShorts ();
    public char  [] getChars  ();
    public int   [] getInts   ();
    public long  [] getLongs  ();
    public float [] getFloats ();
    public double[] getDoubles();


//    //-------------------------------------------------------------------------
//    public NumCatList getNumEnumList();

    
    //-------------------------------------------------------------------------
    public int size();
}
