package ao.ai.ml.model.output;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * User: AO
 * Date: Sep 1, 2010
 * Time: 11:21:00 PM
 */
public class MultiClassSet
{
    //------------------------------------------------------------------------
    public static MultiClassSet of(String...        categories)
    {
        return of(Arrays.asList( categories ));
    }
    public static MultiClassSet of(Iterable<String> categories)
    {
        return new MultiClassSet( categories );
    }


    //------------------------------------------------------------------------
    private final Map<String, Integer> indexes;


    //------------------------------------------------------------------------
    /*package-private*/ MultiClassSet(Iterable<String> values)
    {
        indexes = Maps.newHashMap();

        for (String value : values)
        {
            Integer previous = indexes.put( value, indexes.size() );

            if (previous != null)
            {
                throw new AssertionError("Duplicate value: " + value);
            }
        }
    }


    //------------------------------------------------------------------------
    public int indexOf(String value)
    {
        Integer index = indexes.get( value );
        return (index == null ? -1 : index);
    }


    //------------------------------------------------------------------------
    public MultiClass get(String best)
    {
        return new MultiClass(
                indexOf( best )/*,
                indexes.size()*/);
    }

    public MultiClass get(int bestIndex)
    {
        return new MultiClass(
                bestIndex/*,
                indexes.size()*/);
    }
}
