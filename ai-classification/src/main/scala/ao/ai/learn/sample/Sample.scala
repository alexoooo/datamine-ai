package ao.ai.learn.sample


trait Sample[O]
{
  def add(outputInstance: O)

  def mostFrequent() : O
//  def predictions(): Seq[Prediction[O]]
}