package ao.ai.classify.online.forest;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * User: AO
 * Date: 12/27/10
 * Time: 10:17 PM
 */
public class Param
{
    //------------------------------------------------------------------------
    private final int    fixateAtLearnCount;
    private final int    splitAtLearnCount;
    private final int    splitterCount;
    private final double minSplitGain;


    //------------------------------------------------------------------------
    public Param(
            int    fixateAtLearnCount,
            int    splitAtLearnCount,
            int    splitterCount,
            double minSplitGain)
    {
        checkArgument(splitAtLearnCount >= 0);
        checkArgument(splitterCount > 0);

        this.fixateAtLearnCount = fixateAtLearnCount;
        this.splitAtLearnCount  = splitAtLearnCount;
        this.splitterCount      = splitterCount;
        this.minSplitGain       = minSplitGain;
    }


    //------------------------------------------------------------------------
    public int fixateAtLearnCount()
    {
        return fixateAtLearnCount;
    }

    public int splitAtLearnCount()
    {
        return splitAtLearnCount;
    }

    public int splitterCount()
    {
        return splitterCount;
    }

    public double minSplitGain()
    {
        return minSplitGain;
    }


    //------------------------------------------------------------------------
    public Param withSplitAtLearnCount(
            int newSplitAtLearnCount)
    {
        return new Param(
                fixateAtLearnCount,
                newSplitAtLearnCount,
                splitterCount,
                minSplitGain);
    }
}
