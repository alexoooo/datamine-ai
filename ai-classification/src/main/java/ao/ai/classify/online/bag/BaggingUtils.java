package ao.ai.classify.online.bag;

import ao.util.math.rand.Rand;

/**
 * User: AO
 * Date: 12/28/10
 * Time: 12:10 AM
 */
public class BaggingUtils
{
    //------------------------------------------------------------------------
    private BaggingUtils() {}


    //------------------------------------------------------------------------
    public static int randomEventCount()
    {
        double remainRandom = Rand.nextDouble();
        for (int i = 0; i < 100; i++)
        {
            double prob = BaggingUtils.poisson(i);
            remainRandom -= prob;
            if (remainRandom <= 0)
            {
                return i;
            }
        }
        return 100;
    }


    //------------------------------------------------------------------------
    public static double poisson(int eventCount)
    {
        return poisson( eventCount, 1.0 );
    }

    public static double poisson(
            int eventCount, double expectedOccurrences)
    {
        return Math.pow( expectedOccurrences, eventCount) *
               Math.exp(-expectedOccurrences) /
               factorial(eventCount);
    }

    public static long factorial(int n)
    {
        long fact = 1;
        for (int i = 2; i <= n; i++)
        {
            fact *= i;
        }
        return fact;
    }
}
