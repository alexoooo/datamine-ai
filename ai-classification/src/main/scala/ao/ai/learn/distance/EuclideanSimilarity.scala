package ao.ai.learn.distance

/**
 * User: AO
 * Date: 1/23/11
 * Time: 8:37 PM
 */

class EuclideanSimilarity
    extends Similarity[Seq[Double]]
{
  def similarity(a: Seq[Double], b: Seq[Double]): Double =
  {
    -Math.sqrt(
      (a, b).zipped.map(
        (i, j) => Math.pow(i - j, 2)
      ).sum)
  }
}