package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.NaturalParameter;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 12:50:40 AM
 */
public class BernoulliNatural implements NaturalParameter
{
    //-------------------------------------------------------------------------
    private double theta;


    //-------------------------------------------------------------------------
    public BernoulliNatural(
            double theta)
    {
        this.theta = theta;
    }

    
    //-------------------------------------------------------------------------
    @Override
    public double value(double y)
    {
        return Math.log( theta / (1.0 - theta) );
    }
}
