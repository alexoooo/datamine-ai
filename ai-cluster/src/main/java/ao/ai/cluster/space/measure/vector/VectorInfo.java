package ao.ai.cluster.space.measure.vector;

import ao.ai.cluster.space.measure.Centroid;
import ao.util.math.stats.Info;
import ao.util.misc.Factory;

/**
 * User: alex
 * Date: 20-Jul-2009
 * Time: 6:02:02 PM
 */
public class VectorInfo implements Centroid<double[]>
{
    //--------------------------------------------------------------------
    public static Factory<VectorInfo>
            newFactory(final int dimensions)
    {
        return new Factory<VectorInfo>() {
            public VectorInfo newInstance() {
                return new VectorInfo(
                        dimensions);
            }
        };
    }


    //--------------------------------------------------------------------
    private final double sums[];
    private       double avgNorm[] = null;


    //--------------------------------------------------------------------
    public VectorInfo(int dimentions)
    {
        sums = new double[dimentions];
    }


    //--------------------------------------------------------------------
    public void merge(double values[], double valueCount)
    {
        for (int i = 0; i < values.length; i++)
        {
            sums[ i ] += values[ i ] * valueCount;
        }
        avgNorm = null;
    }

    public void merge(double values[])
    {
        merge(values, 1);
    }


    //--------------------------------------------------------------------
    public double orthogonalDistance(
            double from[], double to[])
    {
        return delta(from, to);
    }

    public static double delta(
            double from[], double to[])
    {
        assert from.length == to.length
               : "unequal lengths: " + from.length + "\t" + to.length;

        double fromNorm[] = Info.normalize(from);
        double   toNorm[] = Info.normalize(to);

        double bitCost = 0;
        for (int i = 0; i < from.length; i++)
        {
            double delta = Math.abs(fromNorm[i] - toNorm[i]);
            bitCost +=
                    (delta == 1)
                    ? 1000 * 1000 * 1000
                    : Info.cost(1.0 - delta);
        }
        return bitCost;
    }


    //--------------------------------------------------------------------
    public double distance(double to[])
    {
        if (avgNorm == null)
        {
            avgNorm = Info.normalize( sums );
        }

        double toNorm[] = Info.normalize( to   );
        double bitCost  = 0;
        for (int i = 0; i < sums.length; i++)
        {
            double delta = Math.abs(avgNorm[i] - toNorm[i]);
            bitCost +=
                    (delta == 1)
                    ? 1000 * 1000 * 1000
                    : Info.cost(1.0 - delta);
        }
        return bitCost;
    }
}
