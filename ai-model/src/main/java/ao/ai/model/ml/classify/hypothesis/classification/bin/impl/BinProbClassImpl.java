package ao.ai.model.ml.classify.hypothesis.classification.bin.impl;

import ao.ai.model.common.value.CategoricalValue;
import ao.ai.model.common.value.ext.BinaryValue;
import ao.ai.model.ml.classify.hypothesis.classification.bin.BinProbClassification;

/**
 * User: alex
 * Date: 25-Apr-2010
 * Time: 7:35:27 PM
 */
public class BinProbClassImpl
        extends BinScoredClassImpl
        implements BinProbClassification
{
    //-------------------------------------------------------------------------
    public BinProbClassImpl(
            double positiveProbability)
    {
        super(probability(positiveProbability),
              1.0 - positiveProbability);
    }

    public BinProbClassImpl(
            double  trueScore,
            double falseScore)
    {
        super(normalizeFirst( trueScore, falseScore),
              normalizeFirst(falseScore,  trueScore));
    }


    //-------------------------------------------------------------------------
    private static double normalizeFirst(
            double first, double withSecond)
    {
        return first / (first + withSecond);
    }

    private static double probability(
            double possibleProbability)
    {
        assert 0 <= possibleProbability &&
                    possibleProbability <= 1;
        return possibleProbability;
    }


    //-------------------------------------------------------------------------
    @Override
    public double probabilityOf(BinaryValue category)
    {
        return score( category.getBin() );
    }

    @Override
    public double probabilityOf(int rank)
    {
        return score( rank );
    }

    @Override
    public double probabilityOf(
            CategoricalValue category)
    {
        assert (category instanceof BinaryValue);
        return probabilityOf(
                (BinaryValue) category);
    }
}