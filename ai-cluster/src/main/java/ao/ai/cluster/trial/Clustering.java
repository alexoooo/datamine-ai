package ao.ai.cluster.trial;

/**
 * User: alex
 * Date: 1-Jul-2009
 * Time: 3:59:11 PM
 */
public class Clustering
{
    //--------------------------------------------------------------------
    private final int    CLUSTERS[];
    private final double ERROR;


    //--------------------------------------------------------------------
    public Clustering(int clusters[], double error)
    {
        CLUSTERS = clusters;
        ERROR    = error;
    }


    //--------------------------------------------------------------------
    public int cluster(int index)
    {
        return CLUSTERS[ index ];
    }
    
    public int[] clusters()
    {
        return CLUSTERS;
    }

    public double error()
    {
        return ERROR;
    }
}
