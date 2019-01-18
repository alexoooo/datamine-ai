package ao.ai.cluster.analysis;

import ao.ai.cluster.space.Domain;

/**
 * User: alex
 * Date: 2-Jun-2009
 * Time: 7:52:14 PM
 */
public interface ClusterAnalysis<T>
{
    /**
     * @param items data to cluster
     * @param nClusters number of clusters to use
     * @return clustering, could be different for each call
     */
    public int[] cluster(Domain<T> items, int nClusters);
}
