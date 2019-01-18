package ao.ai.model.ml.classify.learner.grid;

import ao.ai.model.common.data.BinGrid;
import ao.ai.model.ml.classify.example.BinClassified;
import ao.ai.model.ml.classify.hypothesis.classifier.grid.BinGridBinClassifier;
import ao.ai.model.ml.classify.learner.bin.BinClassLearner;

import java.util.List;

/**
 * User: alex
 * Date: 4-May-2010
 * Time: 11:10:54 PM
 */
public interface BinGridBinClassLearner
        extends BinClassLearner<BinGrid>
{
    //-------------------------------------------------------------------------
    @Override
    public BinGridBinClassifier learn(
            List<? extends BinClassified<BinGrid>> trainingPoints);
}
