package ao.ai.model.ml.v2.clazz.impl;

import ao.ai.model.ml.v2.clazz.MultiClass;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 1:14:14 PM
 */
public class MultiClassImpl
        implements MultiClass
{
    //-------------------------------------------------------------------------
    public static final MultiClass[] CLASS_TYPES = initClassTypes( 1024 );

    private static MultiClass[] initClassTypes(int size)
    {
        MultiClass[] classTypes = new MultiClassImpl[ size ];

        for (int i = 0; i < size; i++)
        {
            classTypes[ i ] = new MultiClassImpl( i );
        }

        return classTypes;
    }

    public static MultiClass create(int bestIndex)
    {
        return bestIndex < CLASS_TYPES.length
               ? CLASS_TYPES[ bestIndex ]
               : new MultiClassImpl( bestIndex );
    }


    //-------------------------------------------------------------------------
    private final int best;


    //-------------------------------------------------------------------------
    protected MultiClassImpl(int best)
    {
        this.best = best;
    }


    //-------------------------------------------------------------------------
    @Override
    public int best()
    {
        return best;
    }
    

    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return String.valueOf( best );
    }
}
