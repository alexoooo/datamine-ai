package ao.ai.learn.split


trait SplitCondition[I]
{
  //--------------------------------------------------------------------------
  def isLeft(input: I): Boolean
}