package ao.ai.regress.linear.non_parametric;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 7:23:56 AM
 */
public class LocallyWeightedHypothesis
//        implements RegressionHypothesis
{
//    //--------------------------------------------------------------------
////    private final double bandwidth = (0.5 + 0.75) / 2;
//    private final double bandwidth = 0.1;
//
//    private final List<
//            ? extends Example<
//                    ? extends NumericalFeatureList,
//                    ? extends SingleNumericalFeature>>
//            data;
//
//
//    //--------------------------------------------------------------------
//    public LocallyWeightedHypothesis(
//            List<? extends Example<
//                    ? extends NumericalFeatureList,
//                    ? extends SingleNumericalFeature>>
//                trainingSet)
//    {
//        data = trainingSet;
//    }
//
//
//    //--------------------------------------------------------------------
//    @Override
//    public SingleNumericalFeature regress(
//            NumericalFeatureList input)
//    {
//        List<WeightedExample<
//                    ? extends NumericalFeatureList,
//                    ? extends SingleNumericalFeature>>
//                weightedInput = Lists.newArrayList();
//
//		double[] deltas = new double [ data.size() ];
//		for (int i = 0; i < data.size(); i++) {
//			deltas[i] = deltaNorm(
//							data.value(i).input().doubleValues(),
//							input.doubleValues());
//		}
//		scale(deltas);
//
//		for (int i = 0; i < data.size(); i++) {
//			double weight = Math.exp(
//					- deltas[i] * deltas[i]
//	                     / (2.0 * bandwidth * bandwidth));
//
//			weightedInput.add(
//					WeightedExampleDecorator.decorate(
//							data.value(i), weight));
//		}
//
//		return new WeightedNormalSolver()
//					 .learn(weightedInput)
//					 .regress(input);
//    }
//
//
//    //--------------------------------------------------------------------
//	private static double deltaNorm(
//			double[] a, double[] b)
//	{
//		assert a.length == b.length;
//
//		double sumOfDeltaSquares = 0;
//		for (int i = 0; i < a.length; i++) {
//			double delta = (a[i] - b[i]);
//			sumOfDeltaSquares += delta * delta;
//		}
//		return Math.sqrt(sumOfDeltaSquares);
//	}
//
//
//	//--------------------------------------------------------------------
//	private static void scale(double[] deltas)
//	{
//		double max = Doubles.max(deltas);
//		for (int i = 0; i < deltas.length; i++) {
//			deltas[i] /= max;
//		}
//	}
//
//
//    //--------------------------------------------------------------------
//	@Override public String toString()
//	{
//		return "Locally Weighted Hypothesis (" +
//					data.size() + ")";
//	}
}
