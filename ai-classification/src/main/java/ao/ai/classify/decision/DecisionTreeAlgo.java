package ao.ai.classify.decision;

import ao.ai.classify.decision.impl.classification.raw.Prediction;
import ao.ai.classify.decision.impl.input.raw.example.Context;
import ao.ai.classify.decision.impl.input.raw.example.ContextImpl;
import ao.ai.classify.decision.impl.input.raw.example.Datum;
import ao.ai.classify.decision.impl.input.raw.example.LearningSet;
import ao.ai.classify.decision.impl.model.processed.LocalClassifier;
import ao.ai.classify.decision.impl.model.raw.Classifier;
import ao.ai.classify.decision.impl.model.raw.ClassifierImpl;
import ao.ai.model.common.data.NumCatList;
import ao.ai.model.common.data.NumList;
import ao.ai.model.common.data.impl.NumCatListImpl;
import ao.ai.model.ml.v2.algo.BinAlgo;
import ao.ai.model.ml.v2.algo.MultiAlgo;
import ao.ai.model.ml.v2.algo.impl.BinMultiAlgo;
import ao.ai.model.ml.v2.clazz.BinClass;
import ao.ai.model.ml.v2.clazz.MultiClass;
import ao.ai.model.ml.v2.clazz.impl.MultiClassImpl;
import ao.ai.model.ml.v2.example.Example;
import ao.ai.model.ml.v2.example.impl.ExampleImpl;
import ao.ai.model.ml.v2.theory.BinTheory;
import ao.ai.model.ml.v2.theory.MultiTheory;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 12:56:47 PM
 */
public class DecisionTreeAlgo
        implements MultiAlgo<NumCatList>
{
    //-------------------------------------------------------------------------
    private static final Logger LOG = LoggerFactory.getLogger(
            DecisionTreeAlgo.class);


    //-------------------------------------------------------------------------
    public static BinAlgo<NumList> create(
            LocalClassifier.Factory classifierFactoryImpl)
    {
        final BinAlgo<NumCatList> binAlgo =
                new BinMultiAlgo<NumCatList>(
                        new DecisionTreeAlgo(
                                classifierFactoryImpl));

        return new BinAlgo<NumList>() {
            @Override public BinTheory<NumList> learn(
                    List<? extends Example<NumList, BinClass>> data)
            {
                List<Example<NumCatList, BinClass>>
                        generalData = Lists.newArrayList();

                for (Example<NumList, BinClass> example : data)
                {
                    generalData.add(
                            new ExampleImpl<NumCatList, BinClass>(
                                    new NumCatListImpl(
                                            example.input().getDoubles()),
                                    example.output()));
                }

                final BinTheory<NumCatList> theory =
                        binAlgo.learn( generalData );

                return new BinTheory<NumList>() {
                    @Override public BinClass classify(NumList input) {
                        return theory.classify(
                                new NumCatListImpl(
                                        input.getDoubles()));
                    }};
            }
        };
    }


    //-------------------------------------------------------------------------
    private final LocalClassifier.Factory classifierFactory;


    //-------------------------------------------------------------------------
    public DecisionTreeAlgo(
            LocalClassifier.Factory classifierFactoryImpl)
    {
        classifierFactory = classifierFactoryImpl;
    }


    //-------------------------------------------------------------------------
    @Override
    public MultiTheory<NumCatList> learn(
            List<? extends Example<NumCatList, MultiClass>> data)
    {
        assert data != null && ! data.isEmpty();

        final LearningSet examples = new LearningSet();
        final Classifier learner  =
                new ClassifierImpl(
                        classifierFactory.newInstance());

        int maxCategory = 0;
        for (Example<NumCatList, MultiClass> example : data)
        {
            examples.add(function(
                    example.output().best(),
                    example.input()));

            if (example.output().best() > maxCategory)
            {
                maxCategory = example.output().best();
            }
        }

        LOG.trace("Converted examples, took: {}", examples);
        learner.set( examples );

        final int nTarget = maxCategory + 1;
        return new MultiTheory<NumCatList>() {
            @Override
            public MultiClass classify(NumCatList input)
            {
                Prediction guess = learner.classify(
                        context( input ));

                double maxProb      = Double.NEGATIVE_INFINITY;
                int    maxProbIndex = 0;

                for (int i = 0; i < nTarget; i++)
                {
                    double prob = guess.probabilityOf( target(i) );

                    if (prob > maxProb)
                    {
                        maxProb      = prob;
                        maxProbIndex = i;
                    }
                }

                return MultiClassImpl.create(
                        maxProbIndex);
            }
        };
    }


    //-------------------------------------------------------------------------
    private ao.ai.classify.decision.impl.input.raw.example.Example
            function(int         bestCategory,
                     NumCatList input)
    {
        return context(input).withTarget( target(bestCategory) );
    }

    private Context context(NumCatList vars)
    {
        List<Datum> varAttributes = new LinkedList<Datum>();

        for (int i = 0; i < vars.size(); i++)
        {
            if (vars.isNum( i ))
            {
                varAttributes.add(new Datum(
                        typeOf( i ), vars.getNum( i )));
            }
            else
            {
                varAttributes.add(new Datum(
                        typeOf( i ), vars.getEnum( i )));
            }
        }

        return new ContextImpl( varAttributes );
    }


    //-------------------------------------------------------------------------
    private static Datum[] targets = initTargets( 1024 );

    private static Datum[] initTargets(int size)
    {
        Datum[] t = new Datum[ size ];
        for (int i = 0; i < size; i++)
        {
            t[ i ] = computeTarget( i );
        }
        return t;
    }

    private static Datum computeTarget(int index)
    {
        return new Datum( "target", typeOf(index) );
    }

    private static Datum target(int index)
    {
        return index < targets.length
               ? targets[ index ]
               : computeTarget( index );
    }


    //-------------------------------------------------------------------------
    private static String typeOf(int index)
    {
        return String.valueOf( index );
    }
}
