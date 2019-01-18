package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OfflineBinaryLearner;
import ao.ai.ml.model.algo.OfflineMultiLearner;
import ao.ai.ml.model.input.coll.DataSet;
import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.output.MultiClassSet;
import ao.ai.ml.model.theory.BinaryClassifier;
import ao.ai.ml.model.theory.MultiClassifier;

/**
 * User: AO
 * Date: Oct 3, 2010
 * Time: 6:14:10 PM
 */
public class OfflineMultiToBinaryLeaner<I>
        implements OfflineBinaryLearner<I>
{
    //------------------------------------------------------------------------
    private final OfflineMultiLearner<I> delegate;


    //------------------------------------------------------------------------
    public OfflineMultiToBinaryLeaner(
            OfflineMultiLearner<I> delegate)
    {
        this.delegate = delegate;
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClassifier<I> learn(
            Sample<I, BinaryClass> trainingSet)
    {
        DataSet<I, MultiClass> data = new DataSet<I, MultiClass>();

        MultiClassSet outSet = MultiClassSet.of(
                Boolean.TRUE .toString(),
                Boolean.FALSE.toString());

        for (int i = 0; i < trainingSet.size(); i++)
        {
            data.add(trainingSet.input ( i ), outSet.get(String.valueOf(
                     trainingSet.output( i ).isPositive() )));
        }

        final MultiClassifier<I> classifier = delegate.learn(
                data.toSample());

        return new BinaryClassifier<I>() {
            @Override public BinaryClass classify(I input) {
                MultiClass prediction = classifier.classify( input );
                return BinaryClass.create(
                        prediction.best() == 0);
            }
        };
    }
}
