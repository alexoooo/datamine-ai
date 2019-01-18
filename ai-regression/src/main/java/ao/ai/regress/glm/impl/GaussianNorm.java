package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.NormalizingConstant;
import ao.util.math.Calc;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 12:16:34 PM
 */
public class GaussianNorm
        implements NormalizingConstant
{
    //-------------------------------------------------------------------------
    private final double meanSquared;
    private final double knownVarianceSquared;


    //-------------------------------------------------------------------------
    public GaussianNorm(double mean)
    {
        this(mean, 1.0);
    }

    public GaussianNorm(
            double mean,
            double knownVarianceSquared)
    {
        this.meanSquared          = Calc.square( mean );
        this.knownVarianceSquared = knownVarianceSquared;
    }

    
    //-------------------------------------------------------------------------
    @Override
    public double value()
    {
        return meanSquared / (2.0 * knownVarianceSquared);
    }
}
