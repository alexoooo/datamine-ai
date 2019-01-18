package ao.ai.test;

import ao.ai.classify.decision.DecisionTreeAlgo;
import ao.ai.classify.decision.impl.tree.GeneralTreeLearner;
import ao.ai.model.common.data.BinGrid;
import ao.ai.model.common.data.NumCatList;
import ao.ai.model.common.data.impl.BitSetGrid;
import ao.ai.model.common.data.impl.NumCatListImpl;
import ao.ai.model.ml.v2.algo.MultiAlgo;
import ao.ai.model.ml.v2.clazz.MultiClass;
import ao.ai.model.ml.v2.clazz.impl.MultiClassImpl;
import ao.ai.model.ml.v2.example.Example;
import ao.ai.model.ml.v2.example.impl.ExampleImpl;
import ao.ai.model.ml.v2.theory.MultiTheory;
import ao.util.math.rand.Rand;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 5:15:03 PM
 */
public class SemeionTest
{
    //-------------------------------------------------------------------------
    private static final double trainPercent = 0.5;

    private static final int rows  = 16;
    private static final int cols  = 16;
    private static final int types = 10;


    //-------------------------------------------------------------------------
    public static void main(String[] args) throws IOException
    {
        List<Example<NumCatList, MultiClass>> data =
                parseFlat("/semeion.data.txt");
//                parseFlat("/home/alex/proj/ai/ai-test/src/" +
//                            "main/resources/semeion.data.txt");
        System.out.println("data size = " + data.size());

        MultiAlgo<NumCatList> algo =
                new DecisionTreeAlgo(
                        new GeneralTreeLearner.Factory());
//                        new RandomLearner.Factory());

        List<Example<NumCatList, MultiClass>> trainingData =
                new ArrayList<Example<NumCatList, MultiClass>>();
        List<Example<NumCatList, MultiClass>> testingData  =
                new ArrayList<Example<NumCatList, MultiClass>>();

        for (Example<NumCatList, MultiClass> datum : data)
        {
            (Rand.nextBoolean(trainPercent)
                ? trainingData : testingData
            ).add( datum );
        }

        MultiTheory<NumCatList> theory =
                algo.learn( trainingData );

        int correct = 0, total = 0;
        for (Example<NumCatList, MultiClass> datum :
                testingData)
        {
//            if (! Rand.nextBoolean(testPercent)) continue;

            MultiClass clazz = theory.classify( datum.input() );
            if (clazz.equals( datum.output() ))
            {
                correct++;
            }
            total++;
        }

        System.out.println(
                ((double) correct) / total);
    }


    //-------------------------------------------------------------------------
    private static List<Example<NumCatList, MultiClass>>
            parseFlat(String file) throws IOException
    {
        BufferedReader lines = new BufferedReader(new InputStreamReader(
                SemeionTest.class.getResourceAsStream( file )));
//                new FileReader(file));

        List<Example<NumCatList, MultiClass>> data =
                Lists.newArrayList();

        String nextLine;
        while ((nextLine = lines.readLine()) != null)
        {
            Example<BinGrid, MultiClass> example =
                    parse( nextLine );

            data.add(new ExampleImpl<NumCatList, MultiClass>(
                    new NumCatListImpl( example.input() ),
                    example.output()));
        }

        lines.close();

        return data;
    }


    //-------------------------------------------------------------------------
    private static Example<BinGrid, MultiClass>
            parse(String line)
    {
        String[] tokens = line.split("\\s+");
        assert tokens.length == (rows * cols + types);

        BitSetGrid grid = new BitSetGrid(rows, cols);

        int index = 0;
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                String token = tokens[ index ];
                double value = Double.parseDouble( token );

                grid.set(row, col, value > 0.5);

                index++;
            }
        }

        MultiClass target = null;
        for (int type = 0;
                 type < types;
                 type++)
        {
            if (tokens[ index + type ].equals("1"))
            {
                target = MultiClassImpl.create( type );
            }
        }

        return new ExampleImpl<BinGrid, MultiClass>(
                grid, target);
    }
}
