package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OfflineMultiLearner;
import ao.ai.ml.model.algo.OnlineMultiLearner;
import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.theory.MultiClassifier;
import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 3:49:29 PM
 */
public class OnlineToOfflineMultiLearner<I>
        implements OfflineMultiLearner<I>
{
    //------------------------------------------------------------------------
    private final int                   iterations;
    private final OnlineMultiLearner<I> delegate;


    //------------------------------------------------------------------------
    public OnlineToOfflineMultiLearner(
            OnlineMultiLearner<I> delegate)
    {
        this(delegate, 10);
    }

    public OnlineToOfflineMultiLearner(
            OnlineMultiLearner<I> delegate,
            int iterations)
    {
        Preconditions.checkNotNull ( delegate );
        Preconditions.checkArgument( iterations > 0 );

        this.delegate   = delegate;
        this.iterations = iterations;
    }


    //------------------------------------------------------------------------
    @Override
    public MultiClassifier<I> learn(
            Sample<I, MultiClass> trainingSet)
    {
        for (int i = 0; i < iterations; i++)
        {
            learnOnce( trainingSet );

            if ((i != iterations - 1) &&
                    ! makesErrors( trainingSet ))
            {
                System.out.println("Done at iteration: " + (i + 1));
                break;
            }
        }

        return delegate;
    }

    private void learnOnce(Sample<I, MultiClass> trainingSet)
    {
        for (int i = 0; i < trainingSet.size(); i++)
        {
            delegate.learn(
                    trainingSet.input ( i ),
                    trainingSet.output( i ));
        }
    }

    private boolean makesErrors(
            Sample<I, MultiClass> trainingSet)
    {
        for (int i = 0; i < trainingSet.size(); i++)
        {
            if (delegate.classify(
                        trainingSet.input ( i )).best() !=
                    trainingSet.output( i ).best())
            {
                return true;
            }
        }

        return false;
    }
}
