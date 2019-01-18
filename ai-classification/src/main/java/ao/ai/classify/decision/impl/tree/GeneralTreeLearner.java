package ao.ai.classify.decision.impl.tree;

import ao.ai.classify.decision.impl.classification.processed.Classification;
import ao.ai.classify.decision.impl.input.processed.attribute.Attribute;
import ao.ai.classify.decision.impl.input.processed.example.LocalContext;
import ao.ai.classify.decision.impl.input.processed.example.LocalExample;
import ao.ai.classify.decision.impl.input.processed.example.LocalLearningSet;
import ao.ai.classify.decision.impl.model.processed.LocalClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 *
 */
public class GeneralTreeLearner
        implements LocalClassifier, Serializable
{
    //--------------------------------------------------------------------
    private static final long serialVersionUID = 0L;
    
    private static final Logger LOG =
            LoggerFactory.getLogger(
                    GeneralTreeLearner.class);


    //--------------------------------------------------------------------
    public static class Factory implements LocalClassifier.Factory
    {
        public LocalClassifier newInstance()
        {
            return new GeneralTreeLearner();
        }
    }


    //--------------------------------------------------------------------
    private           GeneralTree      tree;
    private transient LocalLearningSet examples;


    //--------------------------------------------------------------------
    public synchronized void set(LocalLearningSet ls)
    {
        examples = ls;
        if (ls.isEmpty()) return;

        tree = induce(ls);
//        tree = new DecisionTree<T>(ds);
//        induce(ds.contextAttributes(), tree);

//        System.out.println(tree + "" + tree.messageLength());
    }

    public void add(LocalLearningSet ls)
    {
        examples.addAll( ls );
        set( examples );
    }

    public void add(LocalExample example)
    {
        LocalLearningSet s = new LocalLearningSet();
        s.add( example );
        add( s );
    }


    //--------------------------------------------------------------------
    private GeneralTree induce(LocalLearningSet ls)
    {
        GeneralTree root          = new GeneralTree(ls);
        double      messageLength = root.messageLength();

        while (true)
        {
            double      mml     = Double.MAX_VALUE;
            Attribute   mmlAttr = null;
            GeneralTree mmlLeaf = null;
            for (GeneralTree leaf : root.leaves())
            {
                for (Attribute attr : leaf.availableAttributes())
                {
                    for (Attribute attrView : attr.views())
                    {
                        leaf.split(attrView);

                        double tentativeLength = root.messageLength();
                        LOG.trace("root: {}, tentativeLength: {}",
                                   root    , tentativeLength);

                        if (mml > tentativeLength)
                        {
                            mml     = tentativeLength;
                            mmlAttr = attrView;
                            mmlLeaf = leaf;
                        }

                        leaf.unsplit();
                    }
                }
            }

            LOG.trace("mml for step: {}", mml);
            if (messageLength > mml)
            {
                assert mmlLeaf != null; // to get rid of warning

                messageLength = mml;
                mmlLeaf.split( mmlAttr );
//                System.out.println("MADE CHOICE!!!!!");
//                System.out.println(root);
            }
            else break;
        }

//        root.freeze();
        return root;
    }


    //--------------------------------------------------------------------
    public Classification classify(LocalContext context)
    {
        return (tree == null ?
                null : tree.classify( context ));
    }


    //--------------------------------------------------------------------
    public void limitPopulation(int toMostRecent)
    {
        examples.forget(toMostRecent);
        set( examples );
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return (tree == null)
                ? "No Tree Learned So Far"
                : tree.toString() + " " + tree.messageLength();
    }
}
