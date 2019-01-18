package ao.ai.cluster.error;

import ao.ai.cluster.space.Domain;

/**
 * User: alex
 * Date: 2-Jun-2009
 * Time: 9:21:21 PM
 *
 * Within Cluster Sum of Squares
 */
public class TwoPassWcss<T> implements ObjectiveMeasure<T>
{
    //--------------------------------------------------------------------
    public double error(
            Domain<T> items, int[] clustering, int nClusters)
    {
        T centralTendencies[] =
                centralTendencies(items, clustering, nClusters);

        double wcss = 0;
        for (int i = 0; i < clustering.length; i++)
        {
            double err = items.distanceBetween(
                    centralTendencies[ clustering[i] ], i);
            wcss += err * err * items.represents(i);
        }
        return wcss;
    }

    @SuppressWarnings("unchecked")
    private T[] centralTendencies(
            Domain<T> items, int[] clustering, int nClusters)
    {
        T centralTendencies[] = (T[]) new Object[nClusters];
        for (int i = 0; i < nClusters; i++)
        {
            centralTendencies[i] = items.newCentroid();
        }

        for (int i = 0; i < clustering.length; i++)
        {
            items.mergeAll(
                    centralTendencies[ clustering[i] ], i);
        }
        return centralTendencies;
    }
}
