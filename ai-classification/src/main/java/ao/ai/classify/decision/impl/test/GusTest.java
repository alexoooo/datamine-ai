package ao.ai.classify.decision.impl.test;

import ao.ai.classify.decision.impl.classification.raw.Prediction;
import ao.ai.classify.decision.impl.input.raw.example.*;
import ao.ai.classify.decision.impl.model.raw.Classifier;
import ao.ai.classify.decision.impl.model.raw.ClassifierImpl;
import ao.ai.classify.decision.impl.tree.GeneralTreeLearner;
import ao.util.data.AutovivifiedMap;
import ao.util.misc.Factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * User: aostrovsky
 * Date: 11-Sep-2009
 * Time: 8:01:33 AM
 */
public class GusTest
{
    //--------------------------------------------------------------------
    public static void main(String[] args) {
        new GusTest(new ClassifierImpl(new GeneralTreeLearner()));
//        new GusTest(new ClassifierImpl(new RandomLearner()));
    }


    //--------------------------------------------------------------------
    public GusTest(Classifier learner)
    {
        Map<String, List<Example>> byEvent = byEvent();
        displayAll(learner,  byEvent);
        benchmark (learner,  byEvent);
    }


    //--------------------------------------------------------------------
    private void benchmark(
            Classifier                 learner,
            Map<String, List<Example>> byEvent)
    {
        double totalAcc = 0;
        for (String event : byEvent.keySet()) {
//            System.out.println(event);

            LearningSet examples = new LearningSet();
            for (Map.Entry<String, List<Example>> e :
                    byEvent.entrySet()) {
                if (e.getKey().equals(event)) continue;

                for (Example ex : e.getValue()) {
                    examples.add(ex);
                }
            }
            learner.set( examples );

            double acc  = 0;
            double rand = 1.0 / byEvent.get(event).size();
            for (Example ex : byEvent.get(event)) {
                Prediction p = learner.classify(ex);
                double prob  = p.probabilityOf( ex.target() );
                double delta = prob - rand;

                acc += delta;
//                System.out.println(
//                        prob + "\t" + rand + "\t" + delta
//                             + "\t" + Info.cost(prob));
            }

            totalAcc += acc;
//            System.out.println("accuracy:\t" + acc);
        }
        System.out.println(
                "avg accuracy:\t" + (totalAcc / byEvent.size()));
    }


    //--------------------------------------------------------------------
    private void displayAll(
            Classifier                 learner,
            Map<String, List<Example>> byEvent)
    {
        LearningSet examples = new LearningSet();
        for (Map.Entry<String, List<Example>> e : byEvent.entrySet()) {
            for (Example ex : e.getValue()) {
                examples.add(ex);
            }
        }
        learner.set( examples );
        System.out.println(learner);
    }


    //--------------------------------------------------------------------
    private Map<String, List<Example>> byEvent()
    {
        try {
            return doByEvent();
        } catch (IOException e) {
            throw new Error( e );
        }
    }
    private Map<String, List<Example>> doByEvent()
            throws IOException
    {
        BufferedReader in = new BufferedReader(
                new FileReader("data/poker.csv"));

        // header: chip %, position %, result %
        in.readLine();

        Map<String, List<Example>> byEvent =
                new AutovivifiedMap<String, List<Example>>(
                        Factories.<Example>newArrayList());

        for (String line; (line = in.readLine()) != null;) {
            String[] fields = line.split(",");

            String   event    = fields[0];
            double   chips    = parsePercentage(fields[1]);
            double   position = parsePercentage(fields[2]);
            String   result   = fields[3];

            Example example = new ExampleImpl(
                    new ContextImpl(Arrays.asList(
                            new Datum("chips"   , chips),
                            new Datum("position", position)
                    )),
                    new Datum("result", "R" + result));

            byEvent.get(event).add( example );
        }
        in.close();

        return byEvent;
    }

    private double parsePercentage(String percentage)
    {
        return Double.parseDouble(
                    percentage.replace("%", "")) / 100;
    }
}
