package ao.ai.model.ml.v2.theory;

import ao.ai.model.ml.v2.clazz.MultiClass;

/**
 * User: alex
 * Date: 12-May-2010
 * Time: 9:28:53 PM
 */
public interface MultiTheory<I>
{
    //-------------------------------------------------------------------------
    public MultiClass classify(I  input);
}
