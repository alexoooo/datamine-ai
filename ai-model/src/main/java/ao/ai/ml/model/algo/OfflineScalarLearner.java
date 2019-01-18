package ao.ai.ml.model.algo;

import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.ScalarTarget;
import ao.ai.ml.model.theory.ScalarRegressor;

/**
 * User: AO
 * Date: Sep 22, 2010
 * Time: 9:35:48 PM
 */
public interface OfflineScalarLearner<I>
{
    //------------------------------------------------------------------------
    public ScalarRegressor<I> learn(
            Sample<I, ScalarTarget> trainingSet);
}
