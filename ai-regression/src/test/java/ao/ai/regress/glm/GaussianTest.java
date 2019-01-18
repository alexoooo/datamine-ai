package ao.ai.regress.glm;

import ao.ai.regress.glm.impl.GaussianNorm;
import ao.ai.regress.glm.impl.IdentityStatistic;
import ao.ai.regress.glm.impl.NormalFactor;
import ao.ai.regress.glm.impl.NormalNatural;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 1:18:43 AM
 */
public class GaussianTest
{
    //-------------------------------------------------------------------------
    private static final double TOLERANCE = 0.000000001;


    //-------------------------------------------------------------------------
    @Test
    public void randomTest()
    {
        for (double mean = -10; mean <= 10; mean += 0.1)
        {
            GaussianDistribution gaussian =
                    new GaussianDistribution( mean );

            ExponentialFamily exponential =
                    new ExponentialFamily(
                            new NormalNatural(mean),
                            new IdentityStatistic(),
                            new GaussianNorm(mean),
                            new NormalFactor(1.0));

            for (double x = -15; x <= 15; x += 0.1)
            {

                Assert.assertEquals(
                            exponential.value( x ),
                            gaussian.value( x ),
                            TOLERANCE,
                            "Normal deviates at: " + mean + "/" + x);
            }
        }
    }
}