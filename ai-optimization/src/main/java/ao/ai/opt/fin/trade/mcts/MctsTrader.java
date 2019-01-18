package ao.ai.opt.fin.trade.mcts;

import ao.ai.opt.fin.model.Account;
import ao.ai.opt.fin.model.Action;
import ao.ai.opt.fin.model.MarketData;
import ao.ai.opt.fin.sim.MarketSimulator;
import ao.ai.opt.fin.trade.Trader;
import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 2:56 AM
 */
public class MctsTrader
        implements Trader
{
    //------------------------------------------------------------------------
    private final Account         account;
    private final MarketSimulator simulator;

    private int  numRuns;
    private Node nextRoot = new Node();


    //------------------------------------------------------------------------
//    public MctsTrader()
//    {
//        this(1024);
//    }

    public MctsTrader(
            Account         account,
            MarketSimulator simulator,
            int             iterations)
    {
        numRuns = iterations;

        this.simulator = simulator.prototype();
        this.account   = account  .prototype();
    }



    //------------------------------------------------------------------------
    @Override
    public Action act(MarketData stateDelta)
    {
        MarketSimulator protoSimulator = simulator.prototype();
        Preconditions.checkState(
                simulator.nextDay()
                        .equals( stateDelta ));
//        simulator.learn( stateDelta );

        Node root = nextRoot;
//        Node root = new Node();

//        System.out.println(
//                "Max depth (before): " + root.maxDepth());

        for (int run = 0; run < numRuns; run++)
        {
            root.playSimulation(
                    account.prototype(),
                    protoSimulator.prototype());
        }

//        System.out.println(
//                "Max depth: " + root.maxDepth());
        Action act = root.bestSubAction();

        account.advance( act, stateDelta );
        nextRoot = nextRoot.subNode( act );
        return act;
    }
}
