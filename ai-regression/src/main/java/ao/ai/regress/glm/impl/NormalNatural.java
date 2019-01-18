package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.NaturalParameter;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 11:58:23 AM
 */
public class NormalNatural
        implements NaturalParameter
{
    //-------------------------------------------------------------------------
    private final double mean;
    private final double knownVariance;


    //-------------------------------------------------------------------------
    public NormalNatural(double mean)
    {
        this(mean, 1.0);
    }

    public NormalNatural(
            double mean,
            double varianceSquared)
    {
        this.mean          = mean;
        this.knownVariance = Math.sqrt( varianceSquared );
    }


    //-------------------------------------------------------------------------
    @Override
    public double value(double y)
    {
        return mean / knownVariance;
    }
}
