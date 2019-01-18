package ao.ai.classify.online.filter;

import ao.util.math.rand.Rand;

/**
 * User: AO
 * Date: 12/17/10
 * Time: 6:27 PM
 */
public class FixatingFilter
        implements RealFilter
{
    //------------------------------------------------------------------------
    private RealFilter delegate;

    private boolean isFixed;
    private double  prevMean;
    private int learnCount;

//    private double min;
//    private double max;
//    private int inRangeCount;
//    private int totalCount;


    //------------------------------------------------------------------------
    public FixatingFilter()
    {
        delegate = new VarianceRealFilter(
                Rand.nextGaussian());
    }


    //------------------------------------------------------------------------
    @Override
    public boolean accept(double value)
    {
        return delegate.accept( value );
    }

    @Override
    public void learn(double value)
    {
        if (isFixed) {
            return;
        }

        delegate.learn( value );

        learnCount++;

        VarianceRealFilter filter = (VarianceRealFilter) delegate;
        double currMean = filter.mean();

        if (learnCount > 1)
        {
            double meanDelta = prevMean - currMean;
            double percentDelta = Math.abs(meanDelta / currMean);
            if (percentDelta < 0.01)
            {
                isFixed = true;
                delegate = filter.fixate();
            }
        }

        prevMean = currMean;
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return delegate.toString();
    }
}

