package ao.ai.demo.supervised.classification.fin;

import ao.ai.classify.decision.DecisionTreeLearner;
import ao.ai.classify.decision.impl.random.RandomLearner;
import ao.ai.classify.decision.impl.tree.GeneralTreeLearner;
import ao.ai.classify.online.forest.OnlineRandomForest;
import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.algo.adapt.OfflineToOnlineMultiLearner;
import ao.ai.ml.model.algo.adapt.OnlineMultiToBinaryLeaner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.math.rand.Rand;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Date: 6/19/11
 * Time: 5:20 PM
 */
public class StockPredictor
{
    //------------------------------------------------------------------------
    private static final File HISTORICAL_PRICE_FILE = new File(
            "C:/~/data/stock/cm.csv");

    private static final Splitter COMMA_SPLITTER = Splitter.on(',');


    //------------------------------------------------------------------------
    public static void main(String[] args) throws IOException
    {
        Rand.randomize();

        int    runNumber = 1;
        double scoreSum  = 0;
        for (int i = 3; i <= 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                double score = run( i );
                scoreSum += score;

                System.out.println(
                        i + "\t" + score + "\t" +
                        (scoreSum / runNumber));
                runNumber++;
            }
        }
    }

    public static double run(
            int runNumber) throws IOException
    {
        FeatureExtractor<RealList, RealList> extractor =
                new FeatureExtractor.Identity<RealList>();
//                new AoFeatureExtractor( runNumber );

        OnlineBinaryLearner<RealList> classifier =
//                new AlwaysIncrease();
                new EugeneAttempt();
//                new OnlineMultiToBinaryLeaner<RealList>(
//                        new OnlineRandomForest());
//                new RandomTreeLearner();
//                new OnlineMultiToBinaryLeaner<RealList>(
//                        new OfflineToOnlineMultiLearner<RealList>(
//                    DecisionTreeLearner.createMulti(
//                            new GeneralTreeLearner.Factory())));
//                            new RandomLearner.Factory());

        double[][] features = readFeatures(
//                1000);
                Integer.MAX_VALUE);

        int correct = 0;
        int total   = 0;

        double   previousClose                   = -1;
        boolean  previousGuessIsNextWillBeHigher = false;
        RealList previousInputFeature            = null;

        for (double[] example : features)
        {
            RealList inputFeature = extractor.extract(
                    new RealList( example ));

            BinaryClass nextWillBeHigher =
                    classifier.classify( inputFeature );

            if (previousClose != -1)
            {
                boolean isClosingPriceGreaterThanPrevious =
                        (example[0] >= previousClose);

                boolean previousPredictionWasCorrect =
                        (previousGuessIsNextWillBeHigher ==
                                isClosingPriceGreaterThanPrevious);

                if (previousPredictionWasCorrect)
                {
                    correct++;
                }

                classifier.learn(
                        previousInputFeature,
                        BinaryClass.create(
                                isClosingPriceGreaterThanPrevious ));

                total++;
            }

            previousClose = example[0];
            previousInputFeature = inputFeature;
            previousGuessIsNextWillBeHigher =
                    nextWillBeHigher.isPositive();
        }

        return ((double) correct) / total;
    }


    //------------------------------------------------------------------------
    private static double[][] readFeatures(
            int maxLines) throws IOException
    {
        List<String> lines = Files.readLines(
                HISTORICAL_PRICE_FILE,
                Charsets.US_ASCII);

        double[][] adjustedClosePrices = new double[
                Math.min(maxLines, lines.size() - 1)][];

        for (int i = 0; i < adjustedClosePrices.length; i++)
        {
            Iterator<String> parts =
                    COMMA_SPLITTER
                            .split( lines.get(lines.size() - i - 1) )
                            .iterator();

            parts.next(); // date
            double open = Double.parseDouble(parts.next()); // open
            double high = Double.parseDouble(parts.next()); // high
            double low  = Double.parseDouble(parts.next()); // low
            double close = Double.parseDouble(parts.next()); // close
            double volume = Double.parseDouble(parts.next()); // volume

            double adjustedClose = Double.parseDouble(
                    parts.next());

            adjustedClosePrices[ i ] =
//                    new double[]{adjustedClose, open, high, low, volume};
//                    new double[]{adjustedClose/*, open, high, low, volume*/};
//                    new double[]{adjustedClose, volume};
                    new double[]{adjustedClose};
        }

        return adjustedClosePrices;
    }
}
