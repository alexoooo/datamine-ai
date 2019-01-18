package ao.ai.classify.kernel

import ao.ai.ml.model.input.RealList
import ao.ai.ml.model.algo.OfflineBinaryLearner
import ao.ai.ml.model.output.BinaryClass
import ao.ai.ml.model.theory.{BinaryScoreClassifier, BinaryClassifier}
import scala.collection.JavaConversions._
import ao.ai.ml.model.input.coll.{Example, Sample}
import java.lang.Math
//import ao.util.math.Calc

/**
 * User: AO
 * Date: 11/6/10
 * Time: 3:24 AM
 */
class MeanBinKernelClassifier(
        /*private */kernel: (Seq[Double], Seq[Double]) => Double)
    extends OfflineBinaryLearner[RealList]
{
  //--------------------------------------------------------------------------
  def this() = this(
      (a: Seq[Double], b: Seq[Double]) => {
        val dot = (a, b).zipped.map(_ * _).sum
        Math.pow(dot + 1, 3)
//        Math.tanh( dot )
      })

//      mean =
//        (mean, vec)
//          .zipped map
//        ((m, i) => m + i / vectorSeq.size)


  //--------------------------------------------------------------------------
  def learn(trainingSet: Sample[RealList, BinaryClass])
      : BinaryClassifier[RealList] =
  {
    val (positiveSample, negativeSample) =
      trainingSet partition(_.output.isPositive)

    if (negativeSample.isEmpty || positiveSample.isEmpty) {
      return new BinaryScoreClassifier.ConstantDummy(
          negativeSample.isEmpty)
    }

    val b = 0.5 * (squaredNorm(negativeSample) -
                   squaredNorm(positiveSample))

    val positiveAlpha =  1.0 / positiveSample.size
    val negativeAlpha = -1.0 / negativeSample.size

//    val inputDim = trainingSet input(0) size

    new BinaryClassifier[RealList] {
      def classify(input: RealList): BinaryClass = {
        val h = kernelMean(positiveSample, input) -
                kernelMean(negativeSample, input) +
                b

        BinaryClass.create( h >= 0 )
      }
    }
  }


  //--------------------------------------------------------------------------
  private def kernelMean(
          examples : Traversable[Example[RealList, BinaryClass]],
          input    : RealList)
      : Double =
  {
    var sum   = 0.0
    var count = 0

    val inputSeq = input.toArray
    for (e <- examples)
    {
      sum   += kernel(e.input.toArray, inputSeq)
      count += 1
    }

    sum / count
  }

  private def squaredNorm(
          examples : Traversable[Example[RealList, BinaryClass]])
      : Double =
  {
    var sum   = 0.0
    var count = 0
    for (i <- examples)
    {
      val inputI = i.input.toArray
      for (j <- examples)
      {
        sum   += kernel(inputI, j.input.toArray)
        count += 1
      }
    }

    //sum / Calc.square(examples.size)
    sum / count
  }
}