package ao.ai.classify.online.filter;

import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:53 PM
 */
public class PercentileRealFilter
        implements RealFilter
{
    //------------------------------------------------------------------------
    private final double percentile;


    //------------------------------------------------------------------------
    public PercentileRealFilter(double percentile)
    {
        Preconditions.checkArgument(
                0 <= percentile && percentile <= 1, percentile);

        this.percentile = percentile;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean accept(double value)
    {
        return false;
    }

    @Override
    public void learn(double value)
    {

    }
}
