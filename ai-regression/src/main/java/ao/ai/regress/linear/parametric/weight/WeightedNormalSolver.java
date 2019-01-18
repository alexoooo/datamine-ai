package ao.ai.regress.linear.parametric.weight;


/**
 * User: aostrovsky
 * Date: 4-Feb-2010
 * Time: 8:56:48 AM
 */
public class WeightedNormalSolver
//        implements RegressionWeightedLearner
{
//    //--------------------------------------------------------------------
//    private static final Logger LOG =
//            LoggerFactory.getLogger(
//            		WeightedNormalSolver.class);
//
//
//    //--------------------------------------------------------------------
//    @Override
//    public SupervisedHypothesis<
//            NumericalFeatureList, SingleNumericalFeature> learn(
//            List<? extends WeightedExample<
//                    ? extends NumericalFeatureList,
//                    ? extends SingleNumericalFeature>>
//                data)
//    {
//        Example<? extends NumericalFeatureList,
//                        ? extends SingleNumericalFeature>
//                    arbitraryExample = data.value(0);
//
//        int featureCount = arbitraryExample.input().size();
//        RealMatrix features = new Array2DRowRealMatrix(
//				data.size(), featureCount + 1);
//
//        RealMatrix targets  = new Array2DRowRealMatrix(
//								data.size(), 1);
//
//		RealMatrix weights  = new OpenMapRealMatrix(
//				data.size(), data.size());
//
//		for (int i = 0; i < data.size(); i++) {
//			WeightedExample<? extends NumericalFeatureList,
//                            ? extends SingleNumericalFeature>
//                    e = data.value(i);
//
//            features.setEntry(i, 0, 1.0);
//			for (int j = 1; j <= e.input().size(); j++) {
//				features.setEntry(
//                        i, j, e.input().doubleValue( j - 1 ));
//			}
//
//			targets.setEntry(i, 0, e.output().doubleValue());
//			weights.setEntry(i, i, e.weight());
//		}
//
//		RealMatrix weightedFeatures = weights.multiply(features);
//		RealMatrix weightedTargets  = weights.multiply(targets );
//
//		RealMatrix wFeatureTranspose = features.transpose();
//		RealMatrix parameters        =
//				new LUDecompositionImpl(
//						wFeatureTranspose.multiply(
//								weightedFeatures)
//				).getSolver().getInverse()
//					.multiply( wFeatureTranspose )
//					.multiply( weightedTargets   );
//
//		return new LinearHypothesis( parameters.getColumn(0) );
//    }
//
//
//	//--------------------------------------------------------------------
//	@Override public String toString() {
//		return "Weighted Normal Equation Solver";
//	}
}