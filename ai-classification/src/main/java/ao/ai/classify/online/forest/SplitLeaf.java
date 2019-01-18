package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;
import ao.util.text.Txt;

import java.util.Arrays;

/**
 * User: AO
 * Date: 12/25/10
 * Time: 2:07 PM
 */
/*package-private*/ class SplitLeaf
        implements Node
{
    //------------------------------------------------------------------------
    private final Sample  sample;
    private final Split[] splits;

    private       int     learnCount;


    //------------------------------------------------------------------------
    public SplitLeaf(
            Sample  sample,
            Split[] splits)
    {
        this.sample = sample;
        this.splits = splits;
    }


    //------------------------------------------------------------------------
    @Override
    public void considerSplit(RealList input, MultiClass output)
    {
//        for (Split split : splits)
//        {
//            split.consider(input, output);
//        }
    }

    @Override
    public Node learn(
            Param param, RealList input, MultiClass output)
    {
        sample.learn( output );

        for (Split split : splits)
        {
            split.learn(input, output);
        }

        if (learnCount++ >= param.fixateAtLearnCount())
        {
            Split  maxSplit = null;
            double maxGain  = Double.NEGATIVE_INFINITY;

            double cost = sample.cost();
            for (Split split : splits)
            {
                double gain = cost - split.cost( learnCount );
                if (maxGain < gain && gain >= param.minSplitGain())
                {
                    maxSplit = split;
                    maxGain  = gain;
                }
            }

            if (maxSplit != null)
            {
                return new SplitNode(
                        param, maxSplit);
            }
        }

        return this;
    }

    @Override
    public MultiClass predict(RealList input)
    {
        return sample.predict();
    }


    //------------------------------------------------------------------------
    @Override
    public int size()
    {
        return 1;
    }

    @Override
    public String toString(int depth)
    {
        return Txt.nTimes("\t", depth) + toString();
    }

    @Override
    public String toString()
    {
        return sample + " | " + Arrays.toString( splits );
    }
}
