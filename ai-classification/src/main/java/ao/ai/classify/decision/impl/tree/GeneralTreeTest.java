package ao.ai.classify.decision.impl.tree;

import ao.ai.classify.decision.impl.classification.raw.Prediction;
import ao.ai.classify.decision.impl.input.raw.example.*;
import ao.ai.classify.decision.impl.model.raw.Classifier;
import ao.ai.classify.decision.impl.model.raw.ClassifierImpl;
import ao.util.data.AutovivifiedMap;
import ao.util.math.rand.Rand;
import ao.util.math.stats.Info;
import ao.util.misc.Factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class GeneralTreeTest
{
    //---------------------------------------------------------------------
    private GeneralTreeTest() {}


    //---------------------------------------------------------------------
    public static void main(String[] args) throws Throwable
    {
//        new GeneralTreeTest().testMultivalue();
//        new GeneralTreeTest().testContinuous();
        new GeneralTreeTest().testGus();
    }


    //---------------------------------------------------------------------
    public void testGus() throws IOException
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

//        examples.add(example);
//
//        learner.set( examples );
//        System.out.println(learner);

        for (String event : byEvent.keySet()) {
            System.out.println(event);

            LearningSet examples = new LearningSet();
            Classifier  learner  = new ClassifierImpl(
                    new GeneralTreeLearner());

            for (Map.Entry<String, List<Example>> e : byEvent.entrySet()) {
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
                System.out.println(
                        prob + "\t" + rand + "\t" + delta
                             + "\t" + Info.cost(prob));
            }
            System.out.println(acc);
        }
    }

    private double parsePercentage(String percentage)
    {
        return Double.parseDouble(
                    percentage.replace("%", "")) / 100;
    }


    //---------------------------------------------------------------------
    public void testContinuous()
    {
        LearningSet examples = new LearningSet();
        Classifier  learner  = new ClassifierImpl(
                                        new GeneralTreeLearner());

        for (int i = 0; i < 40; i++)
        {
            double temp = Rand.nextDouble();

            TempClass clazz = TempClass.fromTemp(temp);
            examples.add(
                    new ExampleImpl(
                            new ContextImpl(Arrays.asList(
                                    new Datum("temp", temp))),
                            new Datum("target", clazz)));
        }
        learner.set( examples );
        System.out.println(learner);
//        System.out.println(
//                learner.regress(context(true, false, true, true)));
    }

    private static enum TempClass
    {
        COLD(0.33), FRIDGIT(0.61), WARM(0.66), HOT(0.9), BOILING(1);

        private final double  lessThan;
        private TempClass(double lt) {lessThan = lt;}

        public static TempClass fromTemp(double temp)
        {
            for (TempClass tempClass : values())
            {
                if (temp < tempClass.lessThan)
                {
                    return tempClass;
                }
            }
            return null;
        }
    }


    //---------------------------------------------------------------------
    public void testMultivalue()
    {
        LearningSet examples = new LearningSet();
        Classifier  learner  = new ClassifierImpl(
                                        new GeneralTreeLearner() );

        // (a ^ b) v (c ^ d)
        for (int i = 0; i < 20; i++)
        {
            boolean yesNo[] = new boolean[]{true, false};
            for (boolean a : yesNo)
            {
                for (boolean b : yesNo)
                {
                    for (boolean c : yesNo)
                    {
                        for (boolean d : yesNo)
                        {
                            boolean func = (a && b) || (c && d);
                            if (Rand.nextDouble() < 0.5) // introduce noice
                            {
                                func = !func;
                            }

                            examples.add( function(a, b, c, d, func) );
                        }
                    }
                }
            }
        }
        learner.set( examples );

        System.out.println(
                learner.classify(context(true, false, true, true)));
    }

    private Example function(Boolean... vars)
    {
        return context(Arrays.copyOf(vars, vars.length - 1)).
                withTarget(new Datum(vars[ vars.length - 1 ]));
    }

    private Context context(Boolean... vars)
    {
        LinkedList<Datum> varAttributes = new LinkedList<Datum>();
        Integer type = 0;
        for (Boolean var : vars)
        {
            varAttributes.add(
                    new Datum(String.valueOf(type++), var) );
        }
        return new ContextImpl(varAttributes);
    }
}
