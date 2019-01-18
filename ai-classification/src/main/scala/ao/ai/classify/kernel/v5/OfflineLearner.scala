package ao.ai.classify.kernel.v5

/**
 * User: AO
 * Date: 11/15/10
 * Time: 11:08 PM
 */

trait OfflineLearner[I, T, E <: Ex[I, T], O <: T]
{
  def learn(trainingSet: Seq[E]): Predictor[I, O]
}