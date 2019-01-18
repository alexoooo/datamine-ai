package ao.ai.classify.decision.impl.model.processed;

import ao.ai.classify.decision.impl.classification.processed.Classification;
import ao.ai.classify.decision.impl.input.processed.example.LocalContext;
import ao.ai.classify.decision.impl.input.processed.example.LocalExample;
import ao.ai.classify.decision.impl.input.processed.example.LocalLearningSet;

import java.io.Serializable;

/**
 *
 */
public interface LocalClassifier
{
    //--------------------------------------------------------------------
    public void set(LocalLearningSet ls);
    public void add(LocalLearningSet ls);
    public void add(LocalExample example);

    public Classification classify(LocalContext context);

    public void limitPopulation(int toMostRecent);


    //--------------------------------------------------------------------
    public static interface Factory
            extends Serializable
    {
        public LocalClassifier newInstance();
    }
}
