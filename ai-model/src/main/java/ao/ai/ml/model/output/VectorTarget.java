package ao.ai.ml.model.output;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:12:11 PM
 */
public class VectorTarget
{
    //------------------------------------------------------------------------
    private final double[] best;


    //------------------------------------------------------------------------
    /*package-private*/ VectorTarget(double[] copyBest)
    {
        best = copyBest;
    }

    
    //------------------------------------------------------------------------
    public double[] best()
    {
        return best.clone();
    }

    public double best(int index)
    {
        return best[ index ];
    }

    public int size()
    {
        return best.length;
    }
}
