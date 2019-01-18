package ao.ai.opt.fin.trade.mcts;

import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 10:36 AM
 */
/*package-private*/ class Reward
{
    //------------------------------------------------------------------------
    public static final Reward ZERO = new Reward(0, true);


    //------------------------------------------------------------------------
    private static final double MIN_LOSS = -1.0;
    private static final double MAX_GAIN =  1.0;
    private static final double RANGE    = MAX_GAIN - MIN_LOSS;


    //------------------------------------------------------------------------
    private final double value;


    //------------------------------------------------------------------------
    public Reward(double annualAdjustedReturnOnInvestment)
    {
        this(Math.min(Math.max(
                annualAdjustedReturnOnInvestment,
                MIN_LOSS), MAX_GAIN) / RANGE + 0.5,
             true);
    }

    private Reward(
            double  annualAdjustedReturnOnInvestment,
            boolean checkBetweenZeroAndOne)
    {
        if (checkBetweenZeroAndOne)
        {
            Preconditions.checkArgument(
                    0 <= annualAdjustedReturnOnInvestment &&
                         annualAdjustedReturnOnInvestment <= 1);
        }

        value = annualAdjustedReturnOnInvestment;
    }


    //------------------------------------------------------------------------
    public double averageOver(int visits)
    {
        return value / visits;
    }


    //--------------------------------------------------------------------
    public Reward plus(Reward addend)
    {
        return new Reward(value + addend.value, false);
    }

    public Reward compliment()
    {
        Preconditions.checkState(
                0 <= value && value <= 1);

        return new Reward(1.0 - value);
    }

    public Reward square()
    {
        return new Reward(value * value);
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return String.valueOf( value );
    }
}
