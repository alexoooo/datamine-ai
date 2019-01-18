package ao.ai.learn.sample


trait SampleFactory[O]
{
  def newSample() : Sample[O]
}