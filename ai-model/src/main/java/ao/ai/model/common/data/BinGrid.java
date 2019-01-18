package ao.ai.model.common.data;

import java.io.Serializable;

/**
 * User: alex
 * Date: 27-Apr-2010
 * Time: 10:08:42 PM
 */
public interface BinGrid
        extends Serializable
{
    //-------------------------------------------------------------------------
    public int rows();
    public int columns();


    //-------------------------------------------------------------------------
    public boolean get(int row, int column);
}
