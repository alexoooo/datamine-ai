package ao.ai.classify.decision.impl.model.raw;

import ao.ai.classify.decision.impl.classification.raw.Prediction;
import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.raw.example.Context;
import ao.ai.classify.decision.impl.input.raw.example.Example;
import ao.ai.classify.decision.impl.input.raw.example.LearningSet;
import ao.ai.classify.decision.impl.model.processed.LocalClassifier;

import java.io.Serializable;

/**
 *
 */
public class ClassifierImpl implements Classifier, Serializable
{
    //--------------------------------------------------------------------
    private final DataPool        pool;
    private final LocalClassifier deleget;


    //--------------------------------------------------------------------
    public ClassifierImpl(LocalClassifier localClassifier)
    {
        pool    = new DataPool();
        deleget = localClassifier;
    }


    //--------------------------------------------------------------------
    public void set(LearningSet ls)
    {
        deleget.set( ls.toLearningSet(pool) );
    }

    public void add(LearningSet ls)
    {
        deleget.add( ls.toLearningSet(pool) );
    }

    public void add(Example example)
    {
        deleget.add( example.toExample(pool) );
    }

    public Prediction classify(Context context)
    {
        return new Prediction(
                    deleget.classify(context.toContext(pool)),
                    pool);
    }


    //--------------------------------------------------------------------
    public void limitPopulation(int toMostRecent)
    {
        deleget.limitPopulation( toMostRecent );
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return deleget.toString();
    }
}
