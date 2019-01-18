package ao.ai.ml.model.input;

import java.util.Arrays;

/**
 * Date: Sep 6, 2010
 * Time: 12:47:11 PM
 */
public final class RealList
{
    //------------------------------------------------------------------------
    private final double[] values;


    //------------------------------------------------------------------------
    public RealList(double... values)
    {
        this.values = values.clone();
    }
    

    //------------------------------------------------------------------------
    public double get(int index)
    {
        return values[ index ];
    }

    public int size()
    {
        return values.length;
    }


    //------------------------------------------------------------------------
    public double[] toArray()
    {
        return values.clone();
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString( values );
    }


    //------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;

        RealList realList = (RealList) o;
        return Arrays.equals(values, realList.values);
    }

    @Override
    public int hashCode()
    {
        return values != null ? Arrays.hashCode(values) : 0;
    }
}
