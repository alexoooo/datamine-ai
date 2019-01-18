package ao.ai.classify.kernel

/**
 * User: AO
 * Date: 11/6/10
 * Time: 12:01 PM
 */

class MeanBinClassifier
//    extends OfflineBinaryLearner[RealList]
{
//  //--------------------------------------------------------------------------
//  def learn(trainingSet: Sample[RealList, BinaryClass])
//      : BinaryClassifier[RealList] =
//  {
//    val (positiveSample, negativeSample) =
//      trainingSet partition(_.output.isPositive)
//
//    if (negativeSample.isEmpty || trainingSet.size == 0)
//    {
//      return new BinaryScoreClassifier.ConstantDummy()
//    }
//
//    val inputDim = trainingSet input(0) size
//
////    val positiveMean = mean(
////      positiveSample.map(_.input.toArray), inputDim)
////
////    val negativeMean = mean(
////      negativeSample.map(_.input.toArray), inputDim)
//
//    val positiveMean = mean(positiveSample, inputDim)
//    val negativeMean = mean(negativeSample, inputDim)
//
//    var centerCalc = positiveMean.copy
//    centerCalc += negativeMean
//    centerCalc /= 2
//    val center = centerCalc
//
//    val negativeToPositive = positiveMean
//    negativeToPositive -= negativeMean
//
//    //new LinearBinaryClassifier( weights )
//    new BinaryClassifier[RealList] {
//      def classify(input: RealList): BinaryClass = {
//        val x = new DenseVector( input.toArray )
//        x -= center
//
//        //val h = negativeToPositive.dot(x - center)
//        BinaryClass.create( negativeToPositive.dot( x ) >= 0 )
//      }
//    }
//  }
//
//
////  //--------------------------------------------------------------------------
////  def means(trainingSet: Sample[RealList, BinaryClass])
////      : (scalala.tensor.Vector, scalala.tensor.Vector) =
////  {
////
////
////    val positiveMean = new DenseVector()
////    for (val i <- 0 until trainingSet.size)
////    {
////
////    }
////  }
//
//
//
//  //--------------------------------------------------------------------------
//  private def mean(vectorSeq : Traversable[Example[RealList, BinaryClass]],
//                   inputDim  : Int)
//      : Vector =
//  {
////    val sum = x foldLeft(_ + _)
//
////    val a = Array(1, 2)
////    val b = Array(1, 2)
////
////    val c = (a, b) zipped map (_ + _)
//
////      mean =
////        (mean, vec)
////          .zipped map
////        ((m, i) => m + i / vectorSeq.size)
//
//    var mean  = new DenseVector( inputDim )
//    var count = 0
//
//    for (val vec <- vectorSeq.map(_.input))
//    {
//      for (val i <- 0 until inputDim)
//      {
//        mean( i ) += vec.get( i )
//      }
//
//      count += 1
//    }
//
//    mean /= count
//
//    mean
//  }
}