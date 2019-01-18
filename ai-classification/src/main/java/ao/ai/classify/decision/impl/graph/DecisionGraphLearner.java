package ao.ai.classify.decision.impl.graph;

/**
 *
 */
public class DecisionGraphLearner<T>
        //extends AbstractDecisionLearner<T>
{
//    //--------------------------------------------------------------------
//    private DecisionGraph<T> graph;
//
//
//    //--------------------------------------------------------------------
//    public DecisionGraphLearner() {}
//
//
//    //--------------------------------------------------------------------
//    public synchronized void train(DataSet<T> ds)
//    {
//        graph = induce(ds);
//        System.out.println("graph = " + graph);
//    }
//
//    private DecisionGraph<T> induce(DataSet<T> ds)
//    {
//        DecisionGraph<T> root = new DecisionGraph<T>(ds);
//        double cutoffLength = root.messageLength();
//
//        while (true)
//        {
////            System.out.println("---------------------------------------");
////            System.out.println("progress " + root + " : " + cutoffLength);
//            SplitSet<T> splits = new SplitSet<T>( cutoffLength );
//
//            List<DecisionGraph<T>> leafs = root.leafs();
//            for (DecisionGraph<T> leaf : leafs)
//            {
//                for (AttributeSet<?> attr : leaf.unsplitContexts())
//                {
//                    splits.add(leaf, attr);
//                }
//            }
//            if (splits.shortest() == null) break;
//
//            JoinSet<T> joins = new JoinSet<T>(splits);
//            for (int joinBy = 2;
//                     joinBy <= Math.min(leafs.locationCount(), 12);
//                     joinBy++)
//            {
//                for (DecisionGraph<T> tryJoin[] :
//                        leafPermute(leafs, joinBy))
//                {
//                    joins.add( tryJoin );
//                }
//            }
//
//            ((joins.shortestUseful() == null)
//                    ? splits.shortest()
//                    : joins.shortestUseful()).apply();
//            cutoffLength = root.messageLength();
//        }
//
//        root.freeze();
//        return root;
//    }
//
//    @SuppressWarnings("unchecked")
//    private Iterable<DecisionGraph<T>[]> leafPermute(
//            List<DecisionGraph<T>> leafs, int by)
//    {
//        return new Combiner<DecisionGraph<T>>(
//                    leafs.toArray(
//                            new DecisionGraph[leafs.locationCount()]), by);
//    }
//
//
//
//    //--------------------------------------------------------------------
//    public Histogram<T> regress(Context context)
//    {
//        return graph.regress( context );
//    }
}
