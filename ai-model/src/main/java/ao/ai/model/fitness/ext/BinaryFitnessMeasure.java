package ao.ai.model.fitness.ext;

import ao.ai.model.common.feature_list.ext.cat.bin.SingleBinaryFeature;

/**
 * User: aostrovsky
 * Date: 1-Feb-2010
 * Time: 9:15:37 AM
 */
public interface BinaryFitnessMeasure
        extends CategoricalFitnessMeasure<
                    SingleBinaryFeature>
{
    //-------------------------------------------------------------------------
    public void add(
            SingleBinaryFeature predicted,
            SingleBinaryFeature actual);
}