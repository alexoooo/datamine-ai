package ao.ai.ml.model.theory;

import ao.ai.ml.model.output.BinaryProbClass;

/**
 * Date: Sep 1, 2010
 * Time: 11:33:45 PM
 */
public interface BinaryProbClassifier<I>
        extends BinaryScoreClassifier<I>
{
    //------------------------------------------------------------------------
//    @Override
//    public BinaryProbClass regress(I input);


    //------------------------------------------------------------------------
    @Override
    public BinaryProbClass classify(I input);
}
