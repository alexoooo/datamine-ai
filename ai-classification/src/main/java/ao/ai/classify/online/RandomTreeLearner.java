package ao.ai.classify.online;

import ao.ai.classify.online.filter.FixatingFilter;
import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.math.rand.Rand;
import ao.util.misc.Factory;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:18 PM
 */
public class RandomTreeLearner
        implements OnlineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    private int targetSize;
    private int learnCount = 0;
    private int splitCount = 0;

    private boolean isMistake;


    //------------------------------------------------------------------------
    private final AutoSplit root;


    //------------------------------------------------------------------------
    public RandomTreeLearner()
    {
        this( -1 );
    }

    public RandomTreeLearner(final int targetSize)
    {
        this.targetSize = targetSize;

        root = new AutoSplit(new Factory<Splitter>() {
            @Override public Splitter newInstance() {
                return new RandomSplitter();
            }});
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass output)
    {
        if (targetSize == -1)
        {
            targetSize = input.size() * 2;
        }

//        boolean isMistake =
        isMistake =
                ! root.classify( input ).equals( output );

//        if (isMistake && splitCount == targetSize)
//        {
//            Rand.fromList( root.leafSplits() );
//        }

        learnCount++;

        root.learn(input, output);

//        if (isMistake)
//        {
//            if (splitCount == targetSize)
//            {
//
//            }
//
//            System.out.println( this );
//        }
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClass classify(RealList input)
    {
        return root.classify( input );
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return root.toString();
    }

    private class RandomSplitter implements Splitter
    {
        private int checkCount = 0;

        @Override
        public boolean canSplit()
        {
            return splitCount < targetSize;
        }

        @Override public boolean shouldSplit(RealList input)
        {
            checkCount++;

//            return isMistake || Rand.nextBoolean(
//                    1.0 - Math.sqrt( splitCount ) / (splitCount + 1));
//            return ++localMistakes > Math.sqrt(learnCount);
//            return true;
            return learnCount > 1;
//            return checkCount > Math.sqrt( learnCount );
        }

        @Override
        public AttributeFilter split(RealList input)
        {
            splitCount++;

            int attribute = Rand.nextInt(input.size());
            return new AttributeFilter(attribute,
                       new FixatingFilter());
//                    new ConstantRealFilter(
//                            input.get( attribute )));
//                    return new AttributeFilter( attribute );
        }
    }
}
