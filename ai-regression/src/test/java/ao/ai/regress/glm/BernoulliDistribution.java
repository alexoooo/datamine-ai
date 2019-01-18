package ao.ai.regress.glm;

/**
 * User: alex
 * Date: 27-Jun-2010
 * Time: 1:15:07 AM
 */
public class BernoulliDistribution
{
    //-------------------------------------------------------------------------
    private final double probability;


    //-------------------------------------------------------------------------
    public BernoulliDistribution(double probability)
    {
        this.probability = probability;
    }


    //-------------------------------------------------------------------------
    public double value(double k)
    {
        assert k == 0 || k == 1 : "k elementOf {0, 1}: " + k;

        return Math.pow(      probability,       k) *
               Math.pow(1.0 - probability, 1.0 - k);
    }
}
