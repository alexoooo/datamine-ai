package ao.ai.ml.model.algo.adapt;

import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * User: AO
 * Date: Oct 9, 2010
 * Time: 3:56:21 PM
 */
public class OnlineBinaryVectorBiasLearner
        implements OnlineBinaryLearner<RealList>
{
    //------------------------------------------------------------------------
    private final OnlineBinaryLearner<RealList> delegate;

    private double biasConstant = 1;


    //------------------------------------------------------------------------
    public OnlineBinaryVectorBiasLearner(
            OnlineBinaryLearner<RealList> delegate)
    {
        Preconditions.checkNotNull( delegate );
        this.delegate = delegate;
    }


    //------------------------------------------------------------------------
    @Override
    public void learn(RealList input, BinaryClass output)
    {
        delegate.learn(addBiasConstant( input ), output);
    }

    @Override
    public BinaryClass classify(RealList input)
    {
        return delegate.classify(
                addBiasConstant( input ));
    }


    //------------------------------------------------------------------------
    private RealList addBiasConstant(RealList input)
    {
//        double[] biasedInput = new double[ input.size() + 1 ];
//
//        biasedInput[ 0 ] = 1;
//        for (int i = 1; i < biasedInput.length; i++)
//        {
//            biasedInput[ i ] = input.get( i - 1 );
//        }

        double[] biasedInput = Arrays.copyOf(
                input.toArray(), input.size() + 1);

        for (int i = 0; i < input.size(); i++)
        {
            biasConstant = Math.max(biasConstant,
//                    biasedInput[ i ] * biasedInput[ i ]);
                    Math.abs(biasedInput[ i ]));
        }

//        biasedInput[ biasedInput.length - 1 ] = biasConstant;
        biasedInput[ biasedInput.length - 1 ] = 1000;

        return new RealList( biasedInput );
    }
}
