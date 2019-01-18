package ao.ai.classify.norm;

import ao.ai.ml.model.input.RealList;

/**
 * User: AO
 * Date: Oct 11, 2010
 * Time: 5:41:19 PM
 */
public class RealListNormalizerBuilder
{
    //------------------------------------------------------------------------
    /**
     * @param vector to be considered for normalization
     * @return true if material change in normalization took place
     *          as a result of the new vector
     */
    public boolean add(RealList vector)
    {
        return false;
    }


    //------------------------------------------------------------------------
    public RealListNormalizer build()
    {
        return new RealListNormalizer();
    }
}
