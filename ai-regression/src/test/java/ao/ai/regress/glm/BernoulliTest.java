package ao.ai.regress.glm;

import ao.ai.regress.glm.impl.BernoulliNatural;
import ao.ai.regress.glm.impl.BernoulliNorm;
import ao.ai.regress.glm.impl.IdentityStatistic;
import ao.ai.regress.glm.impl.UnitFactor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 1:18:43 AM
 */
public class BernoulliTest
{
    //-------------------------------------------------------------------------
    private static final double TOLERANCE = 0.000000001;


    //-------------------------------------------------------------------------
    @Test
    public void randomTest()
    {
        for (double p = 0; p <= 1.0; p += 0.01)
        {
            BernoulliDistribution bernoulli =
                    new BernoulliDistribution( p );

            ExponentialFamily exponential =
                    new ExponentialFamily(
                            new BernoulliNatural( p ),
                            new IdentityStatistic(),
                            new BernoulliNorm( p ),
                            new UnitFactor());

            Assert.assertEquals(
                    exponential.value( 0 ),
                    bernoulli.value( 0 ),
                    TOLERANCE,
                    "Bernoulli matches at zero: " + p);

            Assert.assertEquals(
                    exponential.value( 1 ),
                    bernoulli.value( 1 ),
                    TOLERANCE,
                    "Bernoulli matches at one: " + p);
        }
    }
}
