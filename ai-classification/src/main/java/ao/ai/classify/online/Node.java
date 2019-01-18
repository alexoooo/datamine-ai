package ao.ai.classify.online;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.pass.Traverser;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:20 PM
 */
/*package-private*/ interface Node
{
    //------------------------------------------------------------------------
    void learn(RealList input, BinaryClass classification);


    //------------------------------------------------------------------------
    BinaryClass classify(RealList input);


    //------------------------------------------------------------------------
    int subTreeSize();
    int depth();


    //------------------------------------------------------------------------
    void traverse(Traverser<Node> traverser);


    //------------------------------------------------------------------------
//    void merge();
}
