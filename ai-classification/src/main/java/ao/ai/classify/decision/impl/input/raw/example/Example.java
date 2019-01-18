package ao.ai.classify.decision.impl.input.raw.example;

import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.example.LocalExample;

/**
 * External Example
 */
public interface Example extends Context
{
    public Datum target();

    public String targetType();

    public LocalExample toExample(DataPool pool);
}
