package ao.ai.model.ml.v2.theory;

import ao.ai.model.ml.v2.clazz.BinClass;

/**
 * User: alex
 * Date: 12-May-2010
 * Time: 9:28:24 PM
 */
public interface BinTheory<I>
{
    //-------------------------------------------------------------------------
    public BinClass classify(I  input);
}
