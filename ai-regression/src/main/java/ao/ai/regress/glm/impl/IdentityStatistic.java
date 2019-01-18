package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.SufficientStatistic;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 12:28:29 AM
 */
public class IdentityStatistic implements SufficientStatistic
{
    //-------------------------------------------------------------------------
    @Override
    public double value(double y)
    {
        return y;
    }
}
