package ao.ai.classify.kernel.v5

/**
 * User: AO
 * Date: 11/15/10
 * Time: 11:08 PM
 */
trait Predictor[I, O]
{
  def classify(input: I): O
}