package ao.ai.classify.online;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.pass.Traverser;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:20 PM
 */
/*package-private*/ class Leaf
        implements Node
{
    //------------------------------------------------------------------------
    private double[] counts = new double[ 2 ];


    //------------------------------------------------------------------------
    public Leaf() {}

    private Leaf(double[] copyCounts)
    {
        counts = copyCounts;
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass classification)
    {
        int index = classification.isPositive() ? 1 : 0;

        counts[ index ]++;
    }


    //------------------------------------------------------------------------
    @Override
    public BinaryClass classify(RealList input)
    {
        boolean isPositive =
                counts[ 1 ] >= counts[ 0 ];

        return BinaryClass.create( isPositive );
    }


    //------------------------------------------------------------------------
    @Override
    public int subTreeSize()
    {
        return 1;
    }

    @Override
    public int depth()
    {
        return 1;
    }

    @Override
    public void traverse(Traverser<Node> traverser)
    {
        traverser.traverse( this );
    }

//    @Override public void merge()
//    {
//        throw new UnsupportedOperationException();
//    }


    //------------------------------------------------------------------------
    public Leaf prototype(double factor)
    {
        double[] newCounts = new double[ counts.length ];
        for (int i = 0; i < newCounts.length; i++)
        {
            newCounts[ i ] = counts[ i ] * factor;
        }
        return new Leaf( newCounts );
    }

    public Leaf prototype()
    {
        return new Leaf( counts.clone() );
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        double sum = counts[0] + counts[1];
        if (sum == 0) {
            return "n/a";
        }

        double positivePercent =
                counts[1] / sum;

        return Math.round(positivePercent * 100) + "%";
    }
}
