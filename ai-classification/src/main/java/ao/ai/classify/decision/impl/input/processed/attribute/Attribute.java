package ao.ai.classify.decision.impl.input.processed.attribute;

import ao.ai.classify.decision.impl.classification.processed.Classification;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 */
public interface Attribute extends Serializable
{
    // true if both this and the given attribute measure the same thing.
    // for multistate attributes, this determines weather or not
    //  they are the same object.
    // for continuous attributes, this will check if they are two
    //  cuts of the same attribute.
    public boolean typeEquals(Attribute availAttr);
    public String  type();


    public boolean isSingleUse();

    public Collection<? extends LocalDatum> partition();

    public Collection<? extends Attribute> views();
    public Attribute randomView();
    public double viewChoiceLength();

    public Classification newClassification();
}
