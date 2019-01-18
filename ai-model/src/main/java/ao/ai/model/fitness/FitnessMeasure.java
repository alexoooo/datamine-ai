package ao.ai.model.fitness;

import java.util.Comparator;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 9:10:53 AM
 */
public interface FitnessMeasure
        //extends Comparable<FitnessMeasure>
{
    //-------------------------------------------------------------------------
    public double loss();
    

    //-------------------------------------------------------------------------
    public static class LossComparator
            implements Comparator<FitnessMeasure>
    {
        private static final LossComparator
                INSTANCE = new LossComparator();
        public static LossComparator get() {
            return INSTANCE;
        }
        private LossComparator() {}

        @Override
        public int compare(FitnessMeasure a, FitnessMeasure b) {
            return Double.compare(a.loss(), b.loss());
        }
    }
}
