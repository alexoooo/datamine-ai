package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;
import ao.util.math.rand.Rand;
import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * User: AO
 * Date: 1/30/11
 * Time: 4:07 PM
 */
public class ListSplitter implements Splitter
{
    //------------------------------------------------------------------------
    private final int index;

    private double    [] inputs;
    private MultiClass[] outputs;


    //------------------------------------------------------------------------
    public ListSplitter(int index)
    {
        this.index = index;
    }


    //------------------------------------------------------------------------
    @Override
    public boolean isSingular()
    {
        if (inputs == null || inputs.length < 2) {
            return true;
        }

        double[] minMax = minMax();
        return minMax[0] != minMax[1];
    }


    //------------------------------------------------------------------------
    @Override
    public void consider(RealList input, MultiClass output)
    {
        double inputVal = input.get(index);
        if (inputs == null) {
            inputs  = new double    []{ inputVal };
            outputs = new MultiClass[]{ output   };
        } else {
            inputs  = Arrays.copyOf(inputs , inputs .length + 1);
            outputs = Arrays.copyOf(outputs, outputs.length + 1);

            inputs [ inputs .length - 1 ] = inputVal;
            outputs[ outputs.length - 1 ] = output;
        }
    }


    //------------------------------------------------------------------------
    @Override
    public Split split()
    {
        Preconditions.checkNotNull( inputs );

        double[] minMax = minMax();
        double   split  = Rand.nextDouble(minMax[0], minMax[1]);

        Sample left  = new Sample();
        Sample right = new Sample();

        SplitCondition condition =
                new SplitCondition(index, split);

        for (int i = 0; i < inputs.length; i++)
        {
            (condition.isLeft( inputs[i] )
             ? left : right).learn( outputs[i] );
        }

        return new Split(condition, left, right);
    }

    private double[] minMax()
    {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;

        for (double input : inputs) {
            min = Math.min(min, input);
            max = Math.max(max, input);
        }

        return new double[]{min, max};
    }
}
