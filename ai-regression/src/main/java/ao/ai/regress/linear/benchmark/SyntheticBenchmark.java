package ao.ai.regress.linear.benchmark;

/**
 * User: aostrovsky
 * Date: 31-Jan-2010
 * Time: 9:33:51 AM
 */
public class SyntheticBenchmark
{
//    //--------------------------------------------------------------------
//	private static final Logger LOG =
//        LoggerFactory.getLogger(
//        		SyntheticBenchmark.class);
//
//
//	//--------------------------------------------------------------------
//	public static void main(String[] args)
//	{
//		Rand.randomize();
//
//        List<RegressionExample> examples =
//                randomDataSet(1000, 1.0);
//
//        LOG.info("benchmarking {} examples", examples.size());
//        benchmark(examples, new LocallyWeightedRegression());
//        benchmark(examples, new NormalEquationSolver());
//        benchmark(examples, new StochasticGradientDescent());
//        benchmark(examples, new BatchGradientDescent());
//	}
//
//
//	//--------------------------------------------------------------------
//	public static void benchmark(
//			List<RegressionExample> data,
//			RegressionLearner learner)
//	{
//		Stopwatch leanTimer = new Stopwatch();
//        NumericValidation<NumericalFeatureList> numValid =
//                new RandomNumericValidation<NumericalFeatureList>(
//                        10, 0.1);
//        FitnessMeasure fitness = numValid.validate(learner, data);
//
//		LOG.info("{} has fitness {}, took {} ", new Object[]{
//                    learner, fitness, leanTimer});
//	}
//
//
//	//--------------------------------------------------------------------
//	private static List<RegressionExample> randomDataSet(
//			int size, /*RealFunction f, */double noiseFactor)
//	{
//		LOG.info("generating data {}", size);
//
//        FeatureType inputType  = new FeatureTypeImpl("x");
//        FeatureType outputType = new FeatureTypeImpl("y");
//
//        MutableFeatureSet inputFeatureTypes =
//                new FeatureTypeSetImpl();
//        inputFeatureTypes.add(inputType);
//
//		List<RegressionExample> data =
//                new ArrayList<RegressionExample>();
//
//        double coefficient = Rand.nextDouble(-10, 10);
//        double bias        = Rand.nextDouble(-10, 10);
//
//		for (int i = 0; i < size; i++)
//        {
//            double x     = Rand.nextDouble(-10, 10);
//            double noise = (Rand.nextDouble() - 0.5) * noiseFactor;
//            double y     = coefficient * x + bias + noise;
//
//            data.add(new RegressionExampleImpl(
//                    new FeatureScalar(x, inputType),
//                    new FeatureScalar(y, outputType)
//            ));
//
////			data.add(randomExample(f, noise));
//		}
//		return data;
//	}
//
//
////	//--------------------------------------------------------------------
////	private static Example randomExample(
////			RealFunction f, double noise) {
////		return PurturbedExample.purturb(
////					KernelTrick.addQuadratic(
//////					KernelTrick.addSine(
////							RealFunction.Impl.newExample(f)),
////					noise);
////	}
}
