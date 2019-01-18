package ao.ai.classify.online.tree;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;

/**
 * User: AO
 * Date: 12/10/10
 * Time: 10:37 PM
 *
 * @param <I> Input type
 */
public class DecisionNode<I>
{
    //------------------------------------------------------------------------
    private double[] counts = new double[ 2 ];


    //------------------------------------------------------------------------
    public boolean isLeaf()
    {
        return true;
    }


    //------------------------------------------------------------------------
    public int subTreeSize()
    {
        return 1;
    }


    //------------------------------------------------------------------------
    public void learn(RealList input, BinaryClass classification)
    {
        int index = classification.isPositive() ? 1 : 0;

        counts[ index ]++;
    }
}
