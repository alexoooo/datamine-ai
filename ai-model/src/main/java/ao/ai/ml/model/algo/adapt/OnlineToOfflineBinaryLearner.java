package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OfflineBinaryLearner;
import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.theory.BinaryClassifier;
import com.google.common.base.Preconditions;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 3:49:29 PM
 */
public class OnlineToOfflineBinaryLearner<I>
        implements OfflineBinaryLearner<I>
{
    //------------------------------------------------------------------------
    private final int                    iterations;
    private final OnlineBinaryLearner<I> delegate;


    //------------------------------------------------------------------------
    public OnlineToOfflineBinaryLearner(OnlineBinaryLearner<I> delegate)
    {
        this(delegate, 10);
    }

    public OnlineToOfflineBinaryLearner(
            OnlineBinaryLearner<I> delegate,
            int iterations)
    {
        Preconditions.checkNotNull ( delegate );
        Preconditions.checkArgument( iterations > 0 );

        this.delegate   = delegate;
        this.iterations = iterations;
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClassifier<I> learn(
            Sample<I, BinaryClass> trainingSet)
    {
        for (int i = 0; i < iterations; i++)
        {
            learnOnce( trainingSet );

            if (! makesErrors( trainingSet ))
            {
                System.out.println("Done at iteration: " + (i + 1));
                break;
            }
        }

        return delegate;
    }

    private void learnOnce(Sample<I, BinaryClass> trainingSet)
    {
        for (int i = 0; i < trainingSet.size(); i++)
        {
            delegate.learn(
                    trainingSet.input ( i ),
                    trainingSet.output( i ));
        }
    }

    private boolean makesErrors(
            Sample<I, BinaryClass> trainingSet)
    {
        for (int i = 0; i < trainingSet.size(); i++)
        {
            if (! delegate.classify(
                        trainingSet.input ( i )).isPositive() ==
                    trainingSet.output( i ).isPositive())
            {
                return true;
            }
        }

        return false;
    }
}
