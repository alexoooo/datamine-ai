package ao.ai.model.ml.v2.algo;

import ao.ai.model.ml.v2.clazz.MultiClass;
import ao.ai.model.ml.v2.example.Example;
import ao.ai.model.ml.v2.theory.MultiTheory;

import java.util.List;

/**
 * User: alex
 * Date: 12-May-2010
 * Time: 9:50:18 PM
 */
public interface MultiAlgo<I>
{
    //-------------------------------------------------------------------------
    public MultiTheory<I> learn(
              List<? extends Example<I, MultiClass>> data);
}
