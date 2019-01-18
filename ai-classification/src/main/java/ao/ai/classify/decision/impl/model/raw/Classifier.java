package ao.ai.classify.decision.impl.model.raw;

import ao.ai.classify.decision.impl.input.raw.example.Example;
import ao.ai.classify.decision.impl.input.raw.example.LearningSet;

/**

 */
public interface Classifier extends Predictor
{
    public void set(LearningSet ls);
    public void add(LearningSet ls);
    public void add(Example example);

    public void limitPopulation(int toMostRecent);
}
