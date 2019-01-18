package ao.ai.model.common.data.impl;

import ao.ai.model.common.data.NumList;

import java.util.Arrays;


/**
 * User: alex
 * Date: 27-Apr-2010
 * Time: 10:17:28 PM
 */
public class IntNumList
        implements NumList
{
    //-------------------------------------------------------------------------
    private int[] data;


    //-------------------------------------------------------------------------
    public IntNumList(int... copyData)
    {
        data = copyData;
    }


    //-------------------------------------------------------------------------
    @Override
    public byte getByte(int index)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getShort(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getChar(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getInt(int index) {
        return data[ index ];
    }

    @Override
    public long getLong(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloat(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getDouble(int index) {
        return data[ index ];
    }


    //-------------------------------------------------------------------------
    @Override
    public byte[] getBytes() {
        throw new UnsupportedOperationException();
    }

    @Override
    public short[] getShorts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public char[] getChars() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] getInts() {
        return data.clone();
    }

    @Override
    public long[] getLongs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float[] getFloats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double[] getDoubles() {
        double[] asDoubles = new double[ data.length ];
        for (int i = 0; i < data.length; i++)
        {
            asDoubles[ i ] = data[ i ];
        }
        return asDoubles;
    }


    //-------------------------------------------------------------------------
    @Override
    public int size() {
        return data.length;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString( data );
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof IntNumList)) return false;

        IntNumList that = (IntNumList) o;

        if (!Arrays.equals(data, that.data)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(data);
    }
}
