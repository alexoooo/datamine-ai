package ao.ai.classify.decision.impl.input.raw.example;

import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.example.LocalLearningSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class LearningSet
{
    //--------------------------------------------------------------------
    private List<Example> data;


    //--------------------------------------------------------------------
    public LearningSet()
    {
        data = new ArrayList<Example>();
    }


    //--------------------------------------------------------------------
    public void add(Example datum)
    {
        data.add( datum );
    }

    public int size()
    {
        return data.size();
    }

    public boolean isEmpty()
    {
        return data.isEmpty();
    }


    //--------------------------------------------------------------------
    public void addAll(LearningSet addend)
    {
        data.addAll( addend.data );
    }


    //--------------------------------------------------------------------
    public Collection<String> types()
    {
        return isEmpty()
                ? Collections.<String>emptyList()
                : firstExample().types();
    }


    //--------------------------------------------------------------------
    public LocalLearningSet toLearningSet(DataPool pool)
    {
        LocalLearningSet ls = new LocalLearningSet();
        for (Example buffExample : data)
        {
            ls.add( buffExample.toExample(pool) );
        }
        return ls;
    }


    //--------------------------------------------------------------------
    private Example firstExample()
    {
        return (data == null)
                ? null
                : data.get(0);
    }
}
