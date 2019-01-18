package ao.ai.learn.distance

/**
 * User: ao
 * Date: 1/23/11
 * Time: 4:25 PM
 */

trait Similarity[I]
{
  def similarity(a: I, b: I): Double
}