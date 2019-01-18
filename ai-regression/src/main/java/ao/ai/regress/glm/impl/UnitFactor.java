package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.Factor;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 10:44:19 AM
 */
public class UnitFactor
        implements Factor
{
    //-------------------------------------------------------------------------
    @Override
    public double value(double y)
    {
        return 1;
    }
}
