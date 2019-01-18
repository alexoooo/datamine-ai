package ao.ai.model.common.value.impl;

import ao.ai.model.common.value.NumericalValue;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 8:53:53 PM
 */
public class NumericalValueImpl
        implements NumericalValue
{
    //--------------------------------------------------------------------
    private final double value;


    //--------------------------------------------------------------------
    public NumericalValueImpl(double rationalValue)
    {
        value = rationalValue;
    }


    //--------------------------------------------------------------------
    @Override
    public double getNum()
    {
        return value;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return String.valueOf( value );
    }
}
