package ao.ai.cluster.space.impl;

import ao.ai.cluster.space.measure.ScalarCentroid;
import ao.util.data.primitive.DoubleList;
import ao.util.misc.Factory;

/**
 * User: alex
 * Date: 30-Jun-2009
 * Time: 8:14:11 PM
 */
public class ScalarDomain<T extends ScalarCentroid>
        extends ao.ai.cluster.space.impl.MultiDomain<T>
{
    //--------------------------------------------------------------------
    private final DoubleList vals;
    private final Factory<T> centroids;
    private final T          ruler;


    //--------------------------------------------------------------------
    public ScalarDomain(Factory<T> centroidFactory)
    {
        vals      = new DoubleList();
        centroids = centroidFactory;
        ruler     = centroids.newInstance();
    }


    //--------------------------------------------------------------------
    public void add(double value, double count)
    {
        vals .add( value );
        super.add( count );
    }


    //--------------------------------------------------------------------
    public double distanceBetween(
            int locationIndexA, int locationIndexB)
    {
//        return Math.abs(vals.get(locationIndexA) -
//                        vals.get(locationIndexB));

//        T centroid = newCentroid();
//        centroid.merge(vals.get(locationIndexA));
//        return centroid.distance(
//                vals.get(locationIndexB));
        
        return ruler.orthogonalDistance(
                vals.get(locationIndexA),
                vals.get(locationIndexB));
    }

    public double distanceBetween(
            T centralTendancy, int locationAt)
    {
        return centralTendancy.distance(
                vals.get(locationAt));
    }


    //--------------------------------------------------------------------
    public T newCentroid()
    {
        return centroids.newInstance();
    }


    //--------------------------------------------------------------------
    public void mergeAll(
            T centralTendancy, int withLocationAt)
    {
        centralTendancy.merge(
                vals  .get(withLocationAt),
                represents(withLocationAt));
    }


    //--------------------------------------------------------------------
    public void normalize()
    {
        DomainUtils.normalize(vals);
        super      .normalize();
    }
}
