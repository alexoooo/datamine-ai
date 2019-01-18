package ao.ai.ml.model.output;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:10:54 PM
 */
public class ScalarTarget
{
    //------------------------------------------------------------------------
    private final double best;
    

    //------------------------------------------------------------------------
    /*package-private*/ ScalarTarget(double best)
    {
        this.best = best;
    }


    //------------------------------------------------------------------------
    public double best()
    {
        return best;
    }
}
