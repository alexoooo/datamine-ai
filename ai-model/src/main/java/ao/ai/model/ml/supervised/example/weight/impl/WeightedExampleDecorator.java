package ao.ai.model.ml.supervised.example.weight.impl;

import ao.ai.model.common.feature_list.FeatureList;
import ao.ai.model.common.value.FeatureValue;
import ao.ai.model.ml.supervised.example.Example;
import ao.ai.model.ml.supervised.example.weight.WeightedExample;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 7:33:05 AM
 */
public class WeightedExampleDecorator
            <I extends FeatureList<? extends FeatureValue>,
             O extends FeatureList<? extends FeatureValue>>
        implements WeightedExample<I, O>
{
    //--------------------------------------------------------------------
    public static <I extends FeatureList<? extends FeatureValue>,
                   O extends FeatureList<? extends FeatureValue>>
            WeightedExample<I, O> decorate(
                    Example<I, O> example, double weight)
    {
        return new WeightedExampleDecorator<I,O>(example, weight);
    }


    //--------------------------------------------------------------------
    private final Example<I, O> example;
    private final double        weight;


    //--------------------------------------------------------------------
    public WeightedExampleDecorator(
            Example<I, O> example, double weight)
    {
        this.example = example;
        this.weight  = weight;
    }


    //--------------------------------------------------------------------
    @Override
    public double weight()
    {
        return weight;
    }

    @Override
    public I input()
    {
        return example.input();
    }

    @Override
    public O output()
    {
        return example.output();
    }
}
