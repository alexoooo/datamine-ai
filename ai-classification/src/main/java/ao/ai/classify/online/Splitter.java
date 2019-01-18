package ao.ai.classify.online;

import ao.ai.classify.online.filter.ConstantRealFilter;
import ao.ai.ml.model.input.RealList;
import ao.util.math.rand.Rand;

/**
 * User: AO
 * Date: 12/8/10
 * Time: 10:18 PM
 */
public interface Splitter
{
    //------------------------------------------------------------------------
    boolean         canSplit   ();
    boolean         shouldSplit(RealList input);
    AttributeFilter split      (RealList input);

//    boolean


    //------------------------------------------------------------------------
    class ConstantVariance implements Splitter
    {
        private final int maxMistakes;
        private       int mistakeCount;

        public ConstantVariance(int maxMistakes) {
            this.maxMistakes = maxMistakes;
        }

        @Override
        public boolean canSplit()
        {
            return true;
        }

        @Override
        public boolean shouldSplit(RealList input)
        {
            return ++mistakeCount >= maxMistakes;
        }

        @Override
        public AttributeFilter split(RealList input)
        {
            int attribute = Rand.nextInt( input.size() );
            return new AttributeFilter(attribute,
                    new ConstantRealFilter(
                            input.get( attribute )));
//            return new AttributeFilter( attribute );
        }
    }
}
