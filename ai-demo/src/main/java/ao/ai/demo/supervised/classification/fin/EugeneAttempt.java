package ao.ai.demo.supervised.classification.fin;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/19/11
 * Time: 5:45 PM
 */
public class EugeneAttempt
        implements OnlineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    private final List<Double> history = new ArrayList<Double>();


    //------------------------------------------------------------------------
    @Override
    public void learn(
            RealList    closingPrice,
            BinaryClass nextPriceWasHigher)
    {
        learn(closingPrice.get(0), nextPriceWasHigher.isPositive());
    }

    @Override
    public BinaryClass classify(
            RealList closingPrice)
    {
        return BinaryClass.create(
                predictIsNextPriceGoingToBeHigher(
                        closingPrice.get(0)));
    }


    //------------------------------------------------------------------------
    private void learn(
            double  closingPrice,
            boolean nextPriceWasHigher)
    {
        history.add( closingPrice );
    }

    private boolean predictIsNextPriceGoingToBeHigher(
            double closingPrice)
    {
        final int frame = 10;
        if (history.size() < frame) {
            return true;
        }

        double avePrice = 0;

        for (int i = 1; i <= frame; i++)
        {
            avePrice += history.get(history.size() -  i );
        }
        avePrice /= frame;

        double tippingPoint = 0.06;

//        if (closingPrice > history.get(history.size() -2 )) return true;

        if (Math.abs(closingPrice - avePrice)/closingPrice > tippingPoint )
        {
            return false;
        }

        return true;
    }
}
