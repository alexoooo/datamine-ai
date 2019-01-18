package ao.ai.cluster.trial;

import ao.ai.cluster.analysis.ClusterAnalysis;
import ao.ai.cluster.error.ObjectiveMeasure;
import ao.ai.cluster.space.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: alex
 * Date: 1-Jul-2009
 * Time: 3:58:40 PM
 */
public class ParallelTrial<T> implements ClusteringTrial<T>
{
    //--------------------------------------------------------------------
    private final ClusterAnalysis <T> ANALYZER;
    private final ObjectiveMeasure<T> ERROR;
    private final int                 TRIALS;

    private final ExecutorService     EXEC =
            Executors.newFixedThreadPool(Math.max(1,
                    Runtime.getRuntime().availableProcessors()));
//            Executors.newCachedThreadPool();


    //--------------------------------------------------------------------
    public ParallelTrial(
            ClusterAnalysis <T> analyzer,
            ObjectiveMeasure<T> error,
            int                 nTrials)
    {
        ANALYZER = analyzer;
        ERROR    = error;
        TRIALS   = nTrials;
    }


    //--------------------------------------------------------------------
    public Clustering cluster(
            final Domain<T> items, final int nClusters)
    {
        final double leastError        []   = {Double.POSITIVE_INFINITY};
        final int    leastErrorClusters[][] = {null};
//        final int    leastErrorTrial   []   = {-1};

        final int    finishedTrials[] = {0};
        final Object synch         [] = new Object[0];

        List<Callable<Void>> trials =
                new ArrayList<Callable<Void>>(TRIALS);
        for (int trial = 0; trial < TRIALS; trial++)
        {
            trials.add(new Callable<Void>() {
                public Void call() {
                    int    clusters[] =
                            ANALYZER.cluster(items, nClusters);
                    double error      =
                            ERROR.error(items, clusters, nClusters);

                    synchronized (synch)
                    {
                        if (error < leastError[0])
                        {
                            leastError[0]         = error;
                            leastErrorClusters[0] = clusters;
//                            leastErrorTrial[0]    = finishedTrials[0];
                        }
                        finishedTrials[0]++;
                    }
                    return null;
                }
            });
        }

        try {
            EXEC.invokeAll(trials);
        } catch (InterruptedException e) {
            throw new Error( e );
        }

        return new Clustering(leastErrorClusters[0], leastError[0]);
    }


    //--------------------------------------------------------------------
    public void close()
    {
        EXEC.shutdown();
    }
}
