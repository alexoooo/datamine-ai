package ao.ai.opt.fin.sim;

import ao.ai.opt.fin.model.MarketData;
import ao.ai.opt.fin.sim.MarketSimulator;
import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 2:16 AM
 */
public class LiteralMarketSimulator
        implements MarketSimulator
{
    //------------------------------------------------------------------------
    private final double   initialCashInjection;
    private final double[] priceDeltas;

    private       int      nextIndex;


    //------------------------------------------------------------------------
    public LiteralMarketSimulator(
            double   initialCashInjection,
            double[] prices)
    {
        Preconditions.checkArgument(
                prices.length > 1);

        this.initialCashInjection = initialCashInjection;
        priceDeltas = new double[ prices.length - 1 ];

        double prevPrice = prices[ 0 ];
        for (int i = 1; i < prices.length; i++)
        {
            double delta = prices[ i ] / prevPrice - 1;
            priceDeltas[ i - 1 ] = delta;
            prevPrice = prices[ i ];
        }
    }

    private LiteralMarketSimulator(
            double   initialCashInjection,
            double[] priceDeltas,
            int      nextIndex)
    {
        this.initialCashInjection = initialCashInjection;
        this.priceDeltas          = priceDeltas;
        this.nextIndex            = nextIndex;
    }


    //------------------------------------------------------------------------
    public boolean hasNext()
    {
        return (nextIndex < priceDeltas.length);
    }

    @Override
    public MarketData nextDay()
    {
        if (nextIndex == 0)
        {
            nextIndex++;
            return new MarketData(
                    0, initialCashInjection);
        }

        return new MarketData(
                priceDeltas[ nextIndex++ ]);
    }


    //------------------------------------------------------------------------
    @Override
    public MarketSimulator prototype()
    {
        return new LiteralMarketSimulator(
                initialCashInjection, priceDeltas, nextIndex);
    }
}
