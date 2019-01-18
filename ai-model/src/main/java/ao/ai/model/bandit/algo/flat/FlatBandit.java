package ao.ai.model.bandit.algo.flat;

import ao.ai.model.bandit.reward.Reward;

/**
 * User: aostrovsky
 * Date: 10-Feb-2010
 * Time: 7:30:58 PM
 */
public interface FlatBandit
{
    //-------------------------------------------------------------------------
    public Reward trial(int armIndex);

    public int    armCount();


    //-------------------------------------------------------------------------
    public static interface Trial
    {
        public Reward reward();

        public int    armIndex();
    }
}
