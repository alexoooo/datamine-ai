package ao.ai.learn

/**
 * User: AO
 * Date: 1/23/11
 * Time: 4:02 PM
 */

trait OfflineLearner[I, O]
{
  def lean(examples: Seq[(I, O)]): Hypothesis[I, O]
}