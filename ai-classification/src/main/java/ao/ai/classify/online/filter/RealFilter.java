package ao.ai.classify.online.filter;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:40 PM
 */
public interface RealFilter
{
    //------------------------------------------------------------------------
    boolean accept(double value);
    void learn(double value);
}
