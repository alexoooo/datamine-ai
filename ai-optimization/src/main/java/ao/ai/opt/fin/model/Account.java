package ao.ai.opt.fin.model;

import ao.ai.opt.fin.fee.FeeSchedule;
import ao.ai.opt.fin.sim.MarketSimulator;
import ao.ai.opt.fin.trade.Trader;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 2:26 AM
 */
public class Account
{
    //------------------------------------------------------------------------
    private final FeeSchedule feeSchedule;

    private double  price;
    private int     volume;

    private double  cash;
    private double  totalInjected;


    //------------------------------------------------------------------------
    public Account(
            FeeSchedule feeSchedule,
            double      initialPrice)
    {
        this(feeSchedule, initialPrice, 0, 0, 0);
    }

    private Account(
            FeeSchedule feeSchedule,
            double      price,
            int         volume,
            double      cash,
            double      totalInjected)
    {
        this.feeSchedule   = feeSchedule;
        this.price         = price;
        this.volume        = volume;
        this.cash          = cash;
        this.totalInjected = totalInjected;
    }


    //------------------------------------------------------------------------
    public void advance(
            Action     action,
            MarketData observation)
    {
        injectCash(observation.cashInjection());
        dayPassed ( observation.adjustedPriceDelta() );
        act       ( action );
    }

    public Action advance(
            Trader     trader,
            MarketData observation)
    {
        injectCash( observation.cashInjection() );
        Action action = trader.act( observation );
        dayPassed ( observation.adjustedPriceDelta() );

        act( action );
        return action;
    }


    //------------------------------------------------------------------------
    private void injectCash(double delta)
    {
        cash          += delta;
        totalInjected += delta;
    }

    private void dayPassed(double priceDelta)
    {
        price *= (1.0 + priceDelta);
    }


    //------------------------------------------------------------------------
    private void act(Action action)
    {
        if (action == Action.BUY) {
            buy();
        } else if (action == Action.SELL) {
            sell();
        }
    }

    private void buy()
    {
        int    buyVolume = Math.max(0, (int)(cash / price));
        double buyPrice  = price * buyVolume;

        double fee = feeSchedule.trade(
                true, buyVolume, price);

        volume += buyVolume;
        cash = cash - buyPrice - fee;
    }

    private void sell()
    {
        double fee = feeSchedule.trade(
                false, volume, price);

        cash += (price * volume) - fee;
        volume = 0;
    }


    //------------------------------------------------------------------------
    public double liquidate()
    {
        return cash + volume * price;
    }


    //------------------------------------------------------------------------
    public double rollOut(
            Trader trader, MarketSimulator oracle)
    {
        int    days             = 0;
        double injectedBefore   = totalInjected;
        double liquidatedBefore = liquidate();

        while (oracle.hasNext())
        {
            MarketData delta = oracle.nextDay();

            injectCash(delta.cashInjection());

            Action act = trader.act( delta );

            dayPassed(delta.adjustedPriceDelta());

            switch (act)
            {
                case BUY : buy(); break;
                case SELL: sell(); break;
            }

            days++;
        }

        double profitedBefore =
                (liquidatedBefore - injectedBefore);

        double profitedAfter =
                (liquidate() - totalInjected);

        double injectedDelta =
                totalInjected - injectedBefore;

        double deltaProfit =
                profitedAfter - profitedBefore;

        double moneyInput = (profitedBefore + injectedDelta);

        if (deltaProfit == 0) {
            return 0;
        }

        double roi = deltaProfit / moneyInput;
        double annualRoi = roi / ((double) days / 365.2425);
//        if (Double.isNaN( annualRoi )) {
//            System.out.println("wtf?");
//        }

        return annualRoi;
    }


    //------------------------------------------------------------------------
    public Account prototype()
    {
        return new Account(
                feeSchedule, price, volume, cash, totalInjected);
    }
}
