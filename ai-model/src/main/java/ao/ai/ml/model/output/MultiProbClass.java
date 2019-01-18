package ao.ai.ml.model.output;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:10:31 PM
 */
public class MultiProbClass
        extends MultiScoreClass
{
    //------------------------------------------------------------------------
    private final double[] rankToProb;
    private final double[] classToProb;


    //------------------------------------------------------------------------
    /*package-private*/ MultiProbClass(
            int      bestClass,
            int[]    rankToClass,
            int[]    classToRank,
            double[] rankToScore,
            double[] classToScore,
            double[] rankToProb,
            double[] classToProb)
    {
        super(bestClass,
              rankToClass,
              classToRank,
              rankToScore,
              classToScore);

        this.rankToProb  = rankToProb;
        this.classToProb = classToProb;
    }


    //------------------------------------------------------------------------
    /**
     * @param rankIndex index of rank
     * @return probability associated with rankIndex,
     *          or 0 if no score is available
     */
    public double probabilityOfRank(int rankIndex)
    {
        if (rankIndex < 0)
        {
            throw new IndexOutOfBoundsException(
                    "rankIndex (" + rankIndex + ") must be >= 0");
        }

        return rankIndex < rankToProb.length
               ? rankToProb[ rankIndex ]
               : 0;
    }

    /**
     * @param classIndex index of class
     * @return probability associated with class index,
     *          or 0 if no score is available
     */
    public double probabilityOfClass(int classIndex)
    {
        if (classIndex < 0)
        {
            throw new IndexOutOfBoundsException(
                    "classIndex (" + classIndex + ") must be >= 0");
        }

        return classIndex < classToProb.length
               ? classToProb[ classIndex ]
               : 0;
    }
}
