package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.MultiClass;

/**
 * Date: Sep 1, 2010
 * Time: 11:34:28 PM
 */
public interface MultiClassifier<I>
{
    //------------------------------------------------------------------------
    public MultiClass classify(I input);


    //------------------------------------------------------------------------
    public static class ConstantDummy<I>
            implements MultiClassifier<I>
    {
        private final MultiClass classificationConstant;

        public ConstantDummy() {
            this( MultiClass.create(0/*, 0*/) );
        }
        public ConstantDummy(MultiClass multiClass) {
            classificationConstant = multiClass;
        }

        @Override public MultiClass classify(I input) {
            return classificationConstant;
        }
    }
}
