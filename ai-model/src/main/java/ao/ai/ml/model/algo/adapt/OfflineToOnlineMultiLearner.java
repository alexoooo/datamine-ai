package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OfflineMultiLearner;
import ao.ai.ml.model.algo.OnlineMultiLearner;
import ao.ai.ml.model.input.coll.DataSet;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.theory.MultiClassifier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: AO
 * Date: 2/1/11
 * Time: 10:24 PM
 */
public class OfflineToOnlineMultiLearner<T>
        implements OnlineMultiLearner<T>
{
    //------------------------------------------------------------------------
    private final OfflineMultiLearner<T> delegate;
    private final DataSet<T, MultiClass> training;
    private       MultiClassifier<T>     hypothesis;


    //------------------------------------------------------------------------
    public OfflineToOnlineMultiLearner(
            OfflineMultiLearner<T> delegate)
    {
        this.delegate = checkNotNull( delegate );

        training = new DataSet<T, MultiClass>();
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(T input, MultiClass output)
    {
        training.add(input, output);
        hypothesis = null;
    }

    @Override
    public MultiClass classify(T input)
    {
        if (hypothesis != null)
        {
            return hypothesis.classify( input );
        }

        hypothesis = delegate.learn( training.toSample() );

        return classify( input );
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "[As online: " + hypothesis + "]";
    }
}
