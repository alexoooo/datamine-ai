package ao.ai.model.bandit.reward;

/**
 * User: aostrovsky
 * Date: 10-Feb-2010
 * Time: 7:33:56 PM
 */
public interface Reward
{
    //--------------------------------------------------------------------
    /**
     * @return real value in [0, 1] range.
     *          0 being total loss, 1 being absolute win
     */
    public double reward();
}
