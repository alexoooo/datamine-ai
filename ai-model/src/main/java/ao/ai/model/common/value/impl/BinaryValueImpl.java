package ao.ai.model.common.value.impl;

import ao.ai.model.common.value.ext.BinaryValue;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 8:47:20 AM
 */
public enum BinaryValueImpl
        implements BinaryValue
{
    //--------------------------------------------------------------------
    TRUE (true),
    FALSE(false);

    private final static BinaryValue[] VALUES = values();

    public static BinaryValue valueOf(boolean value)
    {
        return value ? TRUE : FALSE;
    }


    //--------------------------------------------------------------------
    private final boolean value;


    //--------------------------------------------------------------------
    private BinaryValueImpl(boolean booleanValue)
    {
        value = booleanValue;
    }


    //--------------------------------------------------------------------
    @Override
    public boolean getBin()
    {
        return value;
    }

    @Override
    public int getCategory()
    {
        return value ? 1 : 0;
    }


    //--------------------------------------------------------------------
    public BinaryValue invert()
    {
        return value ? FALSE : TRUE;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return value
               ? "Positive"
               : "Negative";
    }
}
