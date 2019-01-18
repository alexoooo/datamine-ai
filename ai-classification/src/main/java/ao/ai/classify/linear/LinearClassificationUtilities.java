package ao.ai.classify.linear;

import ao.ai.ml.model.input.RealList;
import ao.util.math.Calc;

/**
 * User: AO
 * Date: Oct 16, 2010
 * Time: 12:49:24 PM
 */
public class LinearClassificationUtilities
{
    //------------------------------------------------------------------------
    private LinearClassificationUtilities() {}


    //------------------------------------------------------------------------
    public static double hingeLoss(double margin)
    {
        return Math.max(0, 1 - margin);
    }

    public static double norm(RealList vector)
    {
        double sumOfSquares = 0;
        for (int i = 0; i < vector.size(); i++)
        {
            sumOfSquares += Calc.square( vector.get(i) );
        }
        return Math.sqrt( sumOfSquares );
    }
    public static double norm(double[] vector)
    {
        double sumOfSquares = 0;
        for (double value : vector)
        {
            sumOfSquares += value * value;
        }
        return Math.sqrt( sumOfSquares );
    }
}
