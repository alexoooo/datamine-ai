package ao.ai.classify.online.filter;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:55 PM
 */
public class VarianceRealFilter
        implements RealFilter
{
    //------------------------------------------------------------------------
    private final double standardDeviations;

    private int learnCount;
    private double mean;
    private double m2;

    private boolean isFixated;


    //------------------------------------------------------------------------
    public VarianceRealFilter(double atOrAboveStandardDeviation)
    {
        this.standardDeviations = atOrAboveStandardDeviation;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean accept(double value)
    {
//        return value >= greaterThanOrEqualTo();

        double variance = m2 / learnCount;
        double standardDeviation = Math.sqrt( variance );

        double deviation = (value - mean) / standardDeviation;
        return standardDeviations >= deviation;
    }

    @Override
    public void learn(double value)
    {
        if (isFixated) {
            return;
        }

        learnCount++;

        double delta = value - mean;
        mean += delta / learnCount;
        m2 += delta *(value - mean);
    }


    //------------------------------------------------------------------------
    public double mean()
    {
        return mean;
    }

    public RealFilter fixate()
    {
//        return new ConstantRealFilter(
//                greaterThanOrEqualTo());

        isFixated = true;
        return this;
    }

//    private double greaterThanOrEqualTo()
//    {
//        return (1 + standardDeviations) * mean;
//    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return mean + " ev " +
               standardDeviations + " sd " +
               (isFixated ? "fixated" : "floating");
//        return "[" + standardDeviations + " sd]";
    }
}
