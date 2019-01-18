package ao.ai.classify.decision;

import ao.ai.ml.model.output.BinaryProbClass;
import ao.ai.ml.model.theory.BinaryProbClassifier;
import ao.util.math.rand.Rand;

/**
 * User: AO
 * Date: Oct 3, 2010
 * Time: 3:43:56 PM
 */
public class RandomBinaryClassifier<T>
        implements BinaryProbClassifier<T>
{
    //------------------------------------------------------------------------
//    private int positiveCount;
//    private int totalCount;


    //------------------------------------------------------------------------
    @Override
    public BinaryProbClass classify(T input)
    {
        return BinaryProbClass.createProb(
                Rand.nextDouble());
    }
}
