package ao.ai.model.ml.classify.learner.num;

import ao.ai.model.common.data.NumList;
import ao.ai.model.ml.classify.example.BinClassified;
import ao.ai.model.ml.classify.hypothesis.classifier.num.NumBinClassifier;
import ao.ai.model.ml.classify.learner.bin.BinClassLearner;

import java.util.List;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 7:55:30 PM
 */
public interface NumBinClassLearner
        extends BinClassLearner<NumList>
{
    //-------------------------------------------------------------------------
    @Override
    public NumBinClassifier learn(
            List<? extends BinClassified<NumList>> trainingPoints);
}
