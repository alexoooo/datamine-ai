package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;
import ao.util.math.rand.Rand;
import ao.util.text.Txt;

import java.util.Arrays;

/**
 * User: AO
 * Date: 12/27/10
 * Time: 9:47 PM
 */
/*package-private*/ class SplitterLeaf
        implements Node
{
    //------------------------------------------------------------------------
    private final Sample     sample;
    private final Splitter[] splitters;

    private       int   leanCount;


    //------------------------------------------------------------------------
    public SplitterLeaf(Param param)
    {
        this(param, new Sample());
    }

    public SplitterLeaf(Param param, Sample initialSample)
    {
        sample    = initialSample;
        splitters = new Splitter[ param.splitterCount() ];
    }


    //------------------------------------------------------------------------
    @Override
    public void considerSplit(RealList input, MultiClass output)
    {
        if (splitters[0] == null)
        {
            for (int i = 0; i < splitters.length; i++)
            {
                splitters[ i ] =
//                        new MinMaxSplitter(
//                        new ListSplitter(
                        new MinMaxMemSplitter(
                                Rand.nextInt( input.size() ));
            }
        }

        for (Splitter splitter : splitters)
        {
            splitter.consider( input, output );
        }
    }

    @Override
    public Node learn(
            Param param, RealList input, MultiClass output)
    {
        sample.learn(output);

        if (++leanCount >= param.splitAtLearnCount())
        {
            int     nSplits = 0;
            Split[] splits  = new Split[ splitters.length ];
            for (Splitter splitter : splitters)
            {
//                if (! splitter.isSingular()) {
                    splits[ nSplits++ ] = splitter.split();
//                }
            }

            if (nSplits > 0)
            {
                if (splits.length != nSplits) {
                    splits = Arrays.copyOf(splits, nSplits);
                }

                return new SplitLeaf(sample, splits);
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


    //------------------------------------------------------------------------
    @Override
    public String toString(int depth)
    {
        return Txt.nTimes("\t", depth) + toString();
    }

    @Override
    public String toString()
    {
        return sample + " | " + Arrays.toString( splitters );
    }
}
