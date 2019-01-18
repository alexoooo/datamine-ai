package ao.ai.opt.fin.fee;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 1:35 AM
 */
public interface FeeSchedule
{
    //------------------------------------------------------------------------
    double trade(
            boolean isBuy,
            int     volume,
            double  price);
}
