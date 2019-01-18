package ao.ai.ml.model.algo;

import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.VectorTarget;
import ao.ai.ml.model.theory.VectorRegressor;

/**
 * User: AO
 * Date: Sep 22, 2010
 * Time: 9:35:48 PM
 */
public interface OfflineVectorLearner<I>
{
    //------------------------------------------------------------------------
    public VectorRegressor<I> learn(
            Sample<I, VectorTarget> trainingSet);
}
