package ao.ai.opt.fin.sim;

import ao.ai.opt.fin.model.MarketData;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 1:37 AM
 */
public interface MarketSimulator
{
    //------------------------------------------------------------------------
    boolean    hasNext();

    MarketData nextDay();

    MarketSimulator prototype();
}
