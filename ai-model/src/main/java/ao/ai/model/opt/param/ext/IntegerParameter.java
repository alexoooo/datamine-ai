package ao.ai.model.opt.param.ext;

import ao.ai.model.opt.param.Parameter;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 6:54:41 AM
 */
public interface IntegerParameter
        extends Parameter
{
    //--------------------------------------------------------------------
    public int value();


    //--------------------------------------------------------------------
    public static interface Spec
            extends Parameter.Spec
    {
        public int fromInclusive();

        public int upToExclusive();
    }
}
