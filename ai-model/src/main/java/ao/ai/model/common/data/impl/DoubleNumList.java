package ao.ai.model.common.data.impl;

import ao.ai.model.common.data.NumList;

import java.util.Arrays;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 5:46:24 PM
 */
public class DoubleNumList
        implements NumList
{
    //-------------------------------------------------------------------------
    private double[] data;


    //-------------------------------------------------------------------------
    public DoubleNumList(double... copyData)
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
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLong(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloat(int index) {
        return (float) getDouble(index);
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
        throw new UnsupportedOperationException();
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
        return data.clone();
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
        if (!(o instanceof DoubleNumList)) return false;

        DoubleNumList that = (DoubleNumList) o;
        return Arrays.equals(data, that.data);

    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(data);
    }
}
