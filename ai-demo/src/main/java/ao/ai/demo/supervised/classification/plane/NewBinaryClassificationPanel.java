package ao.ai.demo.supervised.classification.plane;

import ao.ai.classify.online.forest.OnlineRandomForest;
import ao.ai.ml.model.algo.OnlineBinaryLearner;
import ao.ai.ml.model.algo.adapt.OnlineMultiToBinaryLeaner;
import ao.ai.ml.model.input.RealList;
import ao.ai.ml.model.input.coll.Example;
import ao.ai.ml.model.output.BinaryClass;
import ao.util.math.rand.Rand;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 11:45:22 PM
 */
public class NewBinaryClassificationPanel
        extends    JPanel
        implements MouseListener,
                   MouseMotionListener
{
    //-------------------------------------------------------------------------
//    private static final Logger LOG =
//            LoggerFactory.getLogger( NewBinaryClassificationPanel.class );

    private static final Color BACK_GROUND    = Color.BLACK;
    private static final Color POSITIVE       = Color.RED;
    private static final Color POSITIVE_GUESS = POSITIVE.darker();
    private static final Color NEGATIVE       = Color.BLUE;
    private static final Color NEGATIVE_GUESS = NEGATIVE.darker();


    //-------------------------------------------------------------------------
    public static void main(String[] args)
            throws InvocationTargetException, InterruptedException
    {
        Rand.randomize();

        final NewBinaryClassificationPanel classPanel =
                new NewBinaryClassificationPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setContentPane( classPanel );
        frame.pack();
        frame.setSize(800, 500);
        frame.setVisible( true );

        for (long i = 0; i < Long.MAX_VALUE; i++)
        {
            classPanel.waitForNewExamples();
            classPanel.makePredictions();
            classPanel.refresh(false);

//            SwingUtilities.invokeAndWait(new Runnable() {
////            SwingUtilities.invokeLater(new Runnable() {
//                @Override public void run() {
//                    classPanel.repaint();
//                }
//            });
        }
    }


    //-------------------------------------------------------------------------
    private OnlineBinaryLearner<RealList> newClassLearner()
    {
//        return new OnlineMultiToBinaryLeaner<RealList>(
//                new OfflineToOnlineMultiLearner<RealList>(
//                    DecisionTreeLearner.createMulti(
//                         new GeneralTreeLearner.Factory())));
//                        new RandomLearner.Factory())));

//        return new RandomBinaryClassifier<RealList>();

//        return new OfflineBinaryVectorBiasLearner(
//                new OnlineToOfflineBinaryLearner<RealList>(
////                new OnlineBinaryVectorBiasLearner(
//                        new Perceptron()
////                        new FirstOrderSvm()
////                        new PassiveAggressive()
////                        new OnlineMultiToBinaryLeaner<RealList>(
////                                new SpaLearner())
////                )
//                , 1)
//                );

//        return new MeanBinKernelClassifier();
//        return new MeanBinClassifier();
//        return new OfflineBinaryVectorBiasLearner(
//                new MeanBinClassifier());
//        return new MeanBinKernelClassifier();

//        return new RandomTreeLearner();
        return new OnlineMultiToBinaryLeaner<RealList>(
                new OnlineRandomForest());
//                new OnnLearner());
//        return new OnlineBagging(
//                new Factory<OnlineBinaryLearner<RealList>>() {
//                    @Override public OnlineBinaryLearner<RealList> newInstance() {
//                        return new RandomTreeLearner();
//                    }},
//                128);
    }


    //-------------------------------------------------------------------------
    private       OnlineBinaryLearner<RealList> learner = newClassLearner();

    private final Collection<Example<RealList, BinaryClass>> examples;
    private final List<Example<RealList, BinaryClass>>       predictions;

    private final AoCondition available;


    //-------------------------------------------------------------------------
    public NewBinaryClassificationPanel()
    {
        examples    = Sets .newHashSet();
        predictions = Lists.newArrayList();

        available   = new AoCondition( false );

        addMouseListener( this );
        addMouseMotionListener( this );
    }


    //-------------------------------------------------------------------------
    public synchronized void clear()
    {
        learner = newClassLearner();
        examples.clear();
        predictions.clear();
    }


    //-------------------------------------------------------------------------
    private void waitForNewExamples()
    {
        try {
            available.waitForTrue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        available.setFalse();
    }


    //-------------------------------------------------------------------------
    private synchronized void makePredictions()
    {
//        DataSet<RealList, BinaryClass> data =
//                new DataSet<RealList, BinaryClass>();
//        Collections.shuffle( examples );
//        data.addAll( examples );

//        if (data.size() < 1) {
//            return;
//        }

//        NumBinProbClassLeaner learner = newClassLearner();
//        BinAlgo<NumList> learner = newClassLearner();


//        SupervisedPlainLearner<
//                    NumericalFeatureList,
//                    SingleBinaryFeature>
//                learner =
//                    new MeanBinaryClassifier();
//                    new LogisticStochasticGradientAscent();
//                    new RandomForestBinaryClassifier();
//                    new MinimumInformationBinaryClassifier();

//        NumBinClassifier hype =
//        BinTheory<NumList> hype =
//                learner.learn(
//                        copyOfExamples);
//        BinaryClassifier<RealList> hype =
//                new RandomBinaryClassifier<RealList>();
//                learner.learn( data.toSample() );

//        SupervisedHypothesis<
//                NumericalFeatureList,
//                SingleBinaryFeature>
//            hype = learner.learn(
//                copyOfExamples);

        final List<Example<RealList, BinaryClass>> nextPrediction =
                Lists.newArrayList();

        for (int w = 0; w < getWidth(); w += 10)
        {
            for (int h = 0; h < getHeight(); h += 10)
            {
                try {
                    boolean isPositive =
//                            hype.regress(
//                                    new FeatureVector(null, w, h)
//                            ).binaryCategory();
//                            hype.regress(
//                                    new IntNumList(w, h)
//                            ).isPositive().getBin();
//                            hype.classify(
//                                    new IntNumList(w, h)
                            learner.classify(
                                    new RealList(w, h)
                            ).isPositive();

                    nextPrediction.add(
                            new Example<RealList, BinaryClass>(
                                    new RealList(w, h),
                                    BinaryClass.create( isPositive )));
//                            new XyBinExample(
//                                    w, h, isPositive));
                }
                catch (Throwable e)
                {
                    e.printStackTrace();
                }
            }
        }

        synchronized (this)
        {
            predictions.clear();
            predictions.addAll( nextPrediction );
        }
    }


    //-------------------------------------------------------------------------
    @Override
    public synchronized void paint(Graphics graphics)
    {
        graphics.setColor( BACK_GROUND );
        graphics.fillRect(
                0, 0, getWidth(), getHeight());

        paint(graphics, POSITIVE      , NEGATIVE      , examples   , 2);
        paint(graphics, POSITIVE_GUESS, NEGATIVE_GUESS, predictions, 1);
    }

    private void paint(
            Graphics              graphics,
            Color                 positiveColour,
            Color                 negativeColour,
            Iterable<Example<RealList, BinaryClass>> examples,
            int                   size)
    {
        for (Example<RealList, BinaryClass> point : examples)
        {
            graphics.setColor(
//                    point.classification().isPositive().getBin()
                    point.output().isPositive()
                    ? positiveColour
                    : negativeColour);

            graphics.drawRect(
                    (int) Math.round( point.input().get(0) ),
                    (int) Math.round( point.input().get(1) ),
                    size, size);
        }
    }


    //-------------------------------------------------------------------------
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered (MouseEvent e) {}
    @Override public void mouseExited  (MouseEvent e) {}
    @Override public void mouseMoved   (MouseEvent e) {}

    @Override public void mouseClicked (MouseEvent e)
    {
        if (e.getClickCount() == 3)
        {
            clear();
        }
    }

    private boolean previousClicked = false;

    @Override public void mousePressed (MouseEvent e) {
        previousClicked = isPositive(e);
        userInput( e );
    }

    @Override public void mouseDragged(MouseEvent e) {
        userInput( e );
    }

    private boolean isPositive(MouseEvent e)
    {
        return (e.getButton() == MouseEvent.BUTTON1);
    }

    private synchronized void userInput(MouseEvent e)
    {
        Example<RealList, BinaryClass> example =
                new Example<RealList, BinaryClass>(
                    new RealList(e.getX(), e.getY()),
                    BinaryClass.create( previousClicked ));
        learner.learn( example.input(), example.output() );
        examples.add( example );

        available.setTrue();
        refresh(true);
    }


    //-------------------------------------------------------------------------
    private void refresh(final boolean onlyIfAvailable)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                if (onlyIfAvailable &&
                    ! available.isTrue()) return;
                repaint();
            }
        });
    }
}