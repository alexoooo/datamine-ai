package ao.ai.cluster.space.measure.scalar;

import ao.ai.cluster.space.measure.ScalarCentroid;
import ao.util.misc.Factory;

/**
 * User: alex
 * Date: 30-Jun-2009
 * Time: 6:21:11 PM
 */
public class MeanEuclidean implements ScalarCentroid
{
    //--------------------------------------------------------------------
    public static Factory<MeanEuclidean> newFactory()
    {
        return new Factory<MeanEuclidean>() {
            public MeanEuclidean newInstance() {
                return new MeanEuclidean();
            }
        };
    }


    //--------------------------------------------------------------------
    private double total;
    private double count;


    //--------------------------------------------------------------------
    public MeanEuclidean() {}


    //--------------------------------------------------------------------
    public void merge(double value)
    {
        merge(value, 1);
    }

    public void merge(double value, double valueCount)
    {
        total += value * valueCount;
        count += valueCount;
    }


    //--------------------------------------------------------------------
    public double orthogonalDistance(double from, double to) {
        return Math.abs(from - to);
    }


    //--------------------------------------------------------------------
    public double distance(double ofValue)
    {
        return Math.abs(ofValue - expectation());
    }

    public double expectation()
    {
        return total / count;
    }


    //--------------------------------------------------------------------
    @Override public String toString()
    {
        return String.valueOf( expectation() );
    }
}
