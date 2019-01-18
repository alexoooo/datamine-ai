package ao.ai.classify.decision.impl.random;

import ao.ai.classify.decision.impl.classification.processed.Classification;
import ao.ai.classify.decision.impl.classification.processed.Frequency;
import ao.ai.classify.decision.impl.input.processed.attribute.Multistate;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;
import ao.ai.classify.decision.impl.input.processed.example.LocalContext;
import ao.ai.classify.decision.impl.input.processed.example.LocalExample;
import ao.ai.classify.decision.impl.input.processed.example.LocalLearningSet;
import ao.ai.classify.decision.impl.model.processed.LocalClassifier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class RandomLearner
        implements LocalClassifier, Serializable
{
    //--------------------------------------------------------------------
    public static class Factory implements LocalClassifier.Factory
    {
        public LocalClassifier newInstance()
        {
            return new RandomLearner();
        }
    }


    //--------------------------------------------------------------------
    private static final long serialVersionUID  = 0L;

//    private static final int  DEFAULT_NUM_TREES = 32;
    private static final int  DEFAULT_NUM_TREES = 128;
//    private static final int  DEFAULT_NUM_TREES = 256;


    //--------------------------------------------------------------------
    private final int NUM_TREES;


    //--------------------------------------------------------------------
    public RandomLearner()
    {
        this(DEFAULT_NUM_TREES);
    }

    public RandomLearner(int numTrees)
    {
        NUM_TREES = numTrees;
    }


    //--------------------------------------------------------------------
    private           RandomTree       trees[];
    private           Multistate       targetAttribute;
    private transient LocalLearningSet totalSet;


    //--------------------------------------------------------------------
    public synchronized void set(LocalLearningSet ls)
    {
        trees = new RandomTree[ NUM_TREES ];
        for (int i = 0; i < trees.length; i++)
        {
            trees[ i ] = RandomTree.nextRandom(ls);
        }

        if (ls.isEmpty())
        {
            targetAttribute = null;
            return;
        }
        else
        {
            assert (ls.targetAttribute() instanceof Multistate);
            targetAttribute = (Multistate) ls.targetAttribute();
        }

        totalSet = new LocalLearningSet();
        doAdd( ls );
    }

    public void limitPopulation(int toMostRecent)
    {
        totalSet.forget( toMostRecent );
        set( totalSet );
    }


    //--------------------------------------------------------------------
    public void add(LocalLearningSet ls)
    {
        if (needsInitiation())
        {
            set( ls );
        }
        else if (! ls.isEmpty())
        {
            assert ls.targetAttribute().getClass() ==
                        targetAttribute.getClass();
            doAdd( ls );
        }
    }


    //--------------------------------------------------------------------
    public void add(LocalExample example)
    {
//        if (example == null) return;

        if (needsInitiation())
        {
            LocalLearningSet s = new LocalLearningSet();
            s.add( example );
            set( s );
        }
        else
        {
            assert example.targetAttribute().getClass() ==
                    targetAttribute.getClass();
            doAdd( example );
        }
    }

    private boolean needsInitiation()
    {
         return trees           == null ||
                targetAttribute == null;
    }


    //--------------------------------------------------------------------
    private void doAdd(LocalLearningSet ls)
    {
        for (LocalExample example : ls)
        {
            doAdd( example );
        }
    }

    private void doAdd(LocalExample example)
    {
        totalSet.add( example );
        for (int i = 0; i < trees.length; i++)
        {
            if (! trees[i].updateSatistic(example))
            {
                trees[i] = RandomTree.nextRandom( totalSet );
                for (LocalExample ex : totalSet)
                {
                    trees[i].updateSatistic( ex );
//                    if (! trees[i].updateSatistic( ex ) &&
//                            totalSet.locationCount() > 2)
//                    {
//                        throw new Error(
//                                "can't update existing example: " + ex);
//                    }
                }
            }
        }
    }


    //--------------------------------------------------------------------
    public Classification classify(LocalContext context)
    {
        if (targetAttribute == null) return new Frequency();

        Map<LocalDatum, double[]> rawClasses =
                new HashMap<LocalDatum,double[]>();
        int totalSampleSize = 0;

        for (RandomTree tree : trees)
        {
            Frequency frequency = tree.frequencyAtLeaf( context );

            for (LocalDatum datum : targetAttribute.partition())
            {
                double[] cumProbability = rawClasses.get( datum );
                if (cumProbability == null)
                {
                    cumProbability = new double[1];
                    rawClasses.put(datum, cumProbability);
                }

                cumProbability[0] += frequency.probabilityOf( datum );
            }
            totalSampleSize += frequency.sampleSize();
        }

        int avgSampleSize = (int) Math.ceil((float)
                                    totalSampleSize / trees.length);
        Frequency classification = new Frequency( avgSampleSize );
        for (Map.Entry<LocalDatum, double[]> rawClass :
                rawClasses.entrySet())
        {                         
            classification.addUnsampled(
                    rawClass.getKey(),
                    rawClass.getValue()[0] / trees.length);
        }
        return classification;
    }


    //--------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "RandomLearner with " +
//                    (trees == null ? "no" : trees.length) +
                    NUM_TREES +
               " trees";
    }

    public void printAsForest()
    {
        for (int i = 0; i < trees.length; i++)
        {
            System.out.println(i + ": " + trees[ i ]);
        }
    }
}
