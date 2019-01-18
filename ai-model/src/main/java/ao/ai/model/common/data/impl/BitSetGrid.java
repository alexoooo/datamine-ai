package ao.ai.model.common.data.impl;

import ao.ai.model.common.data.BinGrid;

import java.util.BitSet;

/**
 * User: alex
 * Date: 28-Apr-2010
 * Time: 12:01:28 AM
 */
public class BitSetGrid
        implements BinGrid
{
    //-------------------------------------------------------------------------
    private final int    rows;
    private final int    columns;

    private final BitSet bits;

    
    //-------------------------------------------------------------------------
    public BitSetGrid(
            int nRows, int nCols)
    {
        rows    = nRows;
        columns = nCols;

        bits    = new BitSet(rows * columns);
    }



    //-------------------------------------------------------------------------
    @Override
    public int rows()
    {
        return rows;
    }

    @Override
    public int columns()
    {
        return columns;
    }


    //-------------------------------------------------------------------------
    @Override
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
}
