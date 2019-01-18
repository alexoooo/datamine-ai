package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;

/**
 * User: AO
 * Date: 12/27/10
 * Time: 9:31 PM
 */
/*package-private*/ interface Node
{
    //------------------------------------------------------------------------
    void considerSplit(
            RealList   input,
            MultiClass output);

    Node learn(
            Param      param,
            RealList   input,
            MultiClass output);

    MultiClass predict(
            RealList input);


    //------------------------------------------------------------------------
    int size();
    String toString(int depth);
}
