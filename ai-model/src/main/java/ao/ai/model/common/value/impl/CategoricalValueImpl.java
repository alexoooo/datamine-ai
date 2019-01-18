package ao.ai.model.common.value.impl;

import ao.ai.model.common.value.CategoricalValue;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 8:44:33 AM
 */
public class CategoricalValueImpl
        implements CategoricalValue
{
    //--------------------------------------------------------------------
    private final int    value;
    private final String valueName;


    //--------------------------------------------------------------------
    public CategoricalValueImpl(
            int categoricalValue)
    {
        this(categoricalValue, null);
    }

    public CategoricalValueImpl(
            int    categoricalValue,
            String categoricalValueName)
    {
        value     = categoricalValue;
        valueName = categoricalValueName;
    }


    //--------------------------------------------------------------------
    @Override
    public int getCategory()
    {
        return value;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return valueName == null
               ? String.valueOf( value )
               : valueName;
    }
}
