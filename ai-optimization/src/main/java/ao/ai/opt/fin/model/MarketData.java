package ao.ai.opt.fin.model;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 2:09 AM
 */
public class MarketData
{
    //------------------------------------------------------------------------
    private final double adjustedPricesDelta;
    private final double cashInjection;


    //------------------------------------------------------------------------
    public MarketData(double adjustedPricesDelta)
    {
        this(adjustedPricesDelta, 0);
    }

    public MarketData(
            double adjustedPricesDelta,
            double cashInjection)
    {
        this.adjustedPricesDelta = adjustedPricesDelta;
        this.cashInjection       = cashInjection;
    }


    //------------------------------------------------------------------------
    public double cashInjection()
    {
        return cashInjection;
    }

    public double adjustedPriceDelta()
    {
        return adjustedPricesDelta;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketData data = (MarketData) o;

        if (Double.compare(data.adjustedPricesDelta, adjustedPricesDelta) != 0) return false;
        if (Double.compare(data.cashInjection, cashInjection) != 0) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = adjustedPricesDelta != +0.0d ? Double.doubleToLongBits(adjustedPricesDelta) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = cashInjection != +0.0d ? Double.doubleToLongBits(cashInjection) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
