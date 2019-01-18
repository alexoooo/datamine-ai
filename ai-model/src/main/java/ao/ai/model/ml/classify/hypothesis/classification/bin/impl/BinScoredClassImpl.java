package ao.ai.model.ml.classify.hypothesis.classification.bin.impl;

import ao.ai.model.ml.classify.bin.BinaryClassificationUtils;
import ao.ai.model.ml.classify.hypothesis.classification.bin.BinScoredClassification;

/**
 * User: alex
 * Date: 25-Apr-2010
 * Time: 7:44:57 PM
 */
public class BinScoredClassImpl
        extends    BinRankedClassImpl
        implements BinScoredClassification
{
    //-------------------------------------------------------------------------
    private double  trueScore;
    private double falseScore;


    //-------------------------------------------------------------------------
    public BinScoredClassImpl(
            double trueScore,
            double falseScore)
    {
        super(trueScore >= falseScore);

        assert  trueScore >= 0 &&
               falseScore >= 0;

        this. trueScore =  trueScore;
        this.falseScore = falseScore;
    }


    //-------------------------------------------------------------------------
    @Override
    public double score(int rank)
    {
        return BinaryClassificationUtils.fromIndex( rank )
               ? trueScore : falseScore ;
    }


    //-------------------------------------------------------------------------
    public double score(boolean ofValue)
    {
        return   ofValue
               ? trueScore
               : falseScore;
    }
}
