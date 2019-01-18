package ao.ai.learn.split

import ao.ai.learn.sample.{Sample, SampleFactory}

trait SplitConditionBuffer[I, O]
{
  //--------------------------------------------------------------------------
  def accumulate(input: I, output: O)


  //--------------------------------------------------------------------------
  def splitCondition(): SplitCondition[I]

  def splitCondition(sampleFactory: SampleFactory[O])
      : (SplitCondition[I], Sample[O], Sample[O]) =
  {
    val condition   = splitCondition
    val leftSample  = sampleFactory.newSample
    val rightSample = sampleFactory.newSample

    populateSamples(
        condition, leftSample, rightSample);

    (condition, leftSample, rightSample)
  }

  protected def populateSamples(
      splitCondition: SplitCondition[I],
      leftSample    : Sample[O],
      rightSample   : Sample[O])
}