package ao.ai.learn

/**
 * User: AO
 * Date: 1/23/11
 * Time: 2:29 PM
 */

trait Hypothesis[I, O]
{
  //--------------------------------------------------------------------------
  def predict(input: I): O =
    prediction(input).value

  def prediction(input: I): Prediction[O] =
    predictions(input).sortBy(_.confidence).last
    //predictions(input) max Ordering.by(_.confidence)


  //--------------------------------------------------------------------------
  def predictions(input: I): Seq[Prediction[O]]
}