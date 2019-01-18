package ao.ai.regress.glm.impl;

import ao.ai.regress.glm.def.Factor;
import ao.util.math.Calc;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 10:32:21 AM
 */
public class NormalFactor implements Factor
{
    //-------------------------------------------------------------------------
    private static final double ROOT_TWO_PI =
            Math.sqrt(2.0 * Math.PI);


    //-------------------------------------------------------------------------
    private final double knownVarianceAbs;
    private final double knownVarianceSquared;
//    private final double mean;


    //-------------------------------------------------------------------------
    public NormalFactor()
    {
        this( 1.0 );
    }

    public NormalFactor(double knownVarianceSquared)
    {
//        this.mean = mean;

        this.knownVarianceAbs     = Math.sqrt( knownVarianceSquared );
        this.knownVarianceSquared = knownVarianceSquared;
    }


    //-------------------------------------------------------------------------
    @Override
    public double value(double y)
    {
        return Math.exp( -Calc.square( y ) /
                            (2.0 * knownVarianceSquared))
                / (ROOT_TWO_PI * knownVarianceAbs);


//        return 1.0 / Math.sqrt(2 * Math.PI) *
//               Math.exp(- 0.5 * Calc.square(y));
    }
}
