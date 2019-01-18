package ao.ai.classify.kernel.v5

/**
 * User: AO
 * Date: 11/15/10
 * Time: 11:22 PM
 */

trait WOfflineLearner[I, T, O <: T] extends OfflineLearner[I, T, ExW[I, T], O]
{
  override def learn(trainingSet: Seq[ExW[I, T]]): Predictor[I, O]

//  override def learn(trainingSet: Seq[ExW[I, T]]): Predictor[I, O]
}