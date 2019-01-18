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
/*package-private*/ class MinMaxMemSplitter implements Splitter
{
    //------------------------------------------------------------------------
    private final int index;

    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;

    private MultiClass minClass;
    private MultiClass maxClass;


    //------------------------------------------------------------------------
    public MinMaxMemSplitter(int index)
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

        if (value < min)
        {
            min     = value;
            minClass = output;
        }

        if (value > max)
        {
            max     = value;
            maxClass = output;
        }
    }


    //------------------------------------------------------------------------
    @Override
    public Split split()
    {
        Preconditions.checkState(
                ! Double.isInfinite( min ));

        double split = Rand.nextDouble(min, max);

        SplitCondition condition =
                new SplitCondition(index, split);

        Sample leftSample  = new Sample();
        Sample rightSample = new Sample();

        Sample minSample =
                (condition.isLeft( min ) ? leftSample : rightSample);
        Sample maxSample =
                (condition.isLeft( max ) ? leftSample : rightSample);

        if (minSample == maxSample)
        {
            leftSample .learn( minClass );
            rightSample.learn( maxClass );

            if (Rand.nextBoolean()) {
                leftSample  = leftSample .asBias();
            } else {
                rightSample = rightSample.asBias();
            }
        }
        else
        {
            minSample.learn( minClass );
            maxSample.learn( maxClass );
        }

        return new Split(
                condition, leftSample, rightSample);
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "[" + min + ", " + max + "](" + index+ ")";
    }
}
