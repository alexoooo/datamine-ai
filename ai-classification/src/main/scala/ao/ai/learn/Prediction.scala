package ao.ai.learn

/**
 * User: AO
 * Date: 1/22/11
 * Time: 9:07 PM
 */

class Prediction[O]
(
  val value     : O,
  val confidence: Double
)
{
  def this(value: O) = this(value, 1)
}