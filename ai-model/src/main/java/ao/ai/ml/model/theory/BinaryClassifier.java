package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.BinaryClass;

/**
 * Date: Sep 1, 2010
 * Time: 11:30:40 PM
 */
public interface BinaryClassifier<I>
        //extends Hypothesis<I, BinaryClass>
{
    //------------------------------------------------------------------------
//    @Override
//    public BinaryClass regress(I input);


    //------------------------------------------------------------------------
    public BinaryClass classify(I input);


    //------------------------------------------------------------------------
    public static class Dummy<I> implements BinaryClassifier<I>
    {
        private final BinaryClass classificationConstant;

        public Dummy() {
            this( true );
        }
        public Dummy(boolean isPositive) {
            classificationConstant = BinaryClass.create( isPositive );
        }

        @Override public BinaryClass classify(I input) {
            return classificationConstant;
        }
    }
}
