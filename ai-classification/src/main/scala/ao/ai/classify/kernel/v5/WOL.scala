package ao.ai.classify.kernel.v5

import ao.ai.ml.model.input.RealList
import ao.ai.ml.model.output.{BinaryScoreClass, BinaryClass}

/**
 * User: AO
 * Date: 11/15/10
 * Time: 11:18 PM
 */

class WOL extends WOfflineLearner[RealList, BinaryClass, BinaryScoreClass]
{
  def learn(trainingSet: Seq[ExW[RealList, BinaryClass]])
      : Predictor[RealList, BinaryScoreClass] = {
    null
  }
}