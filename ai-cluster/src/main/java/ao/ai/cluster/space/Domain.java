package ao.ai.cluster.space;

import java.io.Serializable;

/**
 * User: alex
 * Date: 2-Jun-2009
 * Time: 7:53:01 PM
 */
public interface Domain<T> extends Serializable
{
    //--------------------------------------------------------------------
    public int locationCount();


    //--------------------------------------------------------------------
    // double return because it could have been scaled
    public double represents(int locationAt);


    //--------------------------------------------------------------------
    public double distanceBetween(
            int locationIndexA,
            int locationIndexB);


    //--------------------------------------------------------------------
    public T newCentroid();

    // same as create new, then mergeAll
    //public T newCentroid(int ofLocationAt);


    //--------------------------------------------------------------------
    // takes into account represents(withLocationAt)
    public void mergeAll(T centroid, int withLocationAt);


    //--------------------------------------------------------------------
    // positive scalar distance between the expectation
    //  of the central tendancy and the value at locationAt
    public double distanceBetween(
            T centroid, int locationAt);


    //--------------------------------------------------------------------
    /**
     * attempts to scale the values to improve numerical stability.
     */
    public void normalize();
}
