package ao.ai.cluster.space.impl;

import ao.ai.cluster.space.measure.Centroid;
import ao.util.misc.Equalizer;
import ao.util.misc.Equalizers;
import ao.util.misc.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alex
 * Date: 5-Jul-2009
 * Time: 6:38:12 PM
 */
public class CentroidDomain<C extends Centroid<T>, T>
        extends ao.ai.cluster.space.impl.MultiDomain<C>
{
    //--------------------------------------------------------------------
    private final Equalizer<T>         equalizer;
    private final C                    ruler;
    private final Factory<? extends C> centroids;
    private final List   <T>           vals;


    //--------------------------------------------------------------------
    public CentroidDomain(
            Factory<? extends C> centroidFactory)
    {
        this(centroidFactory, Equalizers.<T>equals());
    }
    public CentroidDomain(
            Factory<? extends C> centroidFactory,
            Equalizer<T>         valueEqualizer)
    {
        vals      = new ArrayList<T>();
        centroids = centroidFactory;
        ruler     = centroids.newInstance();
        equalizer = valueEqualizer;

//        histIndex = new AutovivifiedMap<DoubleList, Integer>(
//                        new Factory<Integer>() {
//                            private int next = 0;
//                            public Integer newInstance() {
//                                return next++;
//                            }});
    }


    //--------------------------------------------------------------------
    public int indexOf(T value)
    {
        for (int i = 0; i < vals.size(); i++)
        {
            if (equalizer.equal(value, vals.get(i))) return i;
        }
        return -1;
    }


    //--------------------------------------------------------------------
    public int size()
    {
        return vals.size();
    }

    public T get(int index)
    {
        return vals.get( index );
    }


    //--------------------------------------------------------------------
    public void add(T value, int count)
    {
        vals .add( value );
        super.add( count );

//        histIndex.get()
    }


    //--------------------------------------------------------------------
    public double distanceBetween(
            int locationIndexA, int locationIndexB)
    {
        return ruler.orthogonalDistance(
                vals.get(locationIndexA),
                vals.get(locationIndexB));
    }


    //--------------------------------------------------------------------
    public C newCentroid()
    {
        return centroids.newInstance();
    }


    //--------------------------------------------------------------------
    public void mergeAll(
            C   centralTendancy,
            int withLocationAt)
    {
        centralTendancy.merge(
                vals  .get(withLocationAt),
                represents(withLocationAt));
    }


    //--------------------------------------------------------------------
    public double distanceBetween(
            C   centralTendancy,
            int locationAt)
    {
        return centralTendancy.distance(
                vals.get(locationAt));
    }


    //--------------------------------------------------------------------
    public void normalize()
    {
        super.normalize();
    }
}
