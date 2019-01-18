package ao.ai.learn.distance

import ao.ai.ml.model.input.RealList

/**
 * User: AO
 * Date: 1/23/11
 * Time: 9:03 PM
 */

class EuclideanRealListSimilarity
    extends Similarity[RealList]
{
  //--------------------------------------------------------------------------
  private val delegate = new EuclideanSimilarity


  //--------------------------------------------------------------------------
  def similarity(a: RealList, b: RealList): Double =
  {
    delegate.similarity(a.toArray, b.toArray)
  }
}