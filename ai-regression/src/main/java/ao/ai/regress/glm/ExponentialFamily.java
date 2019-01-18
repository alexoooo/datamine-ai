package ao.ai.regress.glm;

import ao.ai.regress.glm.def.Factor;
import ao.ai.regress.glm.def.NaturalParameter;
import ao.ai.regress.glm.def.NormalizingConstant;
import ao.ai.regress.glm.def.SufficientStatistic;
import ao.util.math.Calc;

/**
 * User: alex
 * Date: 26-Jun-2010
 * Time: 11:57:04 PM
 */
public class ExponentialFamily
{
    //-------------------------------------------------------------------------
    private final NaturalParameter    naturalParameter;
    private final SufficientStatistic sufficientStatistic;
    private final NormalizingConstant normalizingConstant;
    private final Factor              factor;


    //-------------------------------------------------------------------------
    public ExponentialFamily(
            NaturalParameter    naturalParameter,
            SufficientStatistic sufficientStatistic,
            NormalizingConstant normalizingConstant,
            Factor              factor)
    {
//        naturalParameter = new BernoulliNatural( theta );
//        normalizingConstant = new BernoulliNorm( theta );

        this.naturalParameter    = naturalParameter;
        this.sufficientStatistic = sufficientStatistic;
        this.normalizingConstant = normalizingConstant;
        this.factor              = factor;
    }


    //-------------------------------------------------------------------------
    public double value(double y)
    {
        return factor.value( y ) *
               Math.exp(
                    Calc.multiply(
                            naturalParameter.value( y ),
                            sufficientStatistic.value( y )
                    ) - normalizingConstant.value());
    }
}
