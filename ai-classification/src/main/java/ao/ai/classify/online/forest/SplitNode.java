package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;

import static ao.util.text.Txt.nTimes;

/**
 * User: AO
 * Date: 12/27/10
 * Time: 10:14 PM
 */
/*package-private*/ class SplitNode
        implements Node
{
    //------------------------------------------------------------------------
    private final SplitCondition condition;
    private       Node           left;
    private       Node           right;


    //------------------------------------------------------------------------
    public SplitNode(Param param, Split split)
    {
        this.condition = split.condition();

        left  = new SplitterLeaf( param, split.left () );
        right = new SplitterLeaf( param, split.right() );
    }


    //------------------------------------------------------------------------
    @Override
    public void considerSplit(RealList input, MultiClass output)
    {
        side( input ).considerSplit( input, output );
    }

    @Override
    public Node learn(Param param, RealList input, MultiClass output)
    {
        if (condition.isLeft(input)) {
            left  = left .learn(param, input, output);
        } else {
            right = right.learn(param, input, output);
        }

        return this;
    }

    @Override
    public MultiClass predict(RealList input)
    {
        return side( input ).predict( input );
    }


    //------------------------------------------------------------------------
    @Override
    public int size()
    {
        return 1 + left.size() + right.size();
    }


    //------------------------------------------------------------------------
    private Node side(RealList input)
    {
        return (condition.isLeft(input)
                ? left : right);
    }


    //------------------------------------------------------------------------
    @Override
    public String toString(int depth)
    {
        return nTimes("\t", depth) + condition + "\n" +
               left .toString(depth + 1)       + "\n" +
               right.toString(depth + 1);
    }
}
