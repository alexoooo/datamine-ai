package ao.ai.model.common.data.impl;

import ao.ai.model.common.data.BinGrid;
import ao.ai.model.common.data.NumCatList;

import java.util.Arrays;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 1:03:51 PM
 */
public class NumCatListImpl
        implements NumCatList
{
    //-------------------------------------------------------------------------
    private final Object[] values;
    
    
    //-------------------------------------------------------------------------
    public NumCatListImpl(
            BinGrid categoricalValues)
    {
        this( asEnum(categoricalValues) );
    }

    public NumCatListImpl(
            double... numericValues)
    {
        this( asDouble(numericValues) );
    }

    public NumCatListImpl(
            Object[] numericAndEnumValues)
    {
        values = new Object[ numericAndEnumValues.length ];
        
        for (int i = 0; i < values.length; i++)
        {
            Object numericOrEnum = numericAndEnumValues[ i ];
            
            if (! ((numericOrEnum instanceof Number) ||
                   (numericOrEnum instanceof String) ||
                   (numericOrEnum instanceof Enum)))
            {
                throw new IllegalArgumentException(
                        numericOrEnum + "(" + i + ") " +
                        "must be a Number, String, or Enum");
            }
            
            
            values[ i ] = numericAndEnumValues[ i ];
        }
    }


    //-------------------------------------------------------------------------
    private static Object[] asEnum(BinGrid values)
    {
        Object[] input = new Object[
                values.rows() * values.columns() ];

        int index = 0;
        for (int row = 0; row < values.rows(); row++)
        {
            for (int col = 0; col < values.columns(); col++)
            {
                input[ index ] = String.valueOf(
                        values.get(row, col));

                index++;
            }
        }

        return input;
    }

    private static Double[] asDouble(double... values)
    {
        Double[] input = new Double[ values.length ];

        for (int i = 0; i < input.length; i++)
        {
            input[ i ] = values[ i ];
        }

        return input;
    }

    
    //-------------------------------------------------------------------------
    @Override
    public double getNum(int index)
    {
        return isNum( index )
               ? ((Number) values[ index ]).doubleValue()
               : Double.NaN;
    }

    @Override
    public String getEnum(int index)
    {
        return isNum( index )
               ? null
               : values[ index ].toString();
    }

    
    //-------------------------------------------------------------------------
    @Override
    public boolean isNum(int index)
    {
        return (values[ index ] instanceof Number);
    }

    
    //-------------------------------------------------------------------------
    @Override
    public int size()
    {
        return values.length;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString( values );
    }

    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof NumCatListImpl)) return false;

        NumCatListImpl that = (NumCatListImpl) o;
        return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(values);
    }
}
