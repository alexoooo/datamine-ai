package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.MultiScoreClass;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:36:25 PM
 */
public interface MultiScoreClassifier<I>
        extends MultiRankClassifier<I>
{
    //------------------------------------------------------------------------
    @Override
    public MultiScoreClass classify(I input);


    //------------------------------------------------------------------------
    public static class ConstantDummy<I>
            implements MultiScoreClassifier<I>
    {
        private final MultiScoreClass classificationConstant;

        public ConstantDummy() {
            this(MultiScoreClass.createScored( 0 ));
        }

        public ConstantDummy(MultiScoreClass multiScoreClass) {
            classificationConstant = multiScoreClass;
        }

        @Override public MultiScoreClass classify(I input) {
            return classificationConstant;
        }
    }
}
