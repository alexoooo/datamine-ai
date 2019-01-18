package ao.ai.ml.model.output;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Date: Sep 1, 2010
 * Time: 11:16:25 PM
 */
public class MultiClassBuilder
{
    //------------------------------------------------------------------------
//    private Map<Object, Integer> index = new HashMap<Object, Integer>();
    private Set<String> index = new LinkedHashSet<String>();


    //------------------------------------------------------------------------
    public MultiClassBuilder()
    {

    }


    //------------------------------------------------------------------------
    public synchronized MultiClassBuilder add(String o)
    {
        if (! index.contains( o ))
        {
            index.add( o );
        }

        return this;
    }

    public synchronized MultiClassBuilder
            addNext(int numberOfCategories)
    {
        for (int i = 0; i < numberOfCategories; i++)
        {
            index.add(
                    String.valueOf( index.size() ));
        }

        return this;
    }


    //------------------------------------------------------------------------
    public MultiClassSet build()
    {
        return new MultiClassSet( index );
    }
}
