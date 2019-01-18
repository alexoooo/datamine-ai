package ao.ai.classify.online.forest;

import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.MultiClass;

/**
 * User: AO
 * Date: 12/25/10
 * Time: 2:08 PM
 */
/*package-private*/ class Split
//        implements Comparable<Split>
{
    //------------------------------------------------------------------------
    private final SplitCondition condition;
    private final Sample         left;
    private final Sample         right;


    //------------------------------------------------------------------------
    public Split(SplitCondition condition)
    {
        this(condition, new Sample(), new Sample());
    }

    public Split(SplitCondition condition, Sample left, Sample right)
    {
        this.condition = condition;
        this.left      = left;
        this.right     = right;
    }


    //------------------------------------------------------------------------
    public SplitCondition condition()
    {
        return condition;
    }

    public Sample left()
    {
        if (left.totalCount() > 0) {
            return left.prototype();
        } else {
            return right.asBias();
        }
    }

    public Sample right()
    {
        if (right.totalCount() > 0) {
            return right.prototype();
        } else {
            return left.asBias();
        }
    }


    //------------------------------------------------------------------------
//    public void consider(
//            RealList   input,
//            MultiClass output)
//    {
//        // update information criteria (not happening)
//    }

    public void learn(
            RealList   input,
            MultiClass output)
    {
        (condition.isLeft(input)
         ? left : right
        ).learn( output );
    }


    //------------------------------------------------------------------------
    public double cost(int totalCount)
    {
        double leftFraction  = left .totalCount() / totalCount;
        double rightFraction = right.totalCount() / totalCount;

        return leftFraction  * left .cost() +
               rightFraction * right.cost();
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return condition + " ? " + left + " : " + right;
    }
}
