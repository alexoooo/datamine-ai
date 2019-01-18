package ao.ai.opt.fin.fee;

import ao.ai.opt.fin.fee.FeeSchedule;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 2:39 AM
 */
public class ConstantFeeSchedule
        implements FeeSchedule
{
    //------------------------------------------------------------------------
    private final double fee;


    //------------------------------------------------------------------------
    public ConstantFeeSchedule(double fee)
    {
        this.fee = fee;
    }


    //------------------------------------------------------------------------
    @Override
    public double trade(boolean isBuy, int volume, double price)
    {
        return fee;
    }
}
