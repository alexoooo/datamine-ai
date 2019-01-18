package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OfflineBinaryLearner;
import ao.ai.ml.model.algo.adapt.stat.DataPreprocessor;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.input.coll.DataSet;
import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.theory.BinaryClassifier;
import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 3:56:21 PM
 */
public class OfflineBinaryVectorBiasLearner
        implements OfflineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    private final OfflineBinaryLearner<RealList> delegate;

//    private double biasConstant = 1;


    //------------------------------------------------------------------------
    public OfflineBinaryVectorBiasLearner(
            OfflineBinaryLearner<RealList> delegate)
    {
        Preconditions.checkNotNull( delegate );
        this.delegate = delegate;
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClassifier<RealList> learn(
            Sample<RealList, BinaryClass> trainingSet)
    {
        if (trainingSet.size() == 0) {
            return new BinaryClassifier.Dummy<RealList>();
        }

        DataSet<RealList, BinaryClass> biasedTrainingSet =
                new DataSet<RealList, BinaryClass>();

        final DataPreprocessor[] processors = new DataPreprocessor[
                trainingSet.input(0).size()];
        for (int i = 0; i < trainingSet.size(); i++) {
            RealList input = trainingSet.input( i );
            for (int j = 0; j < input.size(); j++) {
                if (processors[ j ] == null) {
                    processors[ j ] = new DataPreprocessor();
                }
                processors[ j ].learn( input.get(j) );
            }
        }

        for (int i = 0; i < trainingSet.size(); i++)
        {
            biasedTrainingSet.add(
                    DataPreprocessor.processList(
                            processors, trainingSet.input( i )),
                    trainingSet.output( i ));
        }

        final BinaryClassifier<RealList> algo =
                delegate.learn( biasedTrainingSet.toSample() );

        return new BinaryClassifier<RealList>() {
            @Override public BinaryClass classify(RealList input)
            {
                return algo.classify( DataPreprocessor.processList(
                            processors, input) );
            }
        };
    }


    //------------------------------------------------------------------------
//    private RealList addBiasConstant(RealList input)
//    {
////        double[] biasedInput = new double[ input.size() + 1 ];
////
////        biasedInput[ 0 ] = 1;
////        for (int i = 1; i < biasedInput.length; i++)
////        {
////            biasedInput[ i ] = input.get( i - 1 );
////        }
//
//        double[] biasedInput = Arrays.copyOf(
//                input.toArray(), input.size() + 1);
//
////        for (int i = 0; i < input.size(); i++)
////        {
////            biasConstant = Math.max(biasConstant,
//////                    biasedInput[ i ] * biasedInput[ i ]);
////                    Math.abs(biasedInput[ i ]));
////        }
//
////        biasedInput[ biasedInput.length - 1 ] = biasConstant;
//        biasedInput[ biasedInput.length - 1 ] = 1000;
//
//        return new RealList( biasedInput );
//    }
}
