package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.MultiProbClass;

/**
 * Date: Sep 1, 2010
 * Time: 11:37:00 PM
 */
public interface MultiProbClassifier<I>
        extends MultiScoreClassifier<I>
{
    //------------------------------------------------------------------------
    @Override
    public MultiProbClass classify(I input);
}
