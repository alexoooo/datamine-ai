package ao.ai.classify.decision.impl.model.raw;

import ao.ai.classify.decision.impl.classification.raw.Prediction;
import ao.ai.classify.decision.impl.input.raw.example.Context;

/**
 *
 */
public interface Predictor
{
    public Prediction classify(Context context);
}
