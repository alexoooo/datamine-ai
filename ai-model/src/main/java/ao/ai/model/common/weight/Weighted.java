package ao.ai.model.common.weight;

/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 9:00:50 AM
 */
public interface Weighted
{
    //--------------------------------------------------------------------
    /**
     * @return importance or prevalence of item
     */
    public double weight();
}
