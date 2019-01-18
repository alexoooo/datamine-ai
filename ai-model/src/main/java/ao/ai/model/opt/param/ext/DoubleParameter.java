package ao.ai.model.opt.param.ext;

import ao.ai.model.opt.param.Parameter;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 6:51:33 AM
 */
public interface DoubleParameter
        extends Parameter
{
    //--------------------------------------------------------------------
    public double doubleValue();


    //--------------------------------------------------------------------
    public static interface Spec
            extends Parameter.Spec
    {
        public double fromInclusive();
        public double upToExclusive();
    }
}
