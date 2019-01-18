package ao.ai.cluster.space.measure.vector;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.LUDecomposition;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.descriptive.moment.VectorialCovariance;

/**
 * User: alex
 * Date: 19-Jul-2009
 * Time: 12:20:07 AM
 */
public class MahalanobisModel
{
    //--------------------------------------------------------------------
    private final RealMatrix means;
    private final RealMatrix covarianceMatrixInverse;


    //--------------------------------------------------------------------
    public MahalanobisModel(
            VectorialCovariance covariance,
            double              sums[],
            double              count)
    {
        assert count > 0;

        double meansArr[] = new double[ sums.length ];
        for (int i = 0; i < meansArr.length; i++) {
            meansArr[ i ] = sums[ i ] / count;
        }
        means = new Array2DRowRealMatrix(meansArr);

        RealMatrix      covarianceMatrix = covariance.getResult();
        LUDecomposition decomposition    =
                new LUDecompositionImpl(covarianceMatrix);

        if (decomposition.getDeterminant() == 0) {
            covarianceMatrixInverse = null;
        } else {
            covarianceMatrixInverse =
                    decomposition.getSolver().getInverse();
        }
    }


    //--------------------------------------------------------------------
    public double distance(double to[])
    {
        RealMatrix xT = new Array2DRowRealMatrix( to );

        if (covarianceMatrixInverse == null)
        {
            return VectorEuclidean.distance(
                    means.getColumn(0), to);
        }
        else
        {
            RealMatrix xMinusU     = xT.subtract( means );

            RealMatrix distSquared =
                    xMinusU.transpose()
                        .multiply( covarianceMatrixInverse )
                    .multiply( xMinusU );

            return Math.sqrt(distSquared.getEntry(0, 0));
        }
    }
}
