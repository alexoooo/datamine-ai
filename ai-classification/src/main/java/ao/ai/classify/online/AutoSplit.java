package ao.ai.classify.online;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.misc.Factory;
import ao.util.pass.Traverser;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:45 PM
 */
public class AutoSplit
        implements Node
{
    //------------------------------------------------------------------------
    private Splitter          splitter;
    private Factory<Splitter> splitterFactory;

    private Node delegate;

    private int     learnCount;
    private boolean isSplit;
    private boolean canSplit = true;


    //------------------------------------------------------------------------
    public AutoSplit(Factory<Splitter> splitterFactory) {
        this(new Leaf(), splitterFactory);
    }

    private AutoSplit(Node delegate, Factory<Splitter> splitterFactory)
    {
        this.delegate        = delegate;
        this.splitterFactory = splitterFactory;
//        splitter = new Splitter.ConstantVariance( 1 );
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass classification)
    {
        //BinaryClass prediction = delegate.classify( input );

        if (canSplit) {
            if (splitter == null) {
                splitter = splitterFactory.newInstance();
            }
            canSplit = splitter.canSplit();
        }

        if (! isSplit && canSplit &&
            //! prediction.equals( classification ) &&
            learnCount > 0)
        {
            if (splitter.shouldSplit( input )) {
                Leaf bias = ((Leaf) delegate).prototype( 0.1 );
                delegate = new Split(
                        new AutoSplit( bias            , splitterFactory ),
                        new AutoSplit( bias.prototype(), splitterFactory ),
                        splitter.split( input ));

                splitterFactory = null;
                isSplit = true;
            }
        }
        else if (! canSplit)
        {
            splitter = null;
        }

        delegate.learn(input, classification);
        learnCount++;
    }

    @Override
    public BinaryClass classify(RealList input)
    {
        return delegate.classify(input);
    }

    @Override
    public int subTreeSize()
    {
        return delegate.subTreeSize();
    }

    @Override
    public int depth()
    {
        return delegate.depth();
    }

    @Override
    public void traverse(Traverser<Node> traverser)
    {
        delegate.traverse( traverser );
    }


    //------------------------------------------------------------------------
//    public List<AutoSplit> leafSplits()
//    {
//        final List<AutoSplit> autoSplits = new ArrayList<AutoSplit>();
//        delegate.traverse(new Traverser<Node>() {
//            @Override public void traverse(Node instance)
//            {
//                if (instance instanceof AutoSplit)
//                {
//                    AutoSplit autoSplit = (AutoSplit) instance;
//                    if (autoSplit.isSplit &&
//                            delegate.depth() == 2)
//                    {
//                        autoSplits.add( (AutoSplit) instance );
//                    }
//                }
//            }});
//        return autoSplits;
//    }

//    @Override
//    public void merge()
//    {
//        ((Split) delegate).
//        delegate = new Leaf();
//
//        isSplit  = false;
//        canSplit = true;
//    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return delegate.toString();
    }
}
