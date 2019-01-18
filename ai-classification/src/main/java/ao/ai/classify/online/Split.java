package ao.ai.classify.online;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.pass.Traverser;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:32 PM
 */
public class Split
        implements Node
{
//    public interface Timer
//    {
//        //--------------------------------------------------------------------
//        boolean         shouldSplit(RealList input);
//    }
//
//    public interface



    //------------------------------------------------------------------------
    private final Node            a;
    private final Node            b;
    private final AttributeFilter filter;


    //------------------------------------------------------------------------
    public Split(Node a, Node b, AttributeFilter filter)
    {
        this.a      = a;
        this.b      = b;
        this.filter = filter;
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass classification)
    {
        filter.learn( input );

        (filter.accept( input ) ? a : b)
                .learn( input, classification );
    }

    @Override
    public BinaryClass classify(RealList input)
    {
        return (filter.accept( input ) ? a : b)
                .classify( input );
    }

    @Override
    public int subTreeSize()
    {
        return a.subTreeSize() + b.subTreeSize() + 1;
    }

    @Override
    public int depth()
    {
        return Math.max(a.depth(), b.depth()) + 1;
    }

    @Override
    public void traverse(Traverser<Node> traverser)
    {
        traverser.traverse( a );
        traverser.traverse( b );
    }

//    @Override
//    public void merge()
//    {
//        throw new UnsupportedOperationException();
//    }


    //------------------------------------------------------------------------



    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "<[" + a + "] " + filter + " [" + b + "]>";
    }
}
