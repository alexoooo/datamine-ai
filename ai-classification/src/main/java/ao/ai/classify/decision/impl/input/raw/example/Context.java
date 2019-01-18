package ao.ai.classify.decision.impl.input.raw.example;

import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.example.LocalContext;

import java.util.List;

/**
 * External Context
 */
public interface Context
{
    public Example withTarget(Datum target);

    public void add(Datum datum);
    public void addAll(Context dataFrom);

    public Context remove(String type);

    public List<String> types();
    public List<Datum>  data();

    public LocalContext toContext(DataPool pool);
}
