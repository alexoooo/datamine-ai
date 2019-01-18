package ao.ai.ml.model.output;

/**
 * User: AO
 * Date: Sep 1, 2010
 * Time: 11:06:44 PM
 */
public final class BinaryProbClass
        extends BinaryScoreClass
{
    //------------------------------------------------------------------------
    public static BinaryProbClass createProb(
            double positiveProbability)
    {
        return new BinaryProbClass(
                positiveProbability,
                1.0 - positiveProbability,
                positiveProbability);
    }

    public static BinaryProbClass createProb(
            double positiveScore,
            double negativeScore)
    {
        return new BinaryProbClass(
                positiveScore, negativeScore,
                Math.abs( positiveScore ) /
                    (Math.abs( positiveScore ) +
                     Math.abs( negativeScore )));
    }

    public static BinaryProbClass createProb(
            BinaryClass binaryClass)
    {
        return createProb(BinaryScoreClass.createScored(binaryClass));
    }

    public static BinaryProbClass createProb(
            BinaryScoreClass binaryScoreClass)
    {
        return createProb(binaryScoreClass.positiveScore(),
                          binaryScoreClass.negativeScore());
    }


    //------------------------------------------------------------------------
    private final double positiveProbability;


    //------------------------------------------------------------------------
    private BinaryProbClass(
            double positiveScore,
            double negativeScore,
            double positiveProbability)
    {
        super(positiveScore, negativeScore);

        verifyProbability( positiveProbability );

        this.positiveProbability = positiveProbability;
    }

    private static void verifyProbability(double probability)
    {
        if (! (Double.compare(-0.0, probability) <= 0 &&
               Double.compare(      probability, 1.0) <= 0) )
        {
            throw new IllegalArgumentException(
                    "Probability must be in [0, 1]: " +
                        probability);
        }
    }


    //------------------------------------------------------------------------
    public final double positiveProbability()
    {
        return positiveProbability;
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return super.toString() +
               " | " + positiveProbability;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BinaryProbClass that = (BinaryProbClass) o;
        return Double.compare(
                this.positiveProbability,
                that.positiveProbability) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = (positiveProbability != +0.0d
                ? Double.doubleToLongBits(positiveProbability) : 0L);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
