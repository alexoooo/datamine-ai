package ao.ai.classify.decision.impl.input.processed.example;

import ao.ai.classify.decision.impl.input.processed.attribute.Attribute;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public interface LocalContext
{
    public LocalExample withTarget(LocalDatum target);

    public List<Attribute> dataAttributes();

    public Collection<LocalDatum> data();

    public void add(LocalDatum datum);
    public void addAll(LocalContext dataFrom);

    public LocalDatum datumOfType(Attribute attribute);
}
