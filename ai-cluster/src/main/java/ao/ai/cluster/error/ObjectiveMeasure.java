package ao.ai.cluster.error;

import ao.ai.cluster.space.Domain;

/**
 * User: alex
 * Date: 2-Jun-2009
 * Time: 9:18:16 PM
 */
public interface ObjectiveMeasure<T>
{
    //--------------------------------------------------------------------
    public double error(
            Domain<T> items, int[] clustering, int nClusters);
}
