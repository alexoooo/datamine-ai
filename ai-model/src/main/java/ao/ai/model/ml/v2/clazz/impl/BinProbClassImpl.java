package ao.ai.model.ml.v2.clazz.impl;

/**
 * User: alex
 * Date: 15-May-2010
 * Time: 11:40:05 AM
 */
public class BinProbClassImpl
        extends BinClassImpl
{
    //-------------------------------------------------------------------------
    public BinProbClassImpl(double positiveProbability)
    {
        super( positiveProbability >= 0.5 );
    }
}
