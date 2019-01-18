package ao.ai.test;

import ao.ai.classify.decision.DecisionTreeAlgo;
import ao.ai.classify.decision.impl.model.processed.LocalClassifier;
import ao.ai.classify.decision.impl.random.RandomLearner;
import ao.ai.model.common.data.NumCatList;
import ao.ai.model.common.data.impl.BitSetGrid;
import ao.ai.model.common.data.impl.NumCatListImpl;
import ao.ai.model.ml.v2.algo.MultiAlgo;
import ao.ai.model.ml.v2.clazz.MultiClass;
import ao.ai.model.ml.v2.clazz.impl.MultiClassImpl;
import ao.ai.model.ml.v2.example.Example;
import ao.ai.model.ml.v2.example.impl.ExampleImpl;
import ao.ai.model.ml.v2.theory.MultiTheory;
import ao.util.io.AoFiles;
import ao.util.math.rand.Rand;
import ao.util.pass.DataSink;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date: Aug 15, 2010
 * Time: 6:41:47 PM
 */
public class SyntheticCharTest
{
    //------------------------------------------------------------------------
    private static final Logger LOG = LoggerFactory.getLogger(
            SyntheticCharTest.class);

    private static final Pattern INDEX_NAME_PAT  = Pattern.compile(
            ".*?(\\d+)-(\\d+).*");

    private static final Pattern INDEX_IMAGE_PAT = Pattern.compile(
            ".*?(\\d+)-(\\d+)-.*");


    //------------------------------------------------------------------------
    public static void main(String[] args)
    {
//        Stopwatch stopWatch = new Stopwatch();

        LocalClassifier.Factory algoFactory =
//                new GeneralTreeLearner.Factory();
                new RandomLearner.Factory();

        System.out.println("Using: " +
                algoFactory.newInstance());

//        double scaleFraction = 1.0;
        for (double sampleFraction  = 1.0;
                    sampleFraction  > 0.0;
                    sampleFraction -= 0.1)
        {
            List<Example<NumCatList, MultiClass>> data =
                    parseLineSamples(
//                            new File("C:/~/data/ocr/0.1.0"),
//                            new File("C:/~/data/ocr/0.1.1"),
//                            new File("C:/~/data/ocr/0.1.3"),
                            new File("/home/alex/data/ocr/0.2.1"),
                            sampleFraction);

//                    parseSamples(
//    //                        new File("input/ocr") );
//                            new File("C:/~/data/ocr/0.0.2"),
//                            sampleFraction, scaleFraction);

//            System.out.println(
//                    data.size()    + " | " +
//                    sampleFraction + " | " +
//                    scaleFraction  + " | " +
//                    stopWatch);

            for  (int i = 0; i < 20; i++)
            {
                System.out.println(
                        data.size() + "\t" +
                        runTest( data, algoFactory ));
            }
        }
    }

    private static double runTest(
            List<Example<NumCatList, MultiClass>> data,
            LocalClassifier.Factory                algoFactory)
    {
        List<Example<NumCatList, MultiClass>>
                training = Lists.newArrayList();
        List<Example<NumCatList, MultiClass>>
                testing  = Lists.newArrayList();

        double testProb = 0.10;
        for (Example<NumCatList, MultiClass> datum : data)
        {
            ((Rand.nextBoolean( testProb ))
              ? testing : training
            ).add( datum );
        }
//        System.out.println(
//                training.size() + " | " +
//                testing .size() + "\t\t" +
//                "Learning at: " + stopWatch);

        MultiAlgo<NumCatList> algo =
                new DecisionTreeAlgo( algoFactory );
        MultiTheory<NumCatList> theory =
                algo.learn(training);

        int correct = 0;
        for (Example<NumCatList, MultiClass> datum : testing)
        {
            MultiClass clazz = theory.classify( datum.input() );
            if (clazz.equals( datum.output() ))
            {
                correct++;
            }
        }

        return ((double) correct) / testing.size();
    }


    //------------------------------------------------------------------------
    public static List<Example<NumCatList, MultiClass>>
            parseLineSamples(
                final File   dataDir,
                final double sampleFraction)
    {
        final NavigableSet<String> lineFiles = Sets.newTreeSet();
        Set<File> indexFiles = Sets.newTreeSet();
        for (File datum : dataDir.listFiles())
        {
            if (datum.getName().endsWith(".csv"))
            {
                indexFiles.add( datum );
            }
            else if (datum.getName().endsWith(".png"))
            {
                lineFiles .add( datum.getName() );
            }
        }

        final List<Example<NumCatList, MultiClass>>
                examples = Lists.newArrayList();
        final Map<Character, Integer> charToIndex =
                Maps.newHashMap();

        for (File indexFile : indexFiles)
        {
            Matcher indexFileNameMatcher =
                    INDEX_NAME_PAT.matcher(
                            indexFile.getName() );
            if (! indexFileNameMatcher.matches()) {
                continue;
            }

            final long runId     = Long.parseLong(
                    indexFileNameMatcher.group(1));
            final int  stdWidth  = Integer.parseInt(
                    indexFileNameMatcher.group(2));
//            int  stdHeight = Integer.parseInt(
//                    indexFileNameMatcher.group(3));

            AoFiles.read(indexFile, new DataSink<BufferedReader>() {
                @Override public void process(
                        BufferedReader input) throws Exception
                {
                    for (String nextLine;
                               (nextLine = input.readLine()) != null;)
                    {
                        long lineId    = -1;
                        int  offset    = -1;
//                        char character = 0;

                        Map<Integer, Character> offsetToCharacter =
                                Maps.newHashMap();

                        for (String token :
                                Splitter.on(
                                        '\t'
                                ).split( nextLine ))
                        {
                            if (lineId == -1) {
                                lineId = Long.parseLong( token );
                            } else if (offset == -1) {
                                offset = Integer.parseInt( token );
                            } else if (token.length() > 0) {
                                char character = token.charAt( 0 );

                                if (Rand.nextBoolean( sampleFraction ))
                                {
                                    offsetToCharacter.put(
                                            offset, character);
//                                            offset, token.charAt( 0 ));
                                }

                                offset    = -1;
//                                character = 0;
                            } else {
//                                throw new IllegalStateException();
                                break;
                            }
                        }

                        String lineFileNamePrefix =
                                runId + "-" + lineId + "-";
                        String lineFileName = lineFiles.higher(
                                lineFileNamePrefix);
                        if (lineFileName.startsWith(
                                lineFileNamePrefix))
                        {
                            addLineExample(
                                    examples,
                                    charToIndex,
                                    stdWidth,
                                    offsetToCharacter,
                                    new File(dataDir, lineFileName));
                        }
                    }
                }});
        }

        return examples;
    }

    private static void addLineExample(
            List<Example<NumCatList, MultiClass>> examples,
            Map<Character, Integer> charToIndex,
            int                     stdWidth,
            Map<Integer, Character> offsetToCharacter,
            File                    lineImageFile)
    {
        BufferedImage lineImage =
                toBufferedImage( lineImageFile );

        for (Map.Entry<Integer, Character>
                e : offsetToCharacter.entrySet())
        {
            Integer charIndex = charToIndex.get( e.getValue() );
            if (charIndex == null) {
                charIndex = charToIndex.size();
                charToIndex.put( e.getValue(), charIndex );
            }

            BitSetGrid charGrid = toBlackAndWhite(
                    lineImage.getSubimage(
                            e.getKey(), 0,
                            stdWidth, lineImage.getHeight()
                    ), 1.0);

            Example<NumCatList, MultiClass> example =
                    new ExampleImpl<NumCatList, MultiClass>(
                            new NumCatListImpl( charGrid  ),
                            MultiClassImpl.create( charIndex ));
            examples.add( example );
        }
    }


    //------------------------------------------------------------------------
    public static List<Example<NumCatList, MultiClass>>
            parseSamples(File   dataDir,
                         double sampleFraction,
                         double scale)
    {
        List<Example<NumCatList, MultiClass>>
                examples = Lists.newArrayList();

        Map<Character, Integer> charToIndex =
                Maps.newHashMap();
        for (File datum : dataDir.listFiles())
        {
            if (! Rand.nextBoolean( sampleFraction ))
            {
                continue;
            }

            Character character =
                    datum.getName().charAt( 0 );

//            if (! (character == 'A' ||
//                   character == 'B' ))
//            {
//                continue;
//            }

            BufferedImage asBufferedImage =
                    toBufferedImage( datum );
            
            Integer   targetNum =
                    charToIndex.get( character );
            if (targetNum == null)
            {
                targetNum = charToIndex.size();
                charToIndex.put( character, targetNum );
            }

            Example<NumCatList, MultiClass> example =
                    new ExampleImpl<NumCatList, MultiClass>(
                            new NumCatListImpl(
                                    toBlackAndWhite(
                                            asBufferedImage,
                                            scale)),
                            MultiClassImpl.create(
                                    targetNum));

            examples.add( example );
        }

        return examples;
    }


    //------------------------------------------------------------------------
    private static BitSetGrid toBlackAndWhite(
            BufferedImage image, double scale)
    {
        assert 0 <= scale && scale <= 1.0;

        BitSetGrid grid = new BitSetGrid(
                (int) Math.round(image.getHeight() * scale),
                (int) Math.round(image.getWidth () * scale));

//        for (int row = 0; row < image.getHeight(); row++)
//        {
//            for (int col = 0; col < image.getWidth (); col++)
//            {
//                int x = col;
//                int y = row;

        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
                int row = Math.min(grid.rows()    - 1,
                                   (int) Math.round(y * scale));
                int col = Math.min(grid.columns() - 1,
                                   (int) Math.round(x * scale));

                Color colour = new Color(
                        image.getRGB(x, y));

                int averageIntensity =
                        (colour.getRed  () +
                         colour.getGreen() +
                         colour.getBlue () ) / 2;

                boolean isBlack = (averageIntensity < 128);

                grid.set(row, col, isBlack);
            }
        }

        return grid;
    }


    private static BufferedImage toBufferedImage(
            File imageLocation)
    {
        try
        {
            return ImageIO.read( imageLocation );
        }
        catch (IOException e)
        {
            LOG.debug("Can't read image", e);

//            throw Throwables.propagate( e );
            return null;
        }
    }
}
