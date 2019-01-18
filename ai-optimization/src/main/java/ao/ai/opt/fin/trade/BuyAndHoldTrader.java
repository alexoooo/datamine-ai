package ao.ai.opt.fin.trade;

import ao.ai.opt.fin.model.Action;
import ao.ai.opt.fin.model.MarketData;
import ao.ai.opt.fin.trade.Trader;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 2:11 AM
 */
public class BuyAndHoldTrader
        implements Trader
{
    //------------------------------------------------------------------------
    private final boolean buyWithCashInjection;


    //------------------------------------------------------------------------
    public BuyAndHoldTrader()
    {
        this( true );
    }

    public BuyAndHoldTrader(boolean buyWithCashInjection)
    {
        this.buyWithCashInjection = buyWithCashInjection;
    }


    //------------------------------------------------------------------------
    @Override
    public Action act(MarketData stateDelta)
    {
        if (buyWithCashInjection &&
                stateDelta.cashInjection() > 0)
        {
            return Action.BUY;
        }

        return Action.HOLD;
    }
}
