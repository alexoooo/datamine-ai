package ao.ai.learn.sample.impl

import ao.ai.learn.sample.{SampleFactory, Sample}


//============================================================================
object RealMeanSample
{
  //--------------------------------------------------------------------------
  val factory = new SampleFactory[Double] {
    def newSample() = new RealMeanSample
  }
}


//============================================================================
class RealMeanSample
    extends Sample[Double]
{
  //--------------------------------------------------------------------------
  private var sum   = 0.0
  private var count = 0


  //--------------------------------------------------------------------------
  def add(outputInstance: Double)
  {
    sum   += outputInstance
    count += 1
  }

  def mostFrequent(): Double =
  {
    if (count == 0) 0
    else sum / count
  }
}