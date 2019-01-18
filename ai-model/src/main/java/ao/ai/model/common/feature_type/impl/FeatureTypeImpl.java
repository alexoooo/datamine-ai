package ao.ai.model.common.feature_type.impl;

import ao.ai.model.common.feature_type.FeatureType;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 9:04:25 PM
 */
public class FeatureTypeImpl
        implements FeatureType
{
    //--------------------------------------------------------------------
    private final Object delegate;


    //--------------------------------------------------------------------
    public FeatureTypeImpl(Object backingType)
    {
        delegate = backingType;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return delegate.toString();
    }

    @Override
    public int hashCode()
    {
        return delegate.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;
        if (! (o instanceof FeatureTypeImpl)) return false;

        FeatureTypeImpl that = (FeatureTypeImpl) o;
        return delegate.equals( that.delegate );
    }
}
