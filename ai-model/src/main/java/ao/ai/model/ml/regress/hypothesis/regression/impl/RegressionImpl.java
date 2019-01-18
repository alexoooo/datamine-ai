package ao.ai.model.ml.regress.hypothesis.regression.impl;

import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.value.NumericalValue;
import ao.ai.model.common.value.impl.NumericalValueImpl;
import ao.ai.model.ml.regress.hypothesis.regression.Regression;

/**
 * User: alex
 * Date: 18-Apr-2010
 * Time: 9:26:48 PM
 */
public class RegressionImpl
        implements Regression
{
    //-------------------------------------------------------------------------
    private final NumericalValue value;
    private final FeatureType    type;


    //-------------------------------------------------------------------------
    public RegressionImpl(
            double      value,
            FeatureType type)
    {
        this(new NumericalValueImpl( value ), type);
    }

    public RegressionImpl(
            NumericalValue value,
            FeatureType    type)
    {
        this.value = value;
        this.type  = type;
    }


    //-------------------------------------------------------------------------
    @Override
    public NumericalValue best()
    {
        return value;
    }

    @Override
    public FeatureType type() {
        return type;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
//        return value.toString();
        return type + " = " + value.toString();
    }
}
