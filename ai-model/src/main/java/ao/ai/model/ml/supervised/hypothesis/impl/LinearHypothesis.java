package ao.ai.model.ml.supervised.hypothesis.impl;

/**
 * User: aostrovsky
 * Date: 30-Jan-2010
 * Time: 8:06:53 PM
 */
public class LinearHypothesis
//        implements RegressionHypothesis
{
//    //--------------------------------------------------------------------
//    private final double[]       parameters;
//
//    private final FeatureList<? extends FeatureValue>
//                                 sampleInput;
//    private final FeatureType    outputType;
//
//
//    //--------------------------------------------------------------------
//    public LinearHypothesis(double[] copyParams)
//    {
//        this(copyParams, null);
//    }
//
//    public LinearHypothesis(
//            double[] copyParams,
//            Example<? extends FeatureList<? extends FeatureValue>,
//                    ? extends SingleFeature<? extends FeatureValue>>
//                representativeExample)
//    {
//        parameters = copyParams;
//
//        if (representativeExample == null)
//        {
//            outputType  = null;
//            sampleInput = null;
//        }
//        else
//        {
//            outputType  = representativeExample.output().type();
//            sampleInput = representativeExample.input();
//
//            assert parameters.length == (sampleInput.size() + 1);
//        }
//    }
//
//
//    //--------------------------------------------------------------------
//    @Override
//    public SingleNumericalFeature regress(
//            NumericalFeatureList input)
//    {
//        assert parameters.length == (input.size() + 1);
//
//		double estimate = parameters[0];
//		for (int p = 1; p < parameters.length; p++) {
//			estimate += parameters[p] * input.doubleValue(p - 1);
//		}
//		return new FeatureScalar(estimate, outputType);
//    }
//
//
//    //--------------------------------------------------------------------
//    @Override
//    public String toString() {
//        if (sampleInput == null || outputType == null) {
//            return Arrays.toString( parameters );
//        }
//
//        StringBuilder str = new StringBuilder();
//        str.append( outputType ).append(" = ");
//
//        for (int i = parameters.length - 1; i >= 0; i--)
//        {
//            str.append( parameters[i] );
//
//            if (i > 0)
//            {
//               str.append( "*" )
//                  .append( sampleInput.type(i - 1) )
//                  .append( " + " );
//            }
//        }
//
//        return str.toString();
//    }
}
