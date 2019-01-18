package ao.ai.ml.model.input.coll;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;
import java.util.List;

/**
 * Date: Sep 1, 2010
 * Time: 11:56:05 PM
 */
public final class Sample<I, O>
        implements Iterable<Example<I, O>>
{
    //------------------------------------------------------------------------
    private final I[] inputs;
    private final O[] outputs;


    //------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    /*package-private*/ Sample(
            List<I> inputList, List<O> outputList)
    {
        assert inputList.size() == outputList.size();

        inputs  = (I[])  inputList.toArray();
        outputs = (O[]) outputList.toArray();
    }


    //------------------------------------------------------------------------
    public I input(int index)
    {
        return inputs[ index ];
    }

    public O output(int index)
    {
        return outputs[ index ];
    }

    public int size()
    {
        return inputs.length;
    }


    //------------------------------------------------------------------------
    public I[] inputs()
    {
        return inputs.clone();
    }

    public O[] outputs()
    {
        return outputs.clone();
    }


    //------------------------------------------------------------------------
    @Override
    public Iterator<Example<I, O>> iterator()
    {
        return new AbstractIterator<Example<I, O>>() {
            private int nextIndex = -1;
            @Override protected Example<I, O> computeNext() {
                return ++nextIndex < size()
                       ? new Example<I,O>(
                            input (nextIndex),
                            output(nextIndex))
                       : endOfData();
            }
        };
    }
}
