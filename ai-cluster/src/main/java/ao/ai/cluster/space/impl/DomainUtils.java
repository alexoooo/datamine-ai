package ao.ai.cluster.space.impl;

import ao.util.data.primitive.DoubleList;

/**
 * User: alex
 * Date: 5-Jul-2009
 * Time: 7:14:57 PM
 */
public class DomainUtils
{
    //--------------------------------------------------------------------
    private DomainUtils() {}


    //--------------------------------------------------------------------
    public static void normalize(DoubleList data)
    {
        if (data.size() == 0) return;

        double sum = 0;
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < data.size(); i++)
        {
            double datum = data.get(i);

            if (datum < min) {
                min = datum;
            }
            if (datum > max) {
                max = datum;
            }
            sum += datum;
        }

        double normAvg = sum / data.size() - min;
        if (normAvg == 0)
        {
            for (int i = 0; i < data.size(); i++)
            {
                data.set(i, 1);
            }
        }
        else if (normAvg < 0.001)
        {
            for (int i = 0; i < data.size(); i++)
            {
                data.set(i, data.get(i) - min);
            }
        }
        else
        {
            for (int i = 0; i < data.size(); i++)
            {
                data.set(i, (data.get(i) - min)/normAvg);
            }
        }
    }
}
