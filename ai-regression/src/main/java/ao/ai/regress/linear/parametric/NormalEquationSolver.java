package ao.ai.regress.linear.parametric;

import ao.ai.model.common.feature_list.ext.num.NumericalFeatureList;
import ao.ai.model.common.feature_list.impl.FeatureScalar;
import ao.ai.model.common.feature_type.FeatureType;
import ao.ai.model.common.feature_type.impl.FeatureTypeImpl;
import ao.ai.model.ml.regress.RegressLearner;
import ao.ai.model.ml.regress.example.Regressed;
import ao.ai.model.ml.regress.example.impl.RegressedImpl;
import ao.ai.model.ml.regress.hypothesis.regression.impl.RegressionImpl;
import ao.ai.model.ml.regress.hypothesis.regressor.Regressor;
import ao.ai.model.ml.regress.hypothesis.regressor.impl.LinearRegressor;
import com.google.common.collect.Lists;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.SingularMatrixException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: aostrovsky
 * Date: 2-Feb-2010
 * Time: 8:53:34 AM
 */
public class NormalEquationSolver
        //implements RegressionLearner
        implements RegressLearner<NumericalFeatureList>
{
    //-------------------------------------------------------------------------
    private static final Logger LOG =
            LoggerFactory.getLogger(
            		NormalEquationSolver.class);


    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
        List<Regressed<? extends NumericalFeatureList>>
                data = Lists.newArrayList();

        FeatureType inputType =
//                new FeatureTypeImpl("x");
                new FeatureTypeImpl("Previous Growth");

        FeatureType outputType =
                new FeatureTypeImpl("Next Growth");

//        for (int x = -10; x <= 10; x++)
//        {
//            data.add(RegressedImpl.newInstance(
//                    new FeatureScalar(x, inputType),
//                    new RegressionImpl(
//                            (double) x / 2, outputType)));
//        }

        double[][] values = {
                {0.276455687, 0.111384281},
                {0.111384281, -0.051392834},
                {-0.051392834, 0.132505401},
                {0.132505401, 0.07944943},
                {0.07944943, 0.285071647}};

        for (double[] example : values)
        {
            data.add(RegressedImpl.newInstance(
                    new FeatureScalar(example[0], inputType),
                    new RegressionImpl(example[1], outputType)));
        }


        RegressLearner<NumericalFeatureList>
                learner = new NormalEquationSolver();

        Regressor<NumericalFeatureList>
                regressor = learner.learn(data);

        System.out.println(
                regressor);

        System.out.println(regressor.regress(
                new FeatureScalar(0.285071647, inputType)));

//        regressor.regress();

    }


    //-------------------------------------------------------------------------
    @Override
    public Regressor<NumericalFeatureList> learn(
            List<? extends Regressed<
                    ? extends NumericalFeatureList>> data)
    {
        Regressed<? extends NumericalFeatureList>
                    arbitraryExample = data.get(0);

        int featureCount = arbitraryExample.input().size();
		RealMatrix features = new Array2DRowRealMatrix(
				data.size(), featureCount + 1);

		RealMatrix targets  = new Array2DRowRealMatrix(
								data.size(), 1);

		for (int i = 0; i < data.size(); i++)
        {
            Regressed<? extends NumericalFeatureList> e = data.get(i);

            features.setEntry(i, 0, 1.0);
			for (int j = 1; j <= featureCount; j++)
            {
				features.setEntry(i, j, e.input().doubleValue(j - 1));
			}

			targets .setEntry(i, 0, e.regression().best().getNum());
		}

        try
        {
            RealMatrix featureTranspose = features.transpose();
            RealMatrix parameters       =
                    new LUDecompositionImpl(
                            featureTranspose.multiply(features)
                    ).getSolver().getInverse()
                        .multiply(featureTranspose)
                        .multiply(targets);

//            return new LinearHypothesis(
//                     parameters.getColumn(0), arbitraryExample);
            return new LinearRegressor(
                            parameters.getColumn(0),
                            arbitraryExample.regression().type());
        }
        catch (SingularMatrixException ignored)
        {
            return null;
        }
    }

    
	//-------------------------------------------------------------------------
	@Override public String toString() {
		return "Normal Equation Solver";
	}
}
