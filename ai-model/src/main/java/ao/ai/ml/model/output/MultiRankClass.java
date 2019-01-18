package ao.ai.ml.model.output;

import java.util.Arrays;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:09:46 PM
 */
public class MultiRankClass
        extends MultiClass
{
    //------------------------------------------------------------------------
    private final int[] classByRank;
    private final int[] rankByClass;


    //------------------------------------------------------------------------
    /*package-private*/ MultiRankClass(
            int   bestClass,
            int[] rankToClass,
            int[] classToRank)
    {
        super(bestClass);

        this.classByRank = rankToClass;
        this.rankByClass = classToRank;
    }


    //------------------------------------------------------------------------
    /**
     * @param classIndex multi class index
     * @return rank of classIndex, or -1 if no rank is available
     */
    public int rankOfClass(int classIndex)
    {
        if (classIndex < 0)
        {
            throw new IndexOutOfBoundsException(
                    "classIndex (" + classIndex + ") must be > 0");
        }

        return classIndex < rankByClass.length
               ? rankByClass[ classIndex ]
               : -1;
    }

    /**
     * @param rank of index
     * @return index classByRank at "rank"
     * @throws IndexOutOfBoundsException if rank is not available
     */
    public int classOfRank(int rank)
    {
        return classByRank[ rank ];
    }

    public int[] classByRank()
    {
        return classByRank.clone();
    }

    public int classCount()
    {
        return classByRank.length;
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString(
                classByRank);
    }
}
