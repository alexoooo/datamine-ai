package ao.ai.cluster.space.measure;

/**
 * User: alex
 * Date: 7-Jul-2009
 * Time: 7:24:01 PM
 */
public interface ScalarCentroid
{
    //--------------------------------------------------------------------
    public void merge(double value);
    public void merge(double value, double valueCount);


    //--------------------------------------------------------------------
    public double distance(double toValue);

    
    //--------------------------------------------------------------------
    public double orthogonalDistance(double from, double to);
}
