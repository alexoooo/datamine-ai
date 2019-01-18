package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.NormalizingConstant;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 1:07:56 AM
 */
public class BernoulliNorm implements NormalizingConstant
{
    //-------------------------------------------------------------------------
    private final double theta;


    //-------------------------------------------------------------------------
    public BernoulliNorm(double theta)
    {
        this.theta = theta;
    }


    //-------------------------------------------------------------------------
    @Override
    public double value()
    {
        return - Math.log( 1.0 - theta );
    }
}
