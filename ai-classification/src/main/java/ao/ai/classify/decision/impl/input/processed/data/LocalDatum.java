package ao.ai.classify.decision.impl.input.processed.data;

import ao.ai.classify.decision.impl.input.processed.attribute.Attribute;

import java.io.Serializable;

/**
 *
 */
public abstract class LocalDatum implements Serializable
{
    //--------------------------------------------------------------------
    private final Attribute ATTRIBUTE;


    //--------------------------------------------------------------------
    public LocalDatum(Attribute attribute)
    {
        ATTRIBUTE = attribute;
    }


    //--------------------------------------------------------------------
    public Attribute attribute()
    {
        return ATTRIBUTE;
    }


    //--------------------------------------------------------------------
    // for state/value, true if the given datum is equal
    // for ValueRange true if the given datum is in range.
    public abstract boolean contains(LocalDatum datum);
//    {
//        return false;
//    }
}
