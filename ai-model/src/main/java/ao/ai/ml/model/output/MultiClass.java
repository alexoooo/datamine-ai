package ao.ai.ml.model.output;

/**
 * User: AO
 * Date: Sep 1, 2010
 * Time: 11:07:01 PM
 */
public class MultiClass
{
    //------------------------------------------------------------------------
    public static MultiClass create(
            int best/*, int size*/)
    {
        return new MultiClass(best/*, size*/);
    }


    //------------------------------------------------------------------------
    private final int best;
//    private final int size;


    //------------------------------------------------------------------------
    /*package-private*/ MultiClass(
            int bestClass/*, int size*/)
    {
        this.best = bestClass;
//        this.size = size;
    }


    //------------------------------------------------------------------------
    public int best()
    {
        return best;
    }

//    public int size()
//    {
//        return size;
//    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return String.valueOf( best );
    }
}
