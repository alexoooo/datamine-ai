package ao.ai.ml.model.input.coll;

import ao.util.data.tuple.Tuples;
import ao.util.data.tuple.TwoTuple;
import ao.util.math.rand.Rand;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: Sep 1, 2010
 * Time: 11:56:17 PM
 */
public final class DataSet<I, O>
{
    //------------------------------------------------------------------------
    private final List<I> inputs;
    private final List<O> outputs;


    //------------------------------------------------------------------------
    public DataSet()
    {
        inputs  = new ArrayList<I>();
        outputs = new ArrayList<O>();
    }


    //------------------------------------------------------------------------
    public void add(I input, O output)
    {
        inputs .add( input  );
        outputs.add( output );
    }

    public void addAll(Iterable<Example<I, O>> examples)
    {
        for (Example<I, O> example : examples)
        {
            add(example.input(), example.output());
        }
    }


    //------------------------------------------------------------------------
    public Sample<I, O> toSample()
    {
        return new Sample<I,O>(inputs, outputs);
    }

    public Sample<I, O> sample(double inclusionProbability)
    {
        List<I> sampleInputs  = new ArrayList<I>();
        List<O> sampleOutputs = new ArrayList<O>();

        for (int i = 0; i < inputs.size(); i++)
        {
            if (Rand.nextBoolean( inclusionProbability ))
            {
                sampleInputs .add( inputs .get(i) );
                sampleOutputs.add( outputs.get(i) );
            }
        }

        return new Sample<I,O>(sampleInputs, sampleOutputs);
    }

    public TwoTuple<Sample<I, O>, Sample<I, O>> split(
            double trainingProbability)
    {
        List<I> trainSampleInputs  = new ArrayList<I>();
        List<O> trainSampleOutputs = new ArrayList<O>();
        List<I> testSampleInputs   = new ArrayList<I>();
        List<O> testSampleOutputs  = new ArrayList<O>();

        for (int i = 0; i < inputs.size(); i++)
        {
            if (Rand.nextBoolean( trainingProbability ))
            {
                trainSampleInputs .add( inputs .get(i) );
                trainSampleOutputs.add( outputs.get(i) );
            }
            else
            {
                testSampleInputs .add( inputs .get(i) );
                testSampleOutputs.add( outputs.get(i) );
            }
        }

        return Tuples.create(
                new Sample<I,O>(trainSampleInputs, trainSampleOutputs),
                new Sample<I,O>( testSampleInputs,  testSampleOutputs));
    }


    //------------------------------------------------------------------------
    public int size()
    {
        return inputs.size();
    }
}
