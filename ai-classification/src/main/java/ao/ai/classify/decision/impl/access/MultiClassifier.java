package ao.ai.classify.decision.impl.access;

import ao.ai.classify.decision.impl.input.raw.example.Context;
import ao.ai.classify.decision.impl.model.raw.Classifier;
import ao.ai.classify.decision.impl.model.raw.ClassifierImpl;
import ao.ai.classify.decision.impl.random.RandomLearner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: alex
 * Date: 23-Jun-2009
 * Time: 8:05:50 PM
 */
public class MultiClassifier
{
    //--------------------------------------------------------------------
    private final List<Context>           data;
    private final Map<String, Classifier> classifiers;


    //--------------------------------------------------------------------
    public MultiClassifier()
    {
        data        = new ArrayList<Context>();
        classifiers = new HashMap<String, Classifier>();

//        Classifier classifier = new ClassifierImpl(
//                new RandomLearner());
    }


    //--------------------------------------------------------------------
    public void add(Context datum)
    {
        if (data.isEmpty())
        {
            for (String type : datum.types())
            {
                classifiers.put(type,
                        new ClassifierImpl(new RandomLearner()));
            }
        }

        for (int i = 0; i < datum.types().size(); i++) {
            String type = datum.types().get(i);
            classifiers.get(type).add(
                    datum.withTarget(
                            datum.data().get(i)));
        }


//        data.add( datum );
    }


    //--------------------------------------------------------------------
//    public
}
