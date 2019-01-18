package ao.ai.ml.model.input.alt;

import ao.ai.ml.model.common.AiModelUtils;
import ao.ai.ml.model.input.RealList;

import java.io.Serializable;
import java.util.BitSet;

/**
 * User: AO
 * Date: 10/28/10
 * Time: 10:52 PM
 */
public final class BinGrid
        implements Serializable
{
    //-------------------------------------------------------------------------
    private final int    rows;
    private final int    columns;

    private final BitSet bits;


    //-------------------------------------------------------------------------
    public BinGrid(
            int nRows, int nCols)
    {
        rows    = nRows;
        columns = nCols;

        bits    = new BitSet(rows * columns);
    }


    //-------------------------------------------------------------------------
    public int rows()
    {
        return rows;
    }

    public int columns()
    {
        return columns;
    }


    //-------------------------------------------------------------------------
    public boolean get(int row, int column)
    {
        return bits.get( index(row, column) );
    }

    public void set(int row, int column, boolean value)
    {
        bits.set( index(row, column), value );
    }


    //-------------------------------------------------------------------------
    private int index(int row, int column)
    {
        assert 0 <= row    && row    < rows;
        assert 0 <= column && column < columns;

        return row * columns + column;
    }


    //-------------------------------------------------------------------------
    public RealList toRealList()
    {
        double[] flat  = new double[rows * columns];
        int      index = 0;

        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < columns; col++)
            {
                flat[ index++ ] = AiModelUtils.sgn(
                        get(row, col));
            }
        }

        return new RealList( flat );
    }
}
