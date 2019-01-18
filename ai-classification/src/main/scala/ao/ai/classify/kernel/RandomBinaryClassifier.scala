package ao.ai.classify.kernel

import ao.ai.ml.model.theory.BinaryClassifier
import ao.ai.ml.model.output.BinaryClass
import ao.ai.ml.model.input.RealList
import util.Random

/**
 * User: AO
 * Date: 11/6/10
 * Time: 3:43 AM
 */
object RandomBinaryClassifier extends BinaryClassifier[RealList]
{
  //--------------------------------------------------------------------------
  private val rand = new Random


  //--------------------------------------------------------------------------
  def classify(input: RealList)
      : BinaryClass =
  {
    BinaryClass.create(
      rand.nextBoolean)
  }
}