package ao.ai.learn.sample.impl

import ao.ai.learn.sample.{SampleFactory, Sample}


//============================================================================
object BinarySample
{
  //--------------------------------------------------------------------------
  val factory = new SampleFactory[Boolean] {
    def newSample() = new BinarySample
  }
}


//============================================================================
class BinarySample
    extends Sample[Boolean]
{
  //--------------------------------------------------------------------------
  private var trueCount  = 0
  private var falseCount = 0


  //--------------------------------------------------------------------------
  def add(outputInstance: Boolean)
  {
    if (outputInstance) trueCount  += 1
    else                falseCount += 1
  }

  def mostFrequent(): Boolean =
  {
    trueCount > falseCount
  }
}