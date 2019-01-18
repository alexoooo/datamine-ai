package ao.ai.model.common.data;

import java.io.Serializable;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 1:01:59 PM
 */
public interface NumCatList
        extends Serializable
{
    //-------------------------------------------------------------------------
    public double  getNum (int index);
    public String  getEnum(int index);
    public boolean isNum  (int index);


    //-------------------------------------------------------------------------
    public int size();
}

