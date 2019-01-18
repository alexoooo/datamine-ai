package ao.ai.learn

/**
 * User: AO
 * Date: 1/23/11
 * Time: 4:16 PM
 */

trait OnlineLearner[I, O]
    extends Hypothesis[I, O]
{
  def lean(input: I, output: O)
}