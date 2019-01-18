package ao.ai.ml.model.algo.adapt.stat;

import ao.ai.ml.model.input.RealList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: AO
 * Date: 11/28/10
 * Time: 12:39 AM
 */
public class DataPreprocessor
{
    //------------------------------------------------------------------------
    private double min   = Double.POSITIVE_INFINITY;
    private double max   = Double.NEGATIVE_INFINITY;

    private int    count = 0;
    private double sum   = 0;
    private double mean  = 0;

    private double std   = 1;
    private double var   = 0;
    private double m2    = 0;

    private List<Double> values = new ArrayList<Double>();


    //------------------------------------------------------------------------
    public void learn(double value)
    {
        min = Math.min(min, value);
        max = Math.max(max, value);

        count++;
        sum += value;

        double delta = value - mean;
        mean += delta / count;
        m2   += delta * (value - mean);

        var = m2 / count;
        std = Math.sqrt( var );

//        values.add( value );
    }


    //------------------------------------------------------------------------
    public double process(double value)
    {
        if (count == 0) {
            return 0;
        }

        Collections.sort( values );

        double mean  = sum / count;
//        double range = (max - min) / 2;
        double range = std;
//        double range =
//                values.get((int)(values.size() * 0.75)) -
//                values.get((int)(values.size() * 0.25));

        if (range == 0) {
            return value == 0 ? 0 : 1.0;
        }

        return (value - mean) / range;
    }

//    public double unProcess(double value)
//    {
//        if (count < 2 || min == max) {
//            return value;
//        }
//
//        double mean  = sum / count;
//        double range = max - min;
//
//        return value * (range / 2) + mean;
//    }


    //------------------------------------------------------------------------
    public static RealList processList(
            DataPreprocessor[] processors,
            RealList           list)
    {
        double[] processed = new double[ processors.length + 1 ];

        for (int i = 0; i < list.size(); i++)
        {
            processed[ i ] = processors[ i ].process(
                    list.get(i));
        }

        processed[ processed.length - 1 ] = 1;

        return new RealList( processed );
    }
}
