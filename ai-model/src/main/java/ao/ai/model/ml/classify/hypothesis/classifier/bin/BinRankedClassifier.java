package ao.ai.model.ml.classify.hypothesis.classifier.bin;

import ao.ai.model.ml.classify.hypothesis.classification.bin.BinRankedClassification;

/**
 * User: Mable
 * Date: Apr 3, 2010
 * Time: 2:37:47 PM
 */
public interface BinRankedClassifier<I>
        extends BinClassifier<I>
//        extends BinClassifier<I>,
//                RankedClassifier<I>
{
    //-------------------------------------------------------------------------

    // BinClassifier<I>
//    @Override
//    public BinClassification regress(I input);


    // RankedClassifier<I>
//    @Override
//    public RankedClassification regress(I input);
    

    //-------------------------------------------------------------------------
    @Override
    public BinRankedClassification classify(I input);
}
