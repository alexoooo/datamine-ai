package ao.ai.classify.online.bag;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.misc.Factory;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: AO
 * Date: 12/18/10
 * Time: 10:38 AM
 */
public class OnlineBagging
        implements OnlineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    private final List<OnlineBinaryLearner<RealList>> learners;


    //------------------------------------------------------------------------
    public OnlineBagging(
            Factory<OnlineBinaryLearner<RealList>> learnerFactory,
            int                                    nLearners)
    {
        learners = Lists.newArrayList();

        for (int i = 0; i < nLearners; i++)
        {
            learners.add( learnerFactory.newInstance() );
        }
    }

    public OnlineBagging(
            List<OnlineBinaryLearner<RealList>> learners)
    {
        this.learners = learners;
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass output)
    {
        for (OnlineBinaryLearner<RealList> learner : learners)
        {
            int k = BaggingUtils.randomEventCount();
            for (int i = 0; i < k; i++)
            {
                learner.learn(input, output);
            }
        }
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClass classify(RealList input)
    {
        int nPositive = 0;
        int nNegative = 0;

        for (OnlineBinaryLearner<RealList> learner : learners)
        {
            BinaryClass classification = learner.classify( input );
            if (classification.isPositive()) {
                nPositive++;
            } else {
                nNegative++;
            }
        }

        return BinaryClass.create(
                nPositive > nNegative);
    }
}
