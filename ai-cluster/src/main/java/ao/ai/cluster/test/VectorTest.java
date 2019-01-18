package ao.ai.cluster.test;

import org.apache.commons.math.DimensionMismatchException;

/**
 * User: alex
 * Date: 8-Jul-2009
 * Time: 12:19:37 AM
 */
public class VectorTest
{
    //--------------------------------------------------------------------
    public static void main(String[] args)
            throws DimensionMismatchException
    {
//        System.out.println("loading data");
//        CentroidDomain<Centroid<double[]>, double[]> data =
//                PersistentObjects.retrieve(
//                        "/home/alex/proj/datamine/input/holeDomain.obj");
//
//        System.out.println("clustering");
//        ClusteringTrial<Centroid<double[]>> analyzer =
//                new SerialTrial<Centroid<double[]>>(
//                        new KMeans<Centroid<double[]>>(),
//                        new TwoPassWcss<Centroid<double[]>>(),
//                        1);
//        Clustering clusters = analyzer.cluster(
//                                         data, (byte) 16);
//        analyzer.close();
//        System.out.println(clusters.error());

//        Mahalanobis dist = new Mahalanobis(2);
////        VectorialCovariance covariance =
////                new VectorialCovariance(2, false);
//
//        for (int i = 0; i < 100; i++)
//        {
//            dist.merge(new double[]{
//                    Rand.nextDouble( 50 * 1000 * 1000),
//                    Rand.nextDouble(100 * 1000 * 1000)},
//                    1000 * 1000);
////            covariance.increment(new double[]{
////                    Rand.nextDouble(),
////                    Rand.nextDouble()});
//        }
//
////        System.out.println(
////                covariance.getResult());
////        System.out.println(
////                covariance.getResult().inverse());
//        System.out.println(dist.distance(new double[]{
//                200.0, 1900.0
//        }));
    }
}
