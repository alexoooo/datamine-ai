package ao.ai.demo.supervised.classification.fin;

import ao.ai.ml.model.input.RealList;

import java.util.*;

/**
 * Date: 6/19/11
 * Time: 6:22 PM
 */
public class AoFeatureExtractor
        implements FeatureExtractor<RealList, RealList>
{
    //------------------------------------------------------------------------
    private final int memorySize;

    private final LinkedList<RealList> memory =
            new LinkedList<RealList>();


    //------------------------------------------------------------------------
    public AoFeatureExtractor(int memorySize)
    {
        this.memorySize = memorySize;
    }


    //------------------------------------------------------------------------
    @Override
    public RealList extract(RealList from)
    {
        memory.add( from );
        while (memory.size() > memorySize) {
            memory.removeFirst();
        }

        List<Double> features = new ArrayList<Double>();
        for (int i = 0; i < from.size(); i++)
        {
//            extract(i, features, 3, false);
            extract(i, features, 3, true);
//            extract(i, features, memorySize/3, false);
//            extract(i, features, memorySize/2, false);
//            extract(i, features, memorySize, false);

//            int splitCount = 1;
//            for (int j = 1; j <= splitCount; j++)
//            {
//                double splitPercentile =
//                        ((double) j / splitCount);
//                extract(i, features,
//                        (int) Math.ceil(splitPercentile * memorySize));
//            }
        }
        return new RealList(toArray( features ));
    }

    private void extract(
            int          featureIndex,
            List<Double> features,
            int          histSize,
            boolean      highRez)
    {
//        double closingValue = from.get( featureIndex );

        double[] hist = new double[ Math.min(histSize, memory.size()) ];

        ListIterator<RealList> memIter =
                memory.listIterator( memory.size() );

        int histIndex = hist.length - 1;
        while (memIter.hasPrevious() && histIndex >= 0) {
            hist[ histIndex-- ] =
                    memIter.previous().get( featureIndex );
        }

        double discount = 1.0;
        double priceSum = 0;
        double qtySum   = 0;
        double minPrice = Double.POSITIVE_INFINITY;
        double maxPrice = Double.NEGATIVE_INFINITY;
        for (double h : hist) {
            priceSum += discount * h;
            minPrice = Math.min(minPrice, h);
            maxPrice = Math.max(maxPrice, h);
            qtySum += discount;
//            discount *= 0.95;
        }
        double avgPrice = priceSum / qtySum;

        double last = hist[ hist.length - 1 ];
        LinkedList<Double> deltaFeatures = new LinkedList<Double>();
        for (int i = hist.length - 1; i >= 1; i--) {
            double delta = (hist[ i ] / hist[ i - 1 ]) - 1;
//            double delta = (hist[ i ] / last) - 1;
            deltaFeatures.add(delta);
//            features.add(delta >= 0 ? 1.0 : -1.0);
//            features.add(delta * delta);
        }
        while (deltaFeatures.size() < histSize - 1) {
            deltaFeatures.addFirst( 0.0 );
        }

        LinkedList<Double> diffFeatures = new LinkedList<Double>();
        for (int i = hist.length - 1; i >= 1; i--) {
            double f = (hist[ i ] - hist[ i - 1 ]) / hist[ i ];
//            double f = (hist[ i ] - hist[ i - 1 ]) / last;
            diffFeatures.add( f );
        }
        while (diffFeatures.size() < histSize - 1) {
            diffFeatures.addFirst( 0.0 );
        }

        double range = maxPrice - minPrice;
        if (range == 0) {
            features.add( 0.0 );
        } else {
            features.add( (last - minPrice) / range );
        }

        features.add( last/avgPrice - 1 );
        features.add( minPrice/avgPrice - 1 );
        features.add( maxPrice/avgPrice - 1 );

        if (highRez) {
            features.addAll( deltaFeatures );
            features.addAll( diffFeatures );
        }

//        features.add((last - avgPrice) / last);
        features.add(Math.abs((last - avgPrice) / last));
//        Math.abs(closingPrice - avePrice)/closingPrice

//        return new RealList(toArray( features ));
    }


    //------------------------------------------------------------------------
    private double[] toArray(List<Double> values)
    {
        double[] arr = new double[ values.size() ];
        int i = 0;
        for (double v : values) {
            arr[ i++ ] = v;
        }
        return arr;
    }
}
