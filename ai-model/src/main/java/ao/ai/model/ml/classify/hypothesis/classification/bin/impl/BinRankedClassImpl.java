package ao.ai.model.ml.classify.hypothesis.classification.bin.impl;

import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.ml.classify.bin.BinaryClassificationUtils;
import ao.ai.model.ml.classify.hypothesis.classification.bin.BinRankedClassification;

/**
 * User: alex
 * Date: 25-Apr-2010
 * Time: 7:46:02 PM
 */
public class BinRankedClassImpl
        extends    BinClassImpl
        implements BinRankedClassification
{
    //-------------------------------------------------------------------------
    public BinRankedClassImpl(boolean bestClassification)
    {
        super(bestClassification);
    }
    
    public BinRankedClassImpl(BinaryValue bestClassification)
    {
        super(bestClassification);
    }


    //-------------------------------------------------------------------------
    @Override
    public BinaryValue ranked(int rank)
    {
        return BinaryClassificationUtils.fromIndex( rank )
               ? best() : best().invert();
    }


    //-------------------------------------------------------------------------
    @Override
    public int size()
    {
        return 2;
    }


    //-------------------------------------------------------------------------

}
