package ao.ai.cluster.trial;

import ao.ai.cluster.analysis.ClusterAnalysis;
import ao.ai.cluster.error.ObjectiveMeasure;
import ao.ai.cluster.space.Domain;
import org.apache.log4j.Logger;


/**
 * User: alex
 * Date: 1-Jul-2009
 * Time: 10:05:44 AM
 */
public class SerialTrial<T> implements ClusteringTrial<T>
{
    //--------------------------------------------------------------------
    private static final Logger LOG =
            Logger.getLogger(SerialTrial.class);


    //--------------------------------------------------------------------
    private final ClusterAnalysis<T>  ANALYZER;
    private final ObjectiveMeasure<T> ERROR;
    private final int                 TRIALS;


    //--------------------------------------------------------------------
    public SerialTrial(
            ClusterAnalysis <T> analyzer,
            ObjectiveMeasure<T> error,
            int                 nTrials)
    {
        ANALYZER = analyzer;
        ERROR    = error;
        TRIALS   = nTrials;
    }


    //--------------------------------------------------------------------
    public Clustering cluster(Domain<T> items, int  nClusters)
    {
        double leastError           = Double.POSITIVE_INFINITY;
        int    leastErrorClusters[] = null;

        int leastErrorTrial = -1;
        for (int trial = 0; trial < TRIALS; trial++)
        {
            int    clusters[] = ANALYZER.cluster(items, nClusters);
            double error      = ERROR.error(items, clusters, nClusters);

            if (error < leastError)
            {
//                System.out.println(trial + "\t" + error);
                leastError         = error;
                leastErrorClusters = clusters;
                leastErrorTrial    = trial;
            }
        }

        LOG.debug("least trial: " +
                    (leastErrorTrial + 1) + "/" + nClusters);
        return new Clustering(leastErrorClusters, leastError);
    }

    
    //--------------------------------------------------------------------
    public void close() {}
}

