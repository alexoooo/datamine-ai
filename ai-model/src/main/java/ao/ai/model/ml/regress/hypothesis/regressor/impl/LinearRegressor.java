package ao.ai.model.ml.regress.hypothesis.regressor.impl;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.ml.regress.hypothesis.regression.Regression;
import ao.ai.model.ml.regress.hypothesis.regression.impl.RegressionImpl;
import ao.ai.model.ml.regress.hypothesis.regressor.Regressor;

/**
 * User: alex
 * Date: 18-Apr-2010
 * Time: 9:23:19 PM
 */
public class LinearRegressor
        implements Regressor<NumericalFeatureList>
{
    //-------------------------------------------------------------------------
    private final double[]    parameters;
    private final FeatureType outputType;



    //-------------------------------------------------------------------------
    public LinearRegressor(
            double[]    copyParams,
            FeatureType targetType)
    {
        parameters = copyParams;
        outputType = targetType;
    }


    //-------------------------------------------------------------------------
    @Override
    public Regression regress(NumericalFeatureList input)
    {
        assert parameters.length == (input.size() + 1);

		double estimate = parameters[0];
		for (int p = 1; p < parameters.length; p++) {
			estimate += parameters[p] * input.doubleValue(p - 1);
		}

//		return new FeatureScalar(estimate, outputType);
        return new RegressionImpl(
                estimate, outputType);
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        if (parameters.length == 2) {
            return parameters[0] + " + " + parameters[1] + " * x";
        }

        return "";
    }
}
