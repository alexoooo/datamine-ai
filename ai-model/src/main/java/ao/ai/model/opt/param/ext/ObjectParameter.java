package ao.ai.model.opt.param.ext;

import ao.ai.model.opt.param.Parameter;

import java.util.List;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 6:57:07 AM
 */
public interface ObjectParameter<T>
{
    //--------------------------------------------------------------------
    public T value();

    
    //--------------------------------------------------------------------
    public static interface Spec<T>
            extends Parameter.Spec
    {
        public List<T> values();
    }
}
