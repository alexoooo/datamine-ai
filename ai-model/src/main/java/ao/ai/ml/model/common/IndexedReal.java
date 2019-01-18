package ao.ai.ml.model.common;

import java.util.Comparator;

/**
 * User: AO
 * Date: 10/24/10
 * Time: 9:02 PM
 */
public class IndexedReal
        implements Comparable<IndexedReal>
{
    //------------------------------------------------------------------------
    public static Comparator<IndexedReal> ASCENDING_REAL_ORDER =
            new Comparator<IndexedReal>()  {
                @Override public int compare(IndexedReal a, IndexedReal b)
                {
                    return Double.compare(a.real, b.real);
                }};

    public static Comparator<IndexedReal> DESCENDING_REAL_ORDER =
            new Comparator<IndexedReal>()  {
                @Override public int compare(IndexedReal a, IndexedReal b)
                {
                    return Double.compare(b.real, a.real);
                }};


    //------------------------------------------------------------------------
    private final int    index;
    private final double real;


    //------------------------------------------------------------------------
    public IndexedReal(int index, double real)
    {
        this.index = index;
        this.real  = real;
    }


    //------------------------------------------------------------------------
    public int index()
    {
        return index;
    }

    public double real()
    {
        return real;
    }


    //------------------------------------------------------------------------
    @Override
    public int compareTo(IndexedReal o)
    {
        return Double.compare(real, o.real);
    }


    //------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "IndexedReal{" +
                "index=" + index +
                ", real=" + real +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexedReal that = (IndexedReal) o;

        if (index != that.index) return false;
        if (Double.compare(that.real, real) != 0) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = index;
        temp = real != +0.0d ? Double.doubleToLongBits(real) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
