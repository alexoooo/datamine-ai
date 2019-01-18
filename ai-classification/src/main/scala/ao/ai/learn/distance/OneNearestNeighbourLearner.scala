package ao.ai.learn.distance

import ao.ai.learn.{Prediction, OnlineLearner}
import collection.mutable.ArrayBuffer

/**
 * User: AO
 * Date: 1/23/11
 * Time: 5:04 PM
 */

class OneNearestNeighbourLearner[I, O](
    similarityFunction: Similarity[I])
  extends OnlineLearner[I, O]
{
  //--------------------------------------------------------------------------
  private val examples = new ArrayBuffer[(I, O)]


  //--------------------------------------------------------------------------
  def lean(input: I, output: O)
  {
    examples += new Tuple2(input, output)
//    examples += (input, output) // why doesn't this work?
  }


  //--------------------------------------------------------------------------
  def predictions(input: I): Seq[Prediction[O]] =
  {
    var mostSimilar   = examples(0)
    var maxSimilarity = Math.NEG_INF_DOUBLE
    for (example <- examples)
    {
      val similarity =
        similarityFunction.similarity(
          input, example._1)

      if (maxSimilarity < similarity)
      {
        maxSimilarity = similarity
        mostSimilar   = example
      }
    }

//    val bySimilarity =
//      examples.sortBy(
//        (a) => similarityFunction.similarity(a._1, input))
//    val mostSimilar = bySimilarity.last

    Seq(new Prediction( mostSimilar._2 ))
  }
}