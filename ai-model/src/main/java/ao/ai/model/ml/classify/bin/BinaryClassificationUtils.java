package ao.ai.model.ml.classify.bin;

/**
 * User: alex
 * Date: 25-Apr-2010
 * Time: 9:47:26 PM
 */
public class BinaryClassificationUtils
{
    //-------------------------------------------------------------------------
    private BinaryClassificationUtils() {}


    //-------------------------------------------------------------------------
    public static boolean fromIndex(int index)
    {
        assert 0 <= index;
        assert index <= 1;
        return index == 0;
    }
}
