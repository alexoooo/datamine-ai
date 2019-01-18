package ao.ai.model.ml.supervised.hypothesis.impl;

/**
 * Created by IntelliJ IDEA.
 * User: Mable
 * Date: Feb 27, 2010
 * Time: 4:26:43 PM
 */
public class AbstractBinaryHypothesis
//        implements BinaryClassificationHypothesis
{
//    //--------------------------------------------------------------------
//    private final double[]       parameters;
//
//    private final FeatureList<? extends FeatureValue>
//                                 sampleInput;
//    private final FeatureType outputType;
//
//
//    //--------------------------------------------------------------------
//    public AbstractBinaryHypothesis(double[] copyParams)
//    {
//        this(copyParams, null);
//    }
//
//    public AbstractBinaryHypothesis(
//            double[] copyParams,
//            Example<? extends FeatureList  <? extends FeatureValue>,
//                                ? extends SingleFeature<? extends FeatureValue>>
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
//    //-------------------------------------------------------------------------
//    @Override
//    public double probabilityOfPositive(
//            NumericalFeatureList input)
//    {
//        assert parameters.length == (input.size() + 1);
//
//		double thetaTransposeX = parameters[0];
//		for (int p = 1; p < parameters.length; p++) {
//			thetaTransposeX +=
//                    parameters[p] * input.doubleValue(p - 1);
//		}
//
//        return logisticFunction(thetaTransposeX);
//    }
//
//
//    //-------------------------------------------------------------------------
//    @Override
//    public SingleBinaryFeature regress(
//            NumericalFeatureList input)
//    {
//        boolean isPositive = Rand.nextBoolean(
//                probabilityOfPositive(input));
//
//        return new BinaryScalar(
//                isPositive, outputType);
//    }
//
//
//    //-------------------------------------------------------------------------
//    @Override
//    public double probabilityOf(NumericalFeatureList input, int categoryIndex)
//    {
//        assert 0 <= categoryIndex &&
//                    categoryIndex < 2;
//
//        return (categoryIndex == 0
//                ? 1.0 - probabilityOfPositive(input)
//                : probabilityOfPositive(input));
//    }
//
//
//    //-------------------------------------------------------------------------
//    @Override
//    public String toString() {
//        if (sampleInput == null || outputType == null) {
//            return Arrays.toString( parameters );
//        }
//
//        StringBuilder str = new StringBuilder();
//        str.append( outputType ).append(" = ");
//
//        str.append("1 / [1 + e^-(");
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
//        str.append(")]");
//
//        return str.toString();
//    }
}
