package ao.ai.classify.kernel

/**
 * User: Mable
 * Date: 11/27/10
 * Time: 5:51 PM
 */

class Forgetron(
      /*private */kernel: (Seq[Double], Seq[Double]) => Double)
//    extends OnlineBinaryLearner[RealList]
{
  //--------------------------------------------------------------------------
  def this() = this(
      (a: Seq[Double], b: Seq[Double]) => {
        val dot = (a, b).zipped.map(_ * _).sum
        Math.pow(dot + 1, 3)
//        Math.tanh( dot )
      })


  //--------------------------------------------------------------------------
//  def learn(trainingSet: Sample[RealList, BinaryClass])
//      : BinaryClassifier[RealList] =
//  {
//    null
//  }
}