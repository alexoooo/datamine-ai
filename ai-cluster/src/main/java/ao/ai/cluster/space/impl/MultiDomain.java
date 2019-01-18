package ao.ai.cluster.space.impl;

import ao.ai.cluster.space.Domain;
import ao.util.data.primitive.DoubleList;

/**
 * User: alex
 * Date: 5-Jul-2009
 * Time: 7:12:57 PM
 */
public abstract class MultiDomain<T> implements Domain<T>
{
    //--------------------------------------------------------------------
    private final DoubleList counts;


    //--------------------------------------------------------------------
    public MultiDomain()
    {
        counts = new DoubleList();
    }


    //--------------------------------------------------------------------
    public void add(double count)
    {
        counts.add( count  );
    }


    //--------------------------------------------------------------------
    public int locationCount()
    {
        return counts.size();
    }


    //--------------------------------------------------------------------
    public double represents(int locationAt)
    {
        return counts.get(locationAt);
    }


    //--------------------------------------------------------------------
    public void normalize()
    {
        DomainUtils.normalize(counts);
    }
}
