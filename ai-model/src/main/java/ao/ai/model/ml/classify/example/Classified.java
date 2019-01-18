package ao.ai.model.ml.classify.example;

import ao.ai.model.ml.classify.hypothesis.classification.Classification;

/**
 * User: Mable
 * Date: Apr 10, 2010
 * Time: 10:41:31 PM
 */
public interface Classified<I>
{
    //-------------------------------------------------------------------------
    public I input();

    
    //-------------------------------------------------------------------------
    public Classification classification();
}
