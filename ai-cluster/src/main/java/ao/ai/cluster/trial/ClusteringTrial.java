package ao.ai.cluster.trial;

import ao.ai.cluster.space.Domain;

/**
 * User: alex
 * Date: 1-Jul-2009
 * Time: 4:34:02 PM
 */
public interface ClusteringTrial<T>
{
    public Clustering cluster(
            Domain<T> items, int nClusters);

    public void close();
}
