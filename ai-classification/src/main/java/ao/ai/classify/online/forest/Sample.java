package ao.ai.classify.online.forest;

import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.output.MultiScoreClass;
import ao.util.math.stats.Info;

import java.util.Arrays;

/**
 * User: AO
 * Date: 12/25/10
 * Time: 2:14 PM
 */
/*package-private*/ class Sample
{
    //------------------------------------------------------------------------
    private double[] counts;


    //------------------------------------------------------------------------
    public Sample()
    {
        this(new double[0]);
    }

    private Sample(double[] copyCounts)
    {
        counts = copyCounts;
    }


    //------------------------------------------------------------------------
    public void learn(MultiClass output)
    {
        if (counts.length <= output.best())
        {
            counts = Arrays.copyOf(counts, output.best() + 1);
        }

        counts[ output.best() ]++;
    }


    //------------------------------------------------------------------------
    public MultiClass predict()
    {
        if (counts.length <= 1)
        {
            return MultiScoreClass.create(0);
        }

        int    maxIndex = 0;
        double maxCount = counts[ 0 ];

        for (int i = 1; i < counts.length; i++)
        {
            double count = counts[ i ];
            if (maxCount < count)
            {
                maxIndex = i;
                maxCount = count;
            }
        }

        return MultiClass.create( maxIndex );
    }


    //------------------------------------------------------------------------
    public double cost()
    {
        return entropy();
    }

    private double entropy()
    {
        double sum = totalCount();

        double entropy = 0;
        for (double count : counts)
        {
            if (count == 0) {
                continue;
            }

            double density = count / sum;
            entropy -= density * Info.log2(density);
        }
        return entropy;
    }


    //------------------------------------------------------------------------
    public double totalCount()
    {
        double sum = 0;
        for (double count : counts)
        {
            sum += count;
        }
        return sum;
    }

//    public Sample defaultTo(Sample bias)
//    {
//        if (totalCount() != 0)
//        {
//            return this;
//        }
//        else
//        {
//
//        }
//    }
    public Sample asBias()
    {
        Sample scaled = prototype();
        scaled.doBias();
        return scaled;
    }
    private void doBias()
    {
//        double sum = totalCount();
//        if (sum == 0) {
//            return;
//        }

        int    maxIndex = -1;
        double maxCount = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < counts.length; i++)
        {
            if (counts[ i ] > maxCount)
            {
                maxCount = counts[ i ];
                maxIndex = i;
            }

            counts[ i ] = 0;
        }

        if (maxIndex != -1)
        {
            counts[ maxIndex ] = Double.MIN_VALUE;
        }
    }

    public Sample prototype()
    {
        return new Sample( counts.clone() );
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString( counts );
    }
}
