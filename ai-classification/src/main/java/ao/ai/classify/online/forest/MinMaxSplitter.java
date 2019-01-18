package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;
import ao.util.math.rand.Rand;
import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: 12/27/10
 * Time: 9:39 PM
 */
/*package-private*/ class MinMaxSplitter implements Splitter
{
    //------------------------------------------------------------------------
    private final int index;

    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;


    //------------------------------------------------------------------------
    public MinMaxSplitter(int index)
    {
        this.index = index;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean isSingular()
    {
        return Double.isInfinite( min ) ||
               min == max;
    }


    //------------------------------------------------------------------------
    @Override
    public void consider(RealList input, MultiClass output)
    {
        double value = input.get( index );

        min = Math.min(value, min);
        max = Math.max(value, max);
    }


    //------------------------------------------------------------------------
    @Override
    public Split split()
    {
        Preconditions.checkState(
                ! Double.isInfinite( min ));

        double split = Rand.nextDouble(min, max);
//        double split = (min + max) / 2;

        return new Split(new SplitCondition(
                index, split));
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "[" + min + ", " + max + "](" + index+ ")";
    }
}
