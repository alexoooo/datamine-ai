package ao.ai.demo.supervised.classification.fin;


/**
 * Date: 6/19/11
 * Time: 6:23 PM
 */
public interface FeatureExtractor<F, T>
{
    //------------------------------------------------------------------------
    T extract(F from);


    //------------------------------------------------------------------------
    class Identity<I> implements FeatureExtractor<I, I>
    {
        public static <I> FeatureExtractor<I, I> create() {
            return new Identity<I>();
        }

        @Override
        public I extract(I from) {
            return from;
        }
    }
}
