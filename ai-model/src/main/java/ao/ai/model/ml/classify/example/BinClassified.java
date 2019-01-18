package ao.ai.model.ml.classify.example;

import ao.ai.model.ml.classify.hypothesis.classification.bin.BinClassification;

/**
 * User: alex
 * Date: 25-Apr-2010
 * Time: 7:07:53 PM
 */
public interface BinClassified<I>
        extends Classified<I>
{
    //-------------------------------------------------------------------------
    @Override
    public BinClassification classification();


    //-------------------------------------------------------------------------
    public boolean isPositive();
}
