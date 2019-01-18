package ao.ai.model.ml.regress.hypothesis.regression;

import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.value.NumericalValue;

/**
 * User: alex
 * Date: 14-Apr-2010
 * Time: 11:12:14 PM
 */
public interface Regression
{
    //-------------------------------------------------------------------------
    public NumericalValue best();


    //-------------------------------------------------------------------------
    public FeatureType    type();
}
