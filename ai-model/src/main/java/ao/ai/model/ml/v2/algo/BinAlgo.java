package ao.ai.model.ml.v2.algo;

import ao.ai.model.ml.v2.clazz.BinClass;
import ao.ai.model.ml.v2.example.Example;
import ao.ai.model.ml.v2.theory.BinTheory;

import java.util.List;

/**
 * User: alex
 * Date: 12-May-2010
 * Time: 9:49:59 PM
 */
public interface BinAlgo<I>
{
    //-------------------------------------------------------------------------
    public BinTheory<I> learn(
              List<? extends Example<I, BinClass>> data);
}
