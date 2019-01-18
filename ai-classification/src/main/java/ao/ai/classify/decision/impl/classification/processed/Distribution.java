package ao.ai.classify.decision.impl.classification.processed;

import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public class Distribution implements Classification
{
    //--------------------------------------------------------------------
    private Collection<LocalDatum> data;


    //--------------------------------------------------------------------
    public Distribution()
    {
        data = new ArrayList<LocalDatum>();
    }


    //--------------------------------------------------------------------
    public void add(LocalDatum datum)
    {
        data.add( datum );
    }

    public double transmissionCost(double alpha)
    {
        return 0;
    }

    public double probabilityOf(LocalDatum datum)
    {
        return 0;
    }

    //--------------------------------------------------------------------
    public int sampleSize()
    {
        return data.size();
    }
}
