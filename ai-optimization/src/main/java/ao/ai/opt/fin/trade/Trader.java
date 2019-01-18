package ao.ai.opt.fin.trade;

import ao.ai.opt.fin.model.Action;
import ao.ai.opt.fin.model.MarketData;

/**
 * User: AO
 * Date: 1/1/11
 * Time: 8:39 PM
 */
public interface Trader
{
    //------------------------------------------------------------------------
    Action act(MarketData stateDelta);
}
