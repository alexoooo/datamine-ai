package ao.ai.classify.kernel

/**
 * User: AO
 * Date: 11/27/10
 * Time: 5:00 PM
 */

class BPA(
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