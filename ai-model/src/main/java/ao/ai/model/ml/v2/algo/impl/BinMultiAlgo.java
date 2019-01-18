package ao.ai.model.ml.v2.algo.impl;

import ao.ai.model.ml.v2.algo.BinAlgo;
import ao.ai.model.ml.v2.algo.MultiAlgo;
import ao.ai.model.ml.v2.clazz.BinClass;
import ao.ai.model.ml.v2.clazz.MultiClass;
import ao.ai.model.ml.v2.clazz.impl.BinClassImpl;
import ao.ai.model.ml.v2.clazz.impl.MultiClassImpl;
import ao.ai.model.ml.v2.example.Example;
import ao.ai.model.ml.v2.example.impl.ExampleImpl;
import ao.ai.model.ml.v2.theory.BinTheory;
import ao.ai.model.ml.v2.theory.MultiTheory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 2:17:32 PM
 */
public class BinMultiAlgo<I>
        implements BinAlgo<I>
{
    //-------------------------------------------------------------------------
    private final MultiAlgo<I> delegate;


    //-------------------------------------------------------------------------
    public BinMultiAlgo(MultiAlgo<I> delegate)
    {
        this.delegate = delegate;
    }


    //-------------------------------------------------------------------------
    @Override
    public BinTheory<I> learn(
            List<? extends Example<I, BinClass>> data)
    {
        List<Example<I, MultiClass>> multiData =
                new ArrayList<Example<I, MultiClass>>();

        for (Example<I, BinClass> example : data)
        {
            multiData.add(
                    new ExampleImpl<I, MultiClass>(
                            example.input(),
                            MultiClassImpl.create(
                                    example.output().best())));
        }

        delegate.learn( multiData );

        final MultiTheory<I> theory =
                delegate.learn( multiData );
        return new BinTheory<I>() {
            @Override public BinClass classify(I input) {
                return BinClassImpl.create(
                        theory.classify(
                                input
                        ).best());
            }
        };
    }
}
