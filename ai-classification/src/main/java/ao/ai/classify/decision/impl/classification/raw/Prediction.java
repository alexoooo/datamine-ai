package ao.ai.classify.decision.impl.classification.raw;

import ao.ai.classify.decision.impl.classification.RealHistogram;
import ao.ai.classify.decision.impl.classification.processed.Classification;
import ao.ai.classify.decision.impl.classification.processed.Distribution;
import ao.ai.classify.decision.impl.classification.processed.Frequency;
import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;
import ao.ai.classify.decision.impl.input.processed.data.State;
import ao.ai.classify.decision.impl.input.raw.example.Datum;

/**
 *
 */
public class Prediction
{
    //--------------------------------------------------------------------
    private final Classification DELEGET;
    private final DataPool       POOL;


    //--------------------------------------------------------------------
    public Prediction(Classification classification,
                      DataPool       pool)
    {
        DELEGET = classification;
        POOL    = pool;
    }


    //--------------------------------------------------------------------
    public double probabilityOf(Datum datum)
    {
        return DELEGET.probabilityOf( datum.toDatum(POOL) );
    }


    //--------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public RealHistogram toRealHistogram()
    {
        if (DELEGET instanceof Distribution) return null;

        RealHistogram<LocalDatum> localHist =
                ((Frequency) DELEGET).asRealHistogram();

        RealHistogram rawHist = new RealHistogram(sampleSize());
        for (LocalDatum clazz : localHist.classes())
        {
            rawHist.add(((State) clazz).state(),
                        localHist.valueOf(clazz));
        }
        return rawHist;
    }

    public int sampleSize()
    {
//        return DELEGET.sampleSize();
        return DELEGET == null
                ? 0 : DELEGET.sampleSize();
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return DELEGET.toString();
    }
}
