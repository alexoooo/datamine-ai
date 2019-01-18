package ao.ai.model.common.value.ext;

import ao.ai.model.common.value.CategoricalValue;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 8:36:22 AM
 */
public interface BinaryValue
        extends CategoricalValue
{
    //--------------------------------------------------------------------
    public boolean getBin();


    //--------------------------------------------------------------------
    public BinaryValue invert();
}
