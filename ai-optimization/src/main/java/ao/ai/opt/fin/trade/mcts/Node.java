package ao.ai.opt.fin.trade.mcts;

import ao.ai.opt.fin.model.Account;
import ao.ai.opt.fin.model.Action;
import ao.ai.opt.fin.sim.MarketSimulator;
import ao.ai.opt.fin.trade.BuyAndHoldTrader;
import ao.ai.opt.fin.trade.Trader;
import ao.util.math.rand.Rand;
import ao.util.text.Txt;
import com.google.common.base.Preconditions;

import javax.accessibility.Accessible;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: AO
 * Date: 1/2/11
 * Time: 10:34 AM
 */
/*package-private*/ class Node
{
    //------------------------------------------------------------------------
    private int    visits          = 0;
    private Reward rewardSum       = Reward.ZERO;
    private Reward rewardSquareSum = Reward.ZERO;

    private final Action action;

    private Node[] kids;


    //------------------------------------------------------------------------
    public Node()
    {
        this( null );
    }

    public Node(Action action)
    {
        this.action = action;
    }


    //------------------------------------------------------------------------
    public int maxDepth()
    {
        if (kids == null) {
            return 1;
        }

        int depth = 0;
        for (Node nextChild : kids) {
            depth = Math.max(depth, nextChild.maxDepth());
        }
        return depth + 1;
    }


    //------------------------------------------------------------------------
    public Action action()
    {
        return action;
    }

    public int visits()
    {
        return visits;
    }

    public Node subNode(Action forAction)
    {
        for (Node kid : kids) {
            if (kid.action == forAction) {
                return kid;
            }
        }
        return null;
    }


    //------------------------------------------------------------------------
    public Action bestSubAction()
    {
        Preconditions.checkState( kids != null );

        Node   bestKid    = null;
        double bestReward = Double.NEGATIVE_INFINITY;

        for (Node kid : kids)
        {
            double reward = kid.averageReward();
            if (reward > bestReward)
            {
                bestReward = reward;
                bestKid    = kid;
            }
        }

        if (bestKid == null) {
            return null;
        }
        return bestKid.action;
    }


    //------------------------------------------------------------------------
    private double averageReward()
    {
        return visits == 0 ? 0 :
               rewardSum.averageOver( visits );
    }
    private double averageRewardSquared()
    {
        double averageReward = averageReward();
        return averageReward * averageReward;
    }
    private double averageSquaredReward()
    {
        return rewardSquareSum.averageOver( visits );
    }

    private double varianceCeiling(int playsSoFar)
    {
        return averageSquaredReward()
               - averageRewardSquared()
               + Math.sqrt(2.0 * Math.log(playsSoFar) / visits);
    }

    private boolean unvisited()
    {
        return visits == 0;
    }


    //--------------------------------------------------------------------
    public void playSimulation(
            Account         simAccount,
            MarketSimulator simulator)
    {
        LinkedList<Node> path = new LinkedList<Node>();
        path.add(this);

        while (! path.getLast().unvisited())
        {
            Node selectedChild =
                    path.getLast().descendByUCB1();
            if (selectedChild == null) {
                break;
            }

            path.add( selectedChild );

            simAccount.advance(
                    selectedChild.action,
                    simulator.nextDay());
        }

        propagateValue(
                path,
                path.getLast().monteCarloValue(
                        simAccount, simulator));
    }

    private Reward monteCarloValue(
            Account         simAccount,
            MarketSimulator simulator)
    {
        return new Reward(
                simAccount.rollOut(
                        new BuyAndHoldTrader(),
                        simulator));
    }

    private void propagateValue(LinkedList<Node> path, Reward reward)
    {
//        Reward maxiMax = reward.compliment();
        for (Iterator<Node> itr = path.descendingIterator();
                itr.hasNext();)
        {
            Node step = itr.next();

            step.rewardSum = step.rewardSum.plus(reward);
            step.rewardSquareSum =
                    step.rewardSquareSum.plus(
                            reward.square());
            step.visits++;
        }
    }


    //--------------------------------------------------------------------
    public Node descendByUCB1()
    {
        generateKids();

        double greatestUtc   = Integer.MIN_VALUE;
        Node   greatestChild = null;
        for (Node nextChild : kids)
        {
            double utcValue;
            if (nextChild.unvisited())
            {
                utcValue = 10000 + 1000 * Rand.nextDouble();
//                utcValue =
//                        optimize
//                        ? 1.0 + Rand.nextDouble()/10000
//                        : 10000 + 1000 * Rand.nextDouble();
//                utcValue = 1.0;
//                utcValue = 1.0 + Rand.nextDouble()/10;
            }
            else
            {
                utcValue =
                    nextChild.averageReward() +
                    Math.sqrt((Math.log(visits) /
                                     nextChild.visits) *
                               Math.min(
                                   0.25,
                                   nextChild.varianceCeiling(
                                           visits)));
            }

            if (utcValue > greatestUtc)
            {
                greatestUtc   = utcValue;
                greatestChild = nextChild;
            }
        }

        return greatestChild;
    }


    //------------------------------------------------------------------------
    private void generateKids()
    {
        if (kids != null) {
            return;
        }

        kids = new Node[ Action.VALUES.length ];
        for (Action act : Action.VALUES)
        {
            kids[ act.ordinal() ] = new Node(act);
        }
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return toString(0);
    }
    public String toString(int indent)
    {
        if (visits == 0) return "";

        StringBuilder str = new StringBuilder();
        str.append('\n');

        str.append( Txt.nTimes(" ", indent)  )
           //.append( "============"  )
           //.append( "\t"            )
           .append( "  "            )
           .append( visits          )
           .append( "  "            )
           .append( rewardSum       )
           .append( "  "            )
           .append( averageReward() )
           .append( "  "            )
           .append( action          );

        if (kids != null) {
            for (Node nextChild : kids) {
                str.append( nextChild.toString(indent + 2) );
            }
        }

        return str.toString();
    }
}
