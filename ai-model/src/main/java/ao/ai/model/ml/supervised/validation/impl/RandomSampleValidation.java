package ao.ai.model.ml.supervised.validation.impl;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 10:46:34 AM
 */
public class RandomSampleValidation
//            <I extends FeatureList<? extends FeatureValue>,
//             O extends FeatureList<? extends FeatureValue>>
//        implements Validation<I, O>
{
//    //--------------------------------------------------------------------
//	private static final Logger LOG =
//        LoggerFactory.getLogger(
//        		RandomSampleValidation.class);
//
//
//    //--------------------------------------------------------------------
//    private final Factory<? extends MutableFitnessMeasure<O>>
//            fitnessMeasureFactory;
//
//    private final int    sampleCount;
//    private final double testProbability;
//
//
//
//    //--------------------------------------------------------------------
//    public RandomSampleValidation(
//            Factory<? extends MutableFitnessMeasure<O>>
//                    measureOfFit,
//            int     nSamples,
//            double  testingFraction)
//    {
//        fitnessMeasureFactory = measureOfFit;
//        sampleCount           = nSamples;
//        testProbability       = testingFraction;
//    }
//
//
//    //--------------------------------------------------------------------
//    @Override
//    public <II extends I, OO extends O> FitnessMeasure validate(
//            SupervisedPlainLearner<II, OO> learner,
//            Collection<? extends Example<II, OO>> data)
//    {
//        MutableFitnessMeasure<O> fitness =
//                fitnessMeasureFactory.newInstance();
//
//        for (int i = 0; i < sampleCount; i++)
//        {
//            validate(learner, data, fitness);
//        }
//
//        return fitness;
//    }
//
//
//    //--------------------------------------------------------------------
//    private <II extends I, OO extends O> void validate(
//            SupervisedPlainLearner<II, OO>        learner,
//            Collection<? extends Example<II, OO>> data,
//            MutableFitnessMeasure<O>              fitness)
//    {
//        List<Example<II, OO>> training =
//                new ArrayList<Example<II, OO>>();
//
//        Collection<Example<II, OO>> testing  =
//                new ArrayList<Example<II, OO>>();
//
//        for (Example<II, OO> d : data)
//        {
//            if (Rand.nextBoolean( testProbability ))
//            {
//                testing.add( d );
//            }
//            else
//            {
//                training.add( d );
//            }
//        }
//
//        SupervisedHypothesis<II, OO> predictor =
//                learner.learn( training );
//
//        LOG.debug("\t predictor is {}", predictor);
//
//        for (Example<II, OO> datum : testing)
//        {
//            OO actual    = datum.output();
//            OO predicted = predictor.regress( datum.input() );
//
//            fitness.add(predicted, actual);
//        }
//    }
}
