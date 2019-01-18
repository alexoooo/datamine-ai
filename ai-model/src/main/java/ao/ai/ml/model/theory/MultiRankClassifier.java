package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.MultiRankClass;

/**
 * User: Mable
 * Date: Sep 1, 2010
 * Time: 11:35:10 PM
 */
public interface MultiRankClassifier<I>
        extends MultiClassifier<I>
{
    //------------------------------------------------------------------------
    @Override
    public MultiRankClass classify(I input);
}
