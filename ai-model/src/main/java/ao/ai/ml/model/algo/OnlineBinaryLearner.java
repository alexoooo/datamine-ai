package ao.ai.ml.model.algo;

import ao.ai.ml.model.output.BinaryClass;
import ao.ai.ml.model.theory.BinaryClassifier;

/**
 * http://www.faqs.org/faqs/ai-faq/neural-nets/part2/section-2.html
 */
public interface OnlineBinaryLearner<I>
        extends BinaryClassifier<I>
//        extends Hypothesis<I, BinaryClass>
{
    //------------------------------------------------------------------------
//    @Override
    public void learn(I input, BinaryClass output);
}
