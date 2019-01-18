package ao.ai.model.bandit.algo.flat.impl;

import ao.ai.model.bandit.algo.flat.FlatBandit;
import ao.ai.model.bandit.reward.Reward;

/**
 * User: aostrovsky
 * Date: 11-Feb-2010
 * Time: 7:36:24 AM
 */
public class FlatBanditTrial
        implements FlatBandit.Trial
{
    //-------------------------------------------------------------------------
    private final Reward REWARD;
    private final int    ARM;


    //-------------------------------------------------------------------------
    public FlatBanditTrial(
            Reward reward,
            int    armIndex)
    {
        REWARD = reward;
        ARM    = armIndex;
    }


    //-------------------------------------------------------------------------
    @Override
    public Reward reward()
    {
        return REWARD;
    }


    //-------------------------------------------------------------------------
    @Override
    public int armIndex()
    {
        return ARM;
    }
}
