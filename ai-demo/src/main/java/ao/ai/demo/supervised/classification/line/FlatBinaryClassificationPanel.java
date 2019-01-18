package ao.ai.demo.supervised.classification.line;

/**
 * User: aostrovsky
 * Date: 5-Feb-2010
 * Time: 11:45:22 PM
 */
public class FlatBinaryClassificationPanel
//        extends    JPanel
//        implements MouseListener,
//                   MouseMotionListener
{
//    //-------------------------------------------------------------------------
//    private static final Logger LOG =
//            LoggerFactory.getLogger( FlatBinaryClassificationPanel.class );
//
//    private static final int   MAX_EXAMPLES   = 1024;
//
//    private static final Color BACK_GROUND    = Color.BLACK;
//    private static final Color POSITIVE       = Color.RED;
//    private static final Color POSITIVE_GUESS = POSITIVE.darker();
//    private static final Color NEGATIVE       = Color.BLUE;
//    private static final Color NEGATIVE_GUESS = NEGATIVE.darker();
//
//
//    //-------------------------------------------------------------------------
//    public static void main(String[] args)
//            throws InvocationTargetException, InterruptedException
//    {
//        final FlatBinaryClassificationPanel panel =
//                new FlatBinaryClassificationPanel();
//
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        frame.setContentPane( panel );
//        frame.pack();
//        frame.setSize(800, 500);
//        frame.setVisible( true );
//
//        while (true)
//        {
//            panel.waitForNewExamples();
//            panel.makePredictions();
//
//            SwingUtilities.invokeAndWait(new Runnable() {
//                @Override public void run() {
//                    panel.repaint();
//                }
//            });
//        }
//    }
//
//
//    //-------------------------------------------------------------------------
//    private final LinkedList<XyBinaryExample> examples;
//    private final List<XyBinaryExample>       predictions;
//
//    private final Condition available;
//
//
//    //-------------------------------------------------------------------------
//    public FlatBinaryClassificationPanel()
//    {
//        examples    = new LinkedList<XyBinaryExample>();
//        predictions = new ArrayList<XyBinaryExample>();
//
//        available   = new Condition(false);
//
//        addMouseListener( this );
//        addMouseMotionListener( this );
//    }
//
//
//    //-------------------------------------------------------------------------
//    private void waitForNewExamples()
//    {
//        try {
//            available.waitForTrue();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        available.setFalse();
//    }
//
//
//    //-------------------------------------------------------------------------
//    private synchronized void makePredictions()
//    {
//        if (examples.size() < 1) {
//            return;
//        }
//
////        SupervisedPlainLearner<
////                        NumericalFeatureList,
////                        SingleBinaryFeature>
////                learner =
//////                    new MeanBinaryClassifier();
//////                    new LogisticStochasticGradientAscent();
////                    new MinimumInformationBinaryClassifier();
//
//        BinClassLearner learner =
//                new MinInfoNumBinClassifier();
//
//        SupervisedHypothesis<
//                        NumericalFeatureList,
//                        SingleBinaryFeature>
//            hype = learner.learn( examples );
//
//        predictions.clear();
//        for (int w = 0; w < getWidth(); w += 10)
//        {
//            for (int h = 0; h < getHeight(); h += 10)
//            {
//                try {
//                    boolean isPositive =
//                            hype.regress(
//                                    new FeatureVector(null, w, h)
//                            ).binaryCategory();
//
//                    predictions.add(
//                            new XyBinaryExample(
//                                    w, h, isPositive));
//                } catch (Throwable ignored) {}
//            }
//        }
//    }
//
//
//    //-------------------------------------------------------------------------
//    @Override
//    public synchronized void paint(Graphics graphics)
//    {
//        graphics.setColor( BACK_GROUND );
//        graphics.fillRect(
//                0, 0, getWidth(), getHeight());
//
//        paint(graphics, POSITIVE      , NEGATIVE      , examples   , 2);
//        paint(graphics, POSITIVE_GUESS, NEGATIVE_GUESS, predictions, 1);
//    }
//
//    private void paint(
//            Graphics              graphics,
//            Color                 positiveColour,
//            Color                 negativeColour,
//            List<XyBinaryExample> examples,
//            int                   size)
//    {
//        for (XyBinaryExample point : examples)
//        {
//            graphics.setColor(
//                    point.output().binaryCategory()
//                    ? positiveColour : negativeColour);
//
//            double[] input = point.input().doubleValues();
//            graphics.drawRect(
//                    (int) Math.round(input[0]),
//                    (int) Math.round(input[1]),
//                    size, size);
//        }
//    }
//
//
//    //-------------------------------------------------------------------------
//    @Override public void mouseReleased(MouseEvent e) {}
//    @Override public void mouseEntered (MouseEvent e) {}
//    @Override public void mouseExited  (MouseEvent e) {}
//    @Override public void mouseMoved   (MouseEvent e) {}
//    @Override public void mouseClicked (MouseEvent e) {}
//
//    private boolean previousClicked = false;
//
//    @Override public void mousePressed (MouseEvent e) {
//        previousClicked = isPositive(e);
//        userInput( e );
//    }
//
//    @Override public void mouseDragged(MouseEvent e) {
//        userInput( e );
//    }
//
//    private boolean isPositive(MouseEvent e)
//    {
//        return (e.getButton() == MouseEvent.BUTTON1);
//    }
//
//    private synchronized void userInput(MouseEvent e)
//    {
//        examples.add(new XyBinaryExample(
//                e.getX(), e.getY(), previousClicked));
//
//        if (examples.size() > MAX_EXAMPLES)
//        {
//            examples.removeFirst();
//        }
//
//        available.setTrue();
//    }
}