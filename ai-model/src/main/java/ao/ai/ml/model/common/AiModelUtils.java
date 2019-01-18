package ao.ai.ml.model.common;

/**
 * User: AO
 * Date: 10/28/10
 * Time: 10:59 PM
 */
public class AiModelUtils
{
    //------------------------------------------------------------------------
    private AiModelUtils() {}


    //------------------------------------------------------------------------
    public static double sgn(boolean isPositive)
    {
        return isPositive ? 1 : -1;
    }
}
