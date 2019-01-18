package ao.ai.model.ml.regress;

import ao.ai.model.ml.regress.example.MultiRegressed;
import ao.ai.model.ml.regress.hypothesis.regressor.MultiRegressor;

import java.util.List;

/**
 * User: alex
 * Date: 11-Apr-2010
 * Time: 2:18:01 PM
 */
public interface MultiRegressLearner<I>
{
    //-------------------------------------------------------------------------
    public MultiRegressor<I> learn(
            List<MultiRegressed<I>> trainingPoints);
}