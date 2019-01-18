package ao.ai.classify.online.forest;

import ao.ai.classify.online.bag.BaggingUtils;
import ao.ai.ml.model.algo.OnlineMultiLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;
import ao.util.math.rand.Rand;

/**
 * User: AO
 * Date: 12/25/10
 * Time: 2:05 PM
 */
public class OnlineRandomForest
        implements OnlineMultiLearner<RealList>
{
    //------------------------------------------------------------------------
    private int learnCount = 0;

    private Node[] roots;
    private int[]  oobMisses;
    private int[]  oobHits;

    private Param param;


    //------------------------------------------------------------------------
    public OnlineRandomForest()
    {
        this( 128 );
//        this( 150 );
//        this( 1 );
    }

    public OnlineRandomForest(int numTrees)
    {
        this( numTrees, null );
    }

    public OnlineRandomForest(
            int   numTrees,
            Param parameters)
    {
        this.param = parameters;
        this.roots = new Node[ numTrees ];
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, MultiClass output)
    {
        if (param == null)
        {
            param = new Param(
                    2,
                    3,
                    (int) Math.ceil(
                            Math.sqrt( input.size() )),
                    0);
        }
        else if (learnCount > 150)
        {
            param = param.withSplitAtLearnCount(
                    (int) Math.round(learnCount * 0.025));
        }

        if (roots[0] == null)
        {
            for (int i = 0; i < roots.length; i++)
            {
                roots[ i ] = new SplitterLeaf( param );
            }

            oobMisses = new int[ roots.length ];
            oobHits   = new int[ roots.length ];
        }

        for (int i = 0; i < roots.length; i++)
        {
            int k = BaggingUtils.randomEventCount();
//            int k = 1;
            if (k > 0)
            {
                for (int j = 0; j < k; j++)
                {
                    roots[i].considerSplit(input, output);
                    roots[i] = roots[i].learn(param, input, output);
                }
            }
            else
            {
                MultiClass prediction = roots[i].predict(input);
                if (prediction.best() == output.best()) {
                    oobHits  [ i ]++;
                } else {
                    oobMisses[ i ]++;
                }
            }
        }

        learnCount++;
//        if (learnCount % roots.length == 0)
//        if (learnCount % 256 == 0)
        if (Rand.nextBoolean( 256 ))
        {
            purge();
        }

//        int totalNodes = 0;
//        for (Node root : roots) {
//            totalNodes += root.size();
//        }
//        System.out.println(
//                learnCount + "\t" + totalNodes);

//        System.out.println(
//                "--------------------------------------------------------\n" +
//                toString());
    }


    //------------------------------------------------------------------------
    private void purge()
    {
        double minScore = Double.POSITIVE_INFINITY;
        int    minIndex = -1;

        for (int i = 0; i < roots.length; i++)
        {
            int total = oobHits[ i ] + oobMisses[ i ];
            if (total == 0) {
                continue;
            }

            double hitPercentage = ((double) oobHits[ i ]) / total;
            double score = Rand.nextDouble() * hitPercentage;
//            double score = hitPercentage;

            if (score < minScore)
            {
                minScore = score;
                minIndex = i;
            }
        }

        if (minIndex != -1)
        {
            roots    [ minIndex ] = new SplitterLeaf( param );
            oobMisses[ minIndex ] = 0;
            oobHits  [ minIndex ] = 0;
        }
    }


    //------------------------------------------------------------------------
    @Override
    public MultiClass classify(RealList input)
    {
        if (roots == null || roots[0] == null) {
            return MultiClass.create( 0 );
        }

        Sample sample = new Sample();
        for (Node root : roots)
        {
            sample.learn(root.predict(input));
        }
        return sample.predict();
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        if (roots == null || roots[0] == null)
        {
            return "OnlineRandomForest";
        }

        int maxSize      = 0;
        int maxSizeIndex = -1;

        for (int i = 0; i < roots.length; i++)
        {
            int size = roots[ i ].size();
            if (size > maxSize)
            {
                maxSize      = size;
                maxSizeIndex = i;
            }
        }

        return roots[ maxSizeIndex ].size() + " :: " +
               roots[ maxSizeIndex ].toString( 0 );
    }
}
