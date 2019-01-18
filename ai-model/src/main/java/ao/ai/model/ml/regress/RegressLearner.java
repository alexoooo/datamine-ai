package ao.ai.model.ml.regress;

import ao.ai.model.ml.regress.example.Regressed;
import ao.ai.model.ml.regress.hypothesis.regressor.Regressor;

import java.util.List;

/**
 * User: alex
 * Date: 11-Apr-2010
 * Time: 2:18:01 PM
 */
public interface RegressLearner<I>
{
    //-------------------------------------------------------------------------
//    public <R extends Regressor<I>> R learn(
//            List<Regressed<I>> trainingPoints);
    
    public Regressor<I> learn(
            List<? extends Regressed<? extends I>> trainingPoints);
}

