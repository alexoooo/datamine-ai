package ao.ai.cluster.space.measure.vector;

import ao.ai.cluster.space.measure.Centroid;
import ao.util.misc.Factory;
import org.apache.commons.math.DimensionMismatchException;
import org.apache.commons.math.stat.descriptive.moment.VectorialCovariance;

/**
 * User: alex
 * Date: 6-Jun-2009
 * Time: 5:22:57 PM
 *
 * See http://en.wikipedia.org/wiki/Mahalanobis_distance
 */
public class Mahalanobis implements Centroid<double[]>
{
    //--------------------------------------------------------------------
    public static Factory<Mahalanobis>
            newFactory(final int dimensions)
    {
        return new Factory<Mahalanobis>() {
            public Mahalanobis newInstance() {
                return new Mahalanobis(
                        dimensions);
            }
        };
    }


    //--------------------------------------------------------------------
    private final double              sums[];
    private       double              count;
    private final VectorialCovariance covariance;
    private  MahalanobisModel modelCache;


    //--------------------------------------------------------------------
    public Mahalanobis(int dimentions)
    {
        sums       = new double[dimentions];
        covariance = new VectorialCovariance(dimentions, false);
        modelCache = null;
    }


    //--------------------------------------------------------------------
    public void merge(double values[], double valueCount)
    {
        for (int i = 0; i < valueCount; i++)
        {
            merge(values);
        }
    }

    public void merge(double values[])
    {
        modelCache = null;

        try {
            covariance.increment(values);
        } catch (DimensionMismatchException e) {
            throw new Error( e );
        }

        for (int i = 0; i < values.length; i++)
        {
            sums[ i ] += values[ i ];
        }
        count++;
    }


    //--------------------------------------------------------------------
    public double orthogonalDistance(
            double[] from, double[] to)
    {
        return VectorEuclidean.delta(from, to);
    }

    public double distance(double to[])
    {
        if (modelCache == null)
        {
            modelCache = new MahalanobisModel(
                    covariance, sums, count);
        }

        return modelCache.distance(to);
    }
}
