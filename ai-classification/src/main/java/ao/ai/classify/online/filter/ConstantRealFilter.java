package ao.ai.classify.online.filter;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:37 PM
 */
public class ConstantRealFilter implements RealFilter
{
    //------------------------------------------------------------------------
    private final double greaterOrEqualTo;


    //------------------------------------------------------------------------
    public ConstantRealFilter(double greaterOrEqualTo)
    {
        this.greaterOrEqualTo = greaterOrEqualTo;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean accept(double value)
    {
        return value >= greaterOrEqualTo;
    }

    @Override
    public void learn(double value)
    {
        // doesn't learn
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return ">= " + greaterOrEqualTo;
    }
}
