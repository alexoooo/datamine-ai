package ao.ai.learn.distance

import ao.ai.ml.model.input.RealList
import ao.ai.ml.model.algo.OnlineMultiLearner
import ao.ai.ml.model.output.MultiClass

/**
 * User: AO
 * Date: 1/23/11
 * Time: 8:56 PM
 */

class OnnLearner
    extends OnlineMultiLearner[RealList]
{
  //--------------------------------------------------------------------------
  private val delegate =
      new OneNearestNeighbourLearner[RealList, MultiClass](
          new EuclideanRealListSimilarity)


  //--------------------------------------------------------------------------
  def learn(input: RealList, output: MultiClass)
  {
    delegate.lean(input, output)
  }


  //--------------------------------------------------------------------------
  def classify(input: RealList): MultiClass =
  {
    delegate.predict( input )
  }
}
