package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.BinaryScoreClass;
import ao.util.math.rand.Rand;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:32:47 PM
 */
public interface BinaryScoreClassifier<I>
        extends BinaryClassifier<I>
{
    //------------------------------------------------------------------------
//    @Override
//    public BinaryScoreClass regress(I input);


    //------------------------------------------------------------------------
    @Override
    public BinaryScoreClass classify(I input);


    //------------------------------------------------------------------------
    public static class ConstantDummy<I>
            implements BinaryScoreClassifier<I>
    {
        private final BinaryScoreClass classificationConstant;

        public ConstantDummy() {
            this( true );
        }
        public ConstantDummy(boolean isPositive) {
            classificationConstant =
                    BinaryScoreClass.createScored(isPositive);
        }

        @Override public BinaryScoreClass classify(I input) {
            return classificationConstant;
        }
    }

    public static class RandomDummy<I>
            implements BinaryScoreClassifier<I>
    {
        public RandomDummy() {}

        @Override public BinaryScoreClass classify(I input) {
            return BinaryScoreClass.createScored(
                    Rand.nextBoolean());
        }
    }
}
