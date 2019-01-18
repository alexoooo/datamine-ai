package ao.ai.classify.decision.impl.input.processed.example;

import ao.ai.classify.decision.impl.input.processed.attribute.Attribute;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;

/**
 *
 */
public interface LocalExample extends LocalContext
{
    LocalDatum target();

    Attribute targetAttribute();
}
