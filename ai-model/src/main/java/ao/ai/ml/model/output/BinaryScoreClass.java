package ao.ai.ml.model.output;

/**
 * User: AO
 * Date: Sep 1, 2010
 * Time: 11:06:20 PM
 */
public class BinaryScoreClass
        extends BinaryClass
{
    //------------------------------------------------------------------------
    public static BinaryScoreClass createScored(
            double positiveScore,
            double negativeScore)
    {
        return new BinaryScoreClass(
                positiveScore, negativeScore);
    }

    public static BinaryScoreClass createScored(
            BinaryClass binaryClass)
    {
        return createScored(binaryClass.isPositive());
    }

    public static BinaryScoreClass createScored(
            boolean isPositive)
    {
        return new BinaryScoreClass(
                isPositive ?  1.0 : -1.0,
                isPositive ? -1.0 :  1.0);
    }


    //------------------------------------------------------------------------
    private final double positiveScore;
    private final double negativeScore;


    //------------------------------------------------------------------------
    /*package-private*/ BinaryScoreClass(
            double positiveScore,
            double negativeScore)
    {
        super(Double.compare(
                positiveScore,
                negativeScore
              ) >= 0);

        verifyReal( positiveScore );
        verifyReal( negativeScore );

        this.positiveScore = positiveScore;
        this.negativeScore = negativeScore;
    }

    private static void verifyReal(double value)
    {
        if (Double.isNaN(value) || Double.isInfinite(value))
        {
            throw new IllegalArgumentException(
                    "Must not be NaN or Infinite: " + value);
        }
    }


    //------------------------------------------------------------------------
    public final double positiveScore()
    {
        return positiveScore;
    }

    public final double negativeScore()
    {
        return negativeScore;
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return super.toString() +
               " | " + positiveScore + " vs " + negativeScore;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        if (! super.equals(o)) return false;

        BinaryScoreClass that = (BinaryScoreClass) o;

        return Double.compare(that.negativeScore, negativeScore) == 0 &&
               Double.compare(that.positiveScore, positiveScore) == 0;
    }

    @Override
    public int hashCode() {
        int  result;
        long temp;

        temp   = (positiveScore != +0.0d
                  ? Double.doubleToLongBits(positiveScore) : 0L);
        result = (int) (temp ^ (temp >>> 32));

        temp   = (negativeScore != +0.0d
                  ? Double.doubleToLongBits(negativeScore) : 0L);
        result = 31 * result + (int) (temp ^ (temp >>> 32));

        return result;
    }
}
