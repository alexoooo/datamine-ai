package ao.ai.model.ml.classify.example.impl;

import ao.ai.model.common.data.NumList;
import ao.ai.model.common.data.impl.DoubleNumList;
import ao.ai.model.ml.classify.example.ext.NumBinClassified;
import ao.ai.model.ml.classify.hypothesis.classification.bin.BinClassification;
import ao.ai.model.ml.classify.hypothesis.classification.bin.impl.BinClassImpl;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 5:44:59 PM
 */
public class NumBinClassifiedImpl
        implements NumBinClassified
{
    //-------------------------------------------------------------------------
    private final NumList           input;
    private final BinClassification classification;


    //-------------------------------------------------------------------------
    public NumBinClassifiedImpl(
            boolean   isPositive,
            double... inputValues)
    {
        assert inputValues != null;

        input          = new DoubleNumList( inputValues );
        classification = new BinClassImpl(  isPositive  );
    }


    //-------------------------------------------------------------------------
    @Override
    public NumList input()
    {
        return input;
    }

    @Override
    public BinClassification classification()
    {
        return classification;
    }

    @Override
    public boolean isPositive()
    {
        return classification.best().getBin();
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return input + " -> " + classification;
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof NumBinClassifiedImpl)) return false;

        NumBinClassifiedImpl that = (NumBinClassifiedImpl) o;

        return classification.equals(that.classification) &&
               input.equals(that.input);

    }

    @Override
    public int hashCode()
    {
        int result = input.hashCode();
        result = 31 * result + classification.hashCode();
        return result;
    }
}
