package ao.ai.regress.glm;

import ao.util.math.Calc;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 12:04:56 PM
 */
public class GaussianDistribution
{
    //-------------------------------------------------------------------------
    private final double mean;
    private final double variance;


    //-------------------------------------------------------------------------
    public GaussianDistribution()
    {
        this( 0 );
    }

    public GaussianDistribution(double mean)
    {
        this(mean, 1.0);
    }

    public GaussianDistribution(
            double mean, double variance)
    {
        this.mean     = mean;
        this.variance = variance;
    }


    //-------------------------------------------------------------------------
    public double value(double x)
    {
        return 1.0 / Math.sqrt(2.0 * Math.PI * variance)
                * Math.exp( - Calc.square(x - mean) / (2 * variance) );
    }
}
