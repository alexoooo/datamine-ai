package ao.ai.cluster.test;

import ao.ai.cluster.analysis.KMeans;
import ao.ai.cluster.error.TwoPassWcss;
import ao.ai.cluster.space.impl.ScalarDomain;
import ao.ai.cluster.space.measure.scalar.MeanEuclidean;
import ao.ai.cluster.trial.Clustering;
import ao.ai.cluster.trial.ClusteringTrial;
import ao.ai.cluster.trial.ParallelTrial;
import ao.util.data.Arrs;
import ao.util.math.rand.Rand;

/**
 * User: alex
 * Date: 30-Jun-2009
 * Time: 8:58:38 PM
 */
public class ClusterTest
{
    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
        Rand.randomize();

        ScalarDomain<MeanEuclidean> domain =
                new ScalarDomain<MeanEuclidean>(
                        MeanEuclidean.newFactory());

//        double data[] = {
//                1, 5, 7, 12, 15, -2,
//                100, 115, 105, 98,
//                500, 520, 530, 540, 519,
//                600,
//                750,
//                1001, 1015,
//                1200};
        double data[] = new double[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = Rand.nextDouble();
        }
        Arrs.shuffle(data);
        
        for (double n : data)
        {
            domain.add(n, 1000 * 1000 * 1000);
        }
//        domain.add(0, 1);
//        domain.add(1, 1);
//        domain.add(2, 1);
//
//        domain.add(10, 1);
//        domain.add(11, 1);
//
//        domain.add(35, 1);
//        domain.add(36, 1);

        domain.normalize();

//        for (int i = 0; i < 5000; i++) {
        int nClusters  = 169;
        ClusteringTrial<MeanEuclidean> clusterer =
                new ParallelTrial<MeanEuclidean>(new KMeans<MeanEuclidean>(),
                                        new TwoPassWcss<MeanEuclidean>(),
                                        512);
        Clustering clustering = clusterer.cluster(domain, nClusters);
        clusterer.close();

//        }
        int clusters[] = clustering.clusters();
//        System.out.println("Error: " + clustering.error());
//
        for (byte c = 0; c < nClusters; c++)
        {
            System.out.println("Cluster " + c);
            for (int i = 0; i < clusters.length; i++) {
                if (clusters[i] != c) continue;
                System.out.println("\t" + data[i]);
            }
        }
    }
}
