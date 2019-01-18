package ao.ai.test;

import ao.ai.classify.online.forest.OnlineRandomForest;
import ao.ai.ml.model.algo.OfflineMultiLearner;
import ao.ai.ml.model.algo.adapt.OnlineToOfflineMultiLearner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.input.alt.BinGrid;
import ao.ai.ml.model.input.coll.DataSet;
import ao.ai.ml.model.input.coll.Example;
import ao.ai.ml.model.input.coll.Sample;
import ao.ai.ml.model.output.MultiClass;
import ao.ai.ml.model.theory.MultiClassifier;
import ao.util.io.AoFiles;
import ao.util.math.rand.Rand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: alex
 * Date: 16-May-2010
 * Time: 7:36:40 PM
 */
public class OptDigitsTest
{
    //-------------------------------------------------------------------------
    private static final int rows  = 8;
    private static final int cols  = 8;


    //-------------------------------------------------------------------------
    public static void main(String[] args) throws IOException
    {
        Rand.randomize();

        Sample<RealList, MultiClass> training =
                parseFlat(AoFiles.resource("optdigits.tra"));

        Sample<RealList, MultiClass> testing =
                parseFlat(AoFiles.resource("optdigits.tes"));

        System.out.println("training size = " + training.size());
        System.out.println("testing size = " + testing.size());

        for (int i = 0; i < 100; i++)
        {
            performTest(training, testing);
        }
    }

    private static void performTest(
            Sample<RealList, MultiClass> training,
            Sample<RealList, MultiClass> testing)
    {
        OfflineMultiLearner<RealList> algo =
//                DecisionTreeLearner.createMulti(
//                        new GeneralTreeLearner.Factory());
//                        new RandomLearner.Factory());
                new OnlineToOfflineMultiLearner<RealList>(
//                        new OnlineBinaryVectorBiasLearner(
//                                new Perceptron()
//                                new FirstOrderSvm()
//                                new PassiveAggressive()
//                                new SpaLearner()
                        new OnlineRandomForest()
//                        new OnnLearner()
                , 1);

//        long beforeLearn = System.currentTimeMillis();
        MultiClassifier<RealList> theory = algo.learn(training);

        long beforeValidation = System.currentTimeMillis();
//        System.out.println(
//                "Learning took: " +
//                (beforeValidation - beforeLearn));

//        int n = 0;
        int correct = 0;
        for (Example<RealList, MultiClass> datum : testing)
        {
//            if (n++ % 50 == 0) {
//                System.out.print("\n" + (n / 50 + 1) + "\t");
//            }

            MultiClass clazz = theory.classify( datum.input() );
            if (clazz.best() == datum.output().best())
            {
                correct++;
//                System.out.print("_");
            }
            else
            {
//                System.out.print("x");
            }
        }

//        System.out.println();
        System.out.println(
                ((double) correct) / testing.size());
//        System.out.println(
//                "Validation took: " +
//                (System.currentTimeMillis() - beforeValidation));
    }


    //-------------------------------------------------------------------------
    private static Sample<RealList, MultiClass>
            parseFlat(File file) throws IOException
    {
        BufferedReader lines = new BufferedReader(
                new FileReader(file));

        DataSet<RealList, MultiClass> data =
                new DataSet<RealList, MultiClass>();

        String nextLine;
        while ((nextLine = lines.readLine()) != null)
        {
            Example<BinGrid, MultiClass> example =
                    parse( nextLine );

            data.add(example.input().toRealList(),
                     example.output());
        }

        lines.close();

        return data.toSample();
    }


    //-------------------------------------------------------------------------
    private static Example<BinGrid, MultiClass>
            parse(String line)
    {
        String[] tokens = line.split(",");
        assert tokens.length == (rows * cols + 1);

        BinGrid grid = new BinGrid(rows, cols);

        int index = 0;
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                String token = tokens[ index ];
                double value = Double.parseDouble( token );

                grid.set(row, col, value >= 0.5);

                index++;
            }
        }

        int targetNum = Integer.parseInt(
                tokens[tokens.length - 1]);
        MultiClass target = MultiClass.create( targetNum );

        return new Example<BinGrid, MultiClass>(
                grid, target);
    }
}
