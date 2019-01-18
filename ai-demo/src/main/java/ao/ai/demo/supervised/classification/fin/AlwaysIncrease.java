package ao.ai.demo.supervised.classification.fin;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/19/11
 * Time: 5:51 PM
 */
public class AlwaysIncrease
        implements OnlineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    @Override
    public void learn(
            RealList    closingPrice,
            BinaryClass nextPriceWasHigher)
    {

    }

    @Override
    public BinaryClass classify(
            RealList closingPrice)
    {
        return BinaryClass.create( true );
    }
}
