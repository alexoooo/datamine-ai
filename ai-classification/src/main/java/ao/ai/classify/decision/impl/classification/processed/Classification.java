package ao.ai.classify.decision.impl.classification.processed;

import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;

import java.io.Serializable;

/**
 *
 */
public interface Classification
        extends Serializable
{         
    //--------------------------------------------------------------------
    public void add(LocalDatum datum);

    public double transmissionCost(double alpha);

    public double probabilityOf(LocalDatum datum);


    //--------------------------------------------------------------------
    public int sampleSize();
}
