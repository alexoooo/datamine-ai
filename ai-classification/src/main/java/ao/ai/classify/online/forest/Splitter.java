package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;

/**
 * User: AO
 * Date: 1/30/11
 * Time: 4:04 PM
 */
public interface Splitter
{
    //------------------------------------------------------------------------
    boolean isSingular();


    //------------------------------------------------------------------------
    void consider(RealList input, MultiClass output);


    //------------------------------------------------------------------------
    Split split();
}
