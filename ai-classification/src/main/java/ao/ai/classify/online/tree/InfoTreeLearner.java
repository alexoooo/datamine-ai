package ao.ai.classify.online.tree;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;

/**
 * User: AO
 * Date: 12/10/10
 * Time: 10:41 PM
 */
public class InfoTreeLearner
    implements OnlineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    private DecisionNode root;

    private int targetSize;


    //------------------------------------------------------------------------
    public InfoTreeLearner()
    {
        this(10);
    }

    public InfoTreeLearner(int targetSize)
    {
        this.targetSize = targetSize;

        root = new DecisionNode();
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass output)
    {
    }

    @Override
    public BinaryClass classify(RealList input)
    {
        return null;
    }
}
