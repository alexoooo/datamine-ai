package ao.ai.cluster.space.measure;

/**
 * User: alex
 * Date: 7-Jul-2009
 * Time: 7:24:07 PM
 */
public interface Centroid<T>
{
    //--------------------------------------------------------------------
    public void merge(T value);
    public void merge(T value, double valueCount);


    //--------------------------------------------------------------------
    public double orthogonalDistance(T from, T to);
    public double distance(T toValue);
}
