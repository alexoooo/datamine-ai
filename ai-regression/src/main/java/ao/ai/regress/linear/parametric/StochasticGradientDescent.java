package ao.ai.regress.linear.parametric;


/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 2:40:39 PM
 */
public class StochasticGradientDescent
//        implements RegressionLearner
{
//    //--------------------------------------------------------------------
//    private static final Logger LOG =
//            LoggerFactory.getLogger(
//            		StochasticGradientDescent.class);
//
//    //--------------------------------------------------------------------
//    private static final int    MAX_ASCENTS              = 4;
//	private static final int    MAXIMUM_DESCENTS         = 10 * 1000;
//	private static final int    CONVERGENCE_DIGITS       = 10;
//	private static final int    CONVERGENCE_SIGNIFICANCE = (int)
//			Math.pow(10, CONVERGENCE_DIGITS);
//	private static final double CONVERGENCE_PRECISION    =
//			CONVERGENCE_SIGNIFICANCE / 1000;
//	private static final double CONVERGENCE_THRESHOLD    =
//			1.0 / CONVERGENCE_SIGNIFICANCE;
//
//	private static final double MIN_LEARNING_RATE	     =
//			0.000000000001;
//
//    private int totalTrials;
//
//
//    //--------------------------------------------------------------------
//    @Override
//    public RegressionHypothesis learn(
//            List<? extends Example<? extends NumericalFeatureList,
//                                   ? extends SingleNumericalFeature>>
//                    data)
//    {
//        totalTrials = 0;
//
//        double learningRate = 1.0;
//		RegressionHypothesis hypothesis;
//		while ((hypothesis = learn(data, learningRate)) == null) {
//			learningRate = learningRate / 2;
//
//			if (reachedDeadEnd(learningRate)) {
//                LOG.debug("reached dead end in {} trials", totalTrials);
//                return null;
//            }
//		}
//
//        LOG.debug("found answer in {} trials", totalTrials);
//		return hypothesis;
//    }
//
//    private boolean reachedDeadEnd(double learningRate) {
//		return Double.isInfinite( learningRate ) ||
//				learningRate < MIN_LEARNING_RATE;
//	}
//
//
//    //--------------------------------------------------------------------
//    public RegressionHypothesis learn(
//			List<? extends Example<? extends NumericalFeatureList,
//                                   ? extends SingleNumericalFeature>>
//                    data,
//			double 	learningRate)
//	{
//        if (data.isEmpty()) {
//            return null;
//        }
//		LOG.trace("learning {} data points at {}",
//					data.size(), learningRate);
//
//        Example<? extends NumericalFeatureList,
//                ? extends SingleNumericalFeature>
//                    arbitraryExample = data.value(0);
//		double[] params = new double[
//                arbitraryExample.input().size() + 1];
//
//		int    i;
//        int    consecutiveAscents = 0;
//		double prevDelta = 0;
//		for (i = 0; i < MAXIMUM_DESCENTS; i++) {
//            double delta = descend(params, data, learningRate);
//            if (delta > prevDelta) {
//                if (consecutiveAscents++ > MAX_ASCENTS) {
//                    return null;
//                }
//            } else {
//                consecutiveAscents = 0;
//            }
//
//			prevDelta = delta;
//			if (cantLearn(delta)) return null;
//
//			LOG.trace("Descent {} delta {} :: {}", new Object[]{
//					  i, delta, new LinearHypothesis(
//                            params, arbitraryExample )});
//
//			if (delta < CONVERGENCE_THRESHOLD) break;
//		}
//		LOG.debug("completed in {} with {}", i, prevDelta);
//
//		round(params);
//		return new LinearHypothesis( params, arbitraryExample );
//	}
//
//	private boolean cantLearn(double delta) {
//		return Double.isNaN(delta) ||
//				Double.isInfinite(delta);
//	}
//
//	private void round(double[] parameters)
//	{
//		// not 100% sure that this preserves maximum precision
//		for (int i = 0; i < parameters.length; i++) {
//			parameters[ i ] = (double) Math.round(
//					parameters[ i ] * CONVERGENCE_PRECISION)
//						  / CONVERGENCE_PRECISION;
//		}
//	}
//
//
//
//	//--------------------------------------------------------------------
//	/**
//	 * @param params from previous iteration
//	 * @param data training set
//	 * @param learningRate algorithm parameter
//     * @return quadratic mean of parameter deltas
//	 */
//	private double descend(
//			double[] params,
//			List<? extends Example<? extends NumericalFeatureList,
//                                   ? extends SingleNumericalFeature>>
//                     data,
//			double	 learningRate)
//	{
//        double[] preParams = params.clone();
//        RegressionHypothesis hypothesis =
//                    new LinearHypothesis( params, null );
//
//        for (Example<? extends NumericalFeatureList,
//                     ? extends SingleNumericalFeature> e : data)
//        {
//			double residue =
//                    e.output().doubleValue() -
//                    hypothesis.regress( e.input() ).doubleValue();
//
//            double pacedResidue = learningRate * residue;
//            params[ 0 ] += pacedResidue; // bias term
//            for (int f = 1; f < params.length; f++) {
//				params[ f ] += pacedResidue *
//                               e.input().doubleValue(f - 1);
//			}
//        }
//
//        totalTrials++;
//		return rootMeanSquares(preParams, params);
//	}
//
//    private double rootMeanSquares(
//			double[] before, double[] after) {
//		assert before.length == after.length;
//
//		double deltaSumOfSquares = 0;
//		for (int i = 0; i < before.length; i++) {
//			double delta = after[i] - before[i];
//			deltaSumOfSquares += delta * delta;
//		}
//
//		return Math.sqrt(deltaSumOfSquares / before.length);
//	}
//
//
//	//--------------------------------------------------------------------
//	@Override public String toString() {
//		return "Stochastic Gradient Descent";
//	}
}
