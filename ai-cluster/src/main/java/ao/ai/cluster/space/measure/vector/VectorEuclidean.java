package ao.ai.cluster.space.measure.vector;

import ao.ai.cluster.space.measure.Centroid;
import ao.util.misc.Factory;

/**
 * User: alex
 * Date: 7-Jul-2009
 * Time: 8:05:09 PM
 */
public class VectorEuclidean implements Centroid<double[]>
{
    //--------------------------------------------------------------------
    public static Factory<VectorEuclidean>
            newFactory(final int dimensions)
    {
        return new Factory<VectorEuclidean>() {
            public VectorEuclidean newInstance() {
                return new VectorEuclidean(
                        dimensions);
            }
        };
    }


    //--------------------------------------------------------------------
    private final double sums[];
    private       double count;


    //--------------------------------------------------------------------
    public VectorEuclidean(int dimentions)
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
        count += valueCount;
    }

    public void merge(double values[])
    {
        merge(values, 1);
    }


    //--------------------------------------------------------------------
    public double orthogonalDistance(
            double[] from, double[] to)
    {
        return delta(from, to);
    }

    public static double delta(
            double[] from, double[] to)
    {
        assert from.length == to.length
               : "unequal lengths: " + from.length + "\t" + to.length;

        double sumOfSquares = 0;
        for (int i = 0; i < from.length; i++)
        {
            double delta = (from[i] - to[i]);
            sumOfSquares += delta * delta;
        }
        return Math.sqrt(sumOfSquares);
    }


    //--------------------------------------------------------------------
    public double distance(double to[])
    {
        double sumOfSquares = 0;

        for (int i = 0; i < sums.length; i++)
        {
            double avg   = sums[i]/count;
            double delta = avg - to[i];
            sumOfSquares += delta * delta;
        }

        return Math.sqrt(sumOfSquares);
    }

    public static double distance(
            double means[], double to[])
    {
        double sumOfSquares = 0;

        for (int i = 0; i < means.length; i++)
        {
            double delta  = means[i] - to[i];
            sumOfSquares += delta * delta;
        }

        return Math.sqrt(sumOfSquares);
    }
}
