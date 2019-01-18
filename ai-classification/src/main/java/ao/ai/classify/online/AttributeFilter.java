package ao.ai.classify.online;

import ao.ai.classify.online.filter.RealFilter;
import ao.ai.classify.online.filter.VarianceRealFilter;
import ao.ai.ml.model.input.RealList;
import ao.util.math.rand.Rand;

/**
 * User: AO
 * Date: 12/6/10
 * Time: 10:35 PM
 */
public class AttributeFilter
{
    //------------------------------------------------------------------------
    private final int        attributeIndex;
    private final RealFilter filter;


    //------------------------------------------------------------------------
    public AttributeFilter(int attributeIndex)
    {
        this(attributeIndex,
             new VarianceRealFilter( Rand.nextGaussian() ));
    }

    public AttributeFilter(int attributeIndex, RealFilter filter)
    {
        this.attributeIndex = attributeIndex;
        this.filter         = filter;

//        this.filter         = new ConstantRealFilter(250);
//        this.filter         = new VarianceRealFilter( Rand.nextGaussian() );
    }


    //------------------------------------------------------------------------
    public boolean accept(RealList input)
    {
        return filter.accept(
                input.get( attributeIndex ));
    }

    public void learn(RealList input)
    {
        filter.learn(
                input.get( attributeIndex ));
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return attributeIndex + " " + filter.toString();
    }
}
