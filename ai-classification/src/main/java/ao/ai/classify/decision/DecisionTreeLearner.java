package ao.ai.classify.decision;

import ao.ai.classify.decision.impl.classification.raw.Prediction;
import ao.ai.classify.decision.impl.input.raw.example.Context;
import ao.ai.classify.decision.impl.input.raw.example.ContextImpl;
import ao.ai.classify.decision.impl.input.raw.example.Datum;
import ao.ai.classify.decision.impl.input.raw.example.LearningSet;
import ao.ai.classify.decision.impl.model.processed.LocalClassifier;
import ao.ai.classify.decision.impl.model.raw.Classifier;
import ao.ai.classify.decision.impl.model.raw.ClassifierImpl;
import ao.ai.ml.model.algo.OfflineBinaryLearner;
import ao.ai.ml.model.algo.OfflineMultiLearner;
import ao.ai.ml.model.algo.adapt.OfflineMultiToBinaryLeaner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.input.coll.DataSet;
import ao.ai.ml.model.input.coll.Example;
import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.output.MultiClassBuilder;
import ao.ai.ml.model.output.MultiClassSet;
import ao.ai.ml.model.theory.MultiClassifier;
import ao.ai.model.common.data.NumCatList;
import ao.ai.model.common.data.impl.NumCatListImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * User: alex ostrovsky
 * Date: 16-May-2010
 * Time: 12:56:47 PM
 */
public class DecisionTreeLearner
        implements OfflineMultiLearner<NumCatList>
{
    //-------------------------------------------------------------------------
    private static final Logger LOG = LoggerFactory.getLogger(
            DecisionTreeLearner.class);


    //-------------------------------------------------------------------------
    public static OfflineBinaryLearner<RealList> createBinary(
            LocalClassifier.Factory classifierFactoryImpl)
    {
        return new OfflineMultiToBinaryLeaner<RealList>(
                createMulti( classifierFactoryImpl ));
    }

    public static OfflineMultiLearner<RealList> createMulti(
            LocalClassifier.Factory classifierFactoryImpl)
    {
        final OfflineMultiLearner<NumCatList> binAlgo =
                        new DecisionTreeLearner(
                                classifierFactoryImpl);

        return new OfflineMultiLearner<RealList>() {
            @Override public MultiClassifier<RealList> learn(
                    Sample<RealList, MultiClass> data)
            {
                DataSet<NumCatList, MultiClass>
                        generalData = new DataSet<NumCatList, MultiClass>();

                for (Example<RealList, MultiClass> example : data)
                {
                    generalData.add(
                            new NumCatListImpl(
                                    example.input().toArray()),
                            example.output());
                }

                final MultiClassifier<NumCatList> theory =
                        binAlgo.learn( generalData.toSample() );

                return new MultiClassifier<RealList>() {
                    @Override public MultiClass classify(RealList input) {
                        return theory.classify(
                                new NumCatListImpl(
                                        input.toArray()));
                    }
                };
            }
        };
    }


    //-------------------------------------------------------------------------
    private final LocalClassifier.Factory classifierFactory;


    //-------------------------------------------------------------------------
    public DecisionTreeLearner(
            LocalClassifier.Factory classifierFactoryImpl)
    {
        classifierFactory = classifierFactoryImpl;
    }


    //-------------------------------------------------------------------------
    @Override
    public MultiClassifier<NumCatList> learn(
            Sample<NumCatList, ao.ai.ml.model.output.MultiClass> data)
    {
        assert data != null && data.size() > 0;

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

        final int           nTarget    = maxCategory + 1;
        final MultiClassSet categories =
                new MultiClassBuilder()
                        .addNext( nTarget )
                        .build();

        return new MultiClassifier<NumCatList>() {
            @Override
            public MultiClass classify(NumCatList input)
            {
                Prediction guess = learner.classify(
                        context( input ));

                double maxProb      = Double.NEGATIVE_INFINITY;
                int    maxProbIndex = 0;

                for (int i = 0; i < nTarget; i++)
                {
                    double prob =
                            guess.sampleSize() == 0
                            ? 1 : guess.probabilityOf( target(i) );

                    if (prob > maxProb)
                    {
                        maxProb      = prob;
                        maxProbIndex = i;
                    }
                }

                return categories.get(
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
