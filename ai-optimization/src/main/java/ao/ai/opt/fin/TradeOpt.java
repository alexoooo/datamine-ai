package ao.ai.opt.fin;

import ao.ai.opt.fin.fee.ConstantFeeSchedule;
import ao.ai.opt.fin.fee.FeeSchedule;
import ao.ai.opt.fin.model.Account;
import ao.ai.opt.fin.model.Action;
import ao.ai.opt.fin.model.MarketData;
import ao.ai.opt.fin.sim.LiteralMarketSimulator;
import ao.ai.opt.fin.sim.MarketSimulator;
import ao.ai.opt.fin.trade.Trader;
import ao.ai.opt.fin.trade.mcts.MctsTrader;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * User: AO
 * Date: 1/1/11
 * Time: 8:41 PM
 */
public class TradeOpt
{
    //------------------------------------------------------------------------
    private static final File HISTORICAL_PRICE_FILE = new File(
            "C:/~/data/stock/cm.csv");

    private static final Splitter COMMA_SPLITTER = Splitter.on(',');


    //------------------------------------------------------------------------
    public static void main(String[] args) throws IOException
    {
        double[] adjustedClosePrices =
                adjustedClosePrices();

        MarketSimulator oracle =
                new LiteralMarketSimulator(
                        5000,
                        adjustedClosePrices);

        FeeSchedule feeSchedule =
                new ConstantFeeSchedule(
                        35);

        Account account = new Account(
                feeSchedule, adjustedClosePrices[ 0 ]);

//        Trader trader = new BuyAndHoldTrader();
        Trader trader = new MctsTrader(
                account, oracle, 16 * 1024);

        int day = 0;
        System.out.println(
                "Day\tAct\tValue");

        while (oracle.hasNext())
        {
            MarketData delta = oracle.nextDay();
            Action act = account.advance(trader, delta);

            System.out.println(
                    day++ + "\t" + act + "\t" + account.liquidate());
        }

//        System.out.println(
//                "Total funds: " + account.liquidate());
    }


    //------------------------------------------------------------------------
    private static double[] adjustedClosePrices() throws IOException
    {
        List<String> lines = Files.readLines(
                HISTORICAL_PRICE_FILE,
                Charsets.US_ASCII);

        double[] adjustedClosePrices =
                new double[ lines.size() - 1 ];

        for (int i = 1; i < lines.size(); i++)
        {
            Iterator<String> parts =
                    COMMA_SPLITTER
                            .split( lines.get(i) )
                            .iterator();

            parts.next(); // date
            parts.next(); // open
            parts.next(); // high
            parts.next(); // low
            parts.next(); // close
            parts.next(); // volume

            double adjustedClose = Double.parseDouble(
                    parts.next());

            adjustedClosePrices[ lines.size() - i - 1 ] = adjustedClose;
        }

        return adjustedClosePrices;
    }
}
