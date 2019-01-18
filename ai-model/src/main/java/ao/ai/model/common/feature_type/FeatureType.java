package ao.ai.model.common.feature_type;

/**
 * User: aostrovsky
 * Date: 23-Jan-2010
 * Time: 6:59:27 PM
 */
public interface FeatureType
{
    //--------------------------------------------------------------------
    


    //--------------------------------------------------------------------
    // Just a reminder that all of the usual contracts must
    //   be implemented for this class

    @Override
    public String  toString();

    @Override
    public boolean equals(Object o);

    @Override
    public int     hashCode();
}
