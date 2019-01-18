package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;

/**
 * User: AO
 * Date: 12/27/10
 * Time: 9:35 PM
 */
/*package-private*/ class SplitCondition
{
    //------------------------------------------------------------------------
    private final int    index;
    private final double pivot;


    //------------------------------------------------------------------------
    public SplitCondition(
            int index, double pivot)
    {
        this.index = index;
        this.pivot = pivot;
    }


    //------------------------------------------------------------------------
    public boolean isLeft(double input)
    {
        return input >= pivot;
    }
    public boolean isLeft(RealList input)
    {
        return input.get( index ) >= pivot;
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "[" + index + "] >= " + pivot;
    }
}
