package ao.ai.classify.decision.impl.classification.processed;

import ao.ai.classify.decision.impl.classification.RealHistogram;
import ao.ai.classify.decision.impl.input.processed.attribute.Attribute;
import ao.ai.classify.decision.impl.input.processed.attribute.Multistate;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;
import ao.ai.classify.decision.impl.input.processed.data.State;
import ao.util.math.stats.Info;

/**
 *
 */
public class Frequency implements Classification
{
    //--------------------------------------------------------------------
    private RealHistogram<LocalDatum> hist;
    private int                       sampleSize;


    //--------------------------------------------------------------------
    public Frequency()
    {
        this(0);
    }
    public Frequency(int totalSampleSize)
    {
        hist       = new RealHistogram<LocalDatum>(
                            totalSampleSize);
        sampleSize = totalSampleSize;
    }


    //--------------------------------------------------------------------
    public void add(LocalDatum datum)
    {
        hist.add( datum );
        hist.incrementSampleSize();
        sampleSize++;
    }
    public void addUnsampled(LocalDatum datum, double value)
    {
        hist.add( datum, value );
    }


    //--------------------------------------------------------------------
    public double probabilityOf(LocalDatum datum)
    {
        return hist.probabilityOf( datum );
    }


    //--------------------------------------------------------------------
    public int sampleSize()
    {
        return sampleSize;
    }


    //--------------------------------------------------------------------
    public double probabilityOfState(Object value)
    {
        for (LocalDatum item : hist.classes())
        {
            if (((State) item).state().equals( value ))
            {
                return hist.probabilityOf( item );
            }
        }
        return 0;
    }


    //--------------------------------------------------------------------
    public LocalDatum mostProbable()
    {
        return hist.mostProbable();
    }


    //--------------------------------------------------------------------
    //If there are M classes,
    //and in the first j things of a category, i[m] have had class m,
    // the class of the (j + l)th thing is encoded assigning a probability
    //  q[m] = (i[m] + alpha)/(j + M * alpha)
    public double transmissionCost(double alpha)
    {
        int numClasses = hist.classCount();

        int    j      = 0;
        double length = 0;
        for (LocalDatum clazz : hist.classes())
        {
            double classCount = hist.valueOf( clazz );
            for (int i = 0; i < classCount; i++)
            {
                double p = (i + alpha)/(j + numClasses*alpha);
                length  -= Info.log2(p);
                j++;
            }
        }
        return length;
    }


    //--------------------------------------------------------------------
    public RealHistogram<LocalDatum> asRealHistogram()
    {
        return hist;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        if (hist.isEmpty()) return "Empty";

        State     aState = (State) hist.classes().iterator().next();
        Attribute attr   = aState.attribute();

        StringBuilder b = new StringBuilder();
        for (LocalDatum datum : ((Multistate) attr).orderedPartition())
        {
            b.append(datum)
             .append("\t")
             .append( hist.valueOf(datum) )
             .append("\t");
        }
        return b.deleteCharAt( b.length()-1 ).toString();

//        return hist.toString() + "\t" +
//               Info.cost(probabilityOf(mostProbable()));
    }
}
