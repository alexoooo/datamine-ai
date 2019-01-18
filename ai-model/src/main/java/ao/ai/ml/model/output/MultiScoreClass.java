package ao.ai.ml.model.output;

import ao.ai.ml.model.common.IndexedReal;
import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:10:04 PM
 */
public class MultiScoreClass
        extends MultiRankClass
{
    //------------------------------------------------------------------------
    public static MultiScoreClass createScored(int bestClass)
    {
        double[] classCores = new double[bestClass + 1];
        classCores[ bestClass ] = 1.0;
        return createScored( classCores );
    }

    public static MultiScoreClass createScored(
            double... classScores)
    {
        Preconditions.checkNotNull( classScores );
        Preconditions.checkArgument(
                classScores.length > 0);

        IndexedReal[] indexToScore = indexToScore(classScores);

        int      bestClass   = indexToScore[ 0 ].index();

        int[]    rankToClass = new int   [ classScores.length ];
        int[]    classToRank = new int   [ classScores.length ];
        double[] rankToScore = new double[ classScores.length ];

        for (int rank = 0; rank < classScores.length; rank++)
        {
            IndexedReal classToScore =
                    indexToScore[ rank ];

            rankToClass[ rank ] = classToScore.index();
            classToRank[ classToScore.index() ] = rank;
            rankToScore[ rank ] = classToScore.real();
        }

        return new MultiScoreClass(
                bestClass, rankToClass, classToRank, rankToScore, classScores);
    }

    private static IndexedReal[]
            indexToScore(double[] classScores)
    {
        IndexedReal[] indexToScore = new IndexedReal[ classScores.length ];

        for (int i = 0; i < classScores.length; i++)
        {
            indexToScore[ i ] = new IndexedReal(i, classScores[i]);
        }

        Arrays.sort(indexToScore, IndexedReal.DESCENDING_REAL_ORDER);

        return indexToScore;
    }


    //------------------------------------------------------------------------
    private double[] rankToScore;
    private double[] classToScore;


    //------------------------------------------------------------------------
    /*package-private*/ MultiScoreClass(
            int      bestClass,
            int[]    rankToClass,
            int[]    classToRank,
            double[] rankToScore,
            double[] classToScore)
    {
        super(bestClass, rankToClass, classToRank);

        this.rankToScore  = rankToScore;
        this.classToScore = classToScore;
    }


    //------------------------------------------------------------------------
    /**
     * @param rankIndex index of rank
     * @return score associated with rankIndex,
     *          or 0 if no score is available
     */
    public double scoreOfRank(int rankIndex)
    {
        if (rankIndex < 0)
        {
            throw new IndexOutOfBoundsException(
                    "rankIndex (" + rankIndex + ") must be >= 0");
        }

        return rankIndex < classToScore.length
               ? rankToScore[ rankIndex ]
               : 0;
    }

    /**
     * @param classIndex index of class
     * @return score associated with class index,
     *          or 0 if no score is available
     */
    public double scoreOfClass(int classIndex)
    {
        if (classIndex < 0)
        {
            throw new IndexOutOfBoundsException(
                    "classIndex (" + classIndex + ") must be >= 0");
        }

        return classIndex < classToScore.length
               ? classToScore[ classIndex ]
               : 0;
    }
}
