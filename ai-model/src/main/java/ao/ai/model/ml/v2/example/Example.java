package ao.ai.model.ml.v2.example;

/**
 * User: alex
 * Date: 15-May-2010
 * Time: 10:46:39 AM
 */
public interface Example<I, O>
{
    //--------------------------------------------------------------------
    public I input();


    //--------------------------------------------------------------------
    public O output();
}
