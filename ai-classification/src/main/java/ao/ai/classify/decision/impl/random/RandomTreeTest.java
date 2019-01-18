package ao.ai.classify.decision.impl.random;

import ao.ai.classify.decision.impl.input.raw.example.*;
import ao.ai.classify.decision.impl.model.raw.Classifier;
import ao.ai.classify.decision.impl.model.raw.ClassifierImpl;
import ao.util.math.rand.Rand;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class RandomTreeTest
{
    //---------------------------------------------------------------------
    private RandomTreeTest() {}


    //---------------------------------------------------------------------
    public static void main(String[] args)
    {
        new RandomTreeTest().testMultivalue();
    }


    //---------------------------------------------------------------------
    public void testMultivalue()
    {
        LearningSet examples = new LearningSet();
        Classifier  learner  = new ClassifierImpl( new RandomLearner() );

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
                withTarget( new Datum(vars[ vars.length - 1 ]) );
    }

    private Context context(Boolean... vars)
    {
        List<Datum> varAttributes = new LinkedList<Datum>();
        int         type          = 0;
        for (Boolean var : vars)
        {
            varAttributes.add(
                    new Datum(String.valueOf(type++), var));
        }
        return new ContextImpl(varAttributes);
    }
}
