package ao.ai.ml.model.input;

import java.io.Serializable;

/**
 * Date: Sep 6, 2010
 * Time: 12:46:54 PM
 */
public final class MultiList
        implements Serializable
{
    //------------------------------------------------------------------------
    private final Domain domain;
    private final int[]  classes;


    //------------------------------------------------------------------------
    public MultiList(
            Domain domain,
            int... instance)
    {
        this.domain  = domain;
        this.classes = instance.clone();

        domain.checkModel( classes );
    }


    //------------------------------------------------------------------------
    public int get(int index)
    {
        return classes[ index ];
    }

    public int classCount(int index)
    {
        return domain.classCounts[ index ];
    }

    public int size()
    {
        return classes.length;
    }


    //------------------------------------------------------------------------
    public static final class Domain
    {
        private final int[] classCounts;

        public Domain(int... classCounts)
        {
            if (classCounts == null || classCounts.length == 0) {
                throw new AssertionError(
                        "Must define at least one class count.");
            }

            this.classCounts = classCounts.clone();

            for (int count : this.classCounts)
            {
                if (count < 2)
                {
                    throw new AssertionError("Multi-class class count " +
                        "must be 2 or greater: " + count);
                }
            }
        }

        public void checkModel(int[] classValues)
        {
            if (classValues == null ||
                classValues.length != classCounts.length)
            {
                throw new AssertionError("Wrong number of classValues.");
            }

            for (int i = 0; i < classValues.length; i++)
            {
                if (! (0 <= classValues[ i ] &&
                            classValues[ i ] < classCounts[ i ]))
                {
                    throw new AssertionError("Class value out of range: " +
                            classValues[ i ] + " of " + classCounts[ i ]);
                }
            }
        }
    }
}
