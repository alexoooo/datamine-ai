package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.algo.OnlineMultiLearner;
import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.output.MultiClass;

/**
 * User: AO
 * Date: Oct 3, 2010
 * Time: 6:14:10 PM
 */
public class OnlineMultiToBinaryLeaner<I>
        implements OnlineBinaryLearner<I>
{
    //------------------------------------------------------------------------
    private final OnlineMultiLearner<I> delegate;


    //------------------------------------------------------------------------
    public OnlineMultiToBinaryLeaner(
            OnlineMultiLearner<I> delegate)
    {
        this.delegate = delegate;
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(I input, BinaryClass output)
    {
        delegate.learn(input, upCast(output));
    }

    @Override
    public BinaryClass classify(I input)
    {
        MultiClass prediction = delegate.classify( input );
        return downCast( prediction );
    }


    //------------------------------------------------------------------------
    private static MultiClass upCast(
            BinaryClass binaryClass)
    {
        return MultiClass.create(
                binaryClass.isPositive() ? 0 : 1);
    }

    private static BinaryClass downCast(
            MultiClass multiClass)
    {
        return BinaryClass.create(
                multiClass.best() == 0);
    }
}
