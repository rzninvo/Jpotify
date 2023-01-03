package View;

import Model.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class MainFrame extends JFrame
{
    private final String WINDOW_TITLE = "JPotify";
    public static int width = 950;
    public static int height = 600;
    private final int X = 0;
    private final int Y = 0;
    private MainPanel mainPanel;
    private FrameComponent frame;
    private BackgroundComponentDragger backgroundComponentDragger;
    private Boolean fullScreenMode;
    public MainFrame()
    {
        super();
        mainPanel = new MainPanel(950, 600);
        this.setTitle(WINDOW_TITLE);
        frame = new FrameComponent(new Insets(5, 5, 5, 5));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/View/Icons/JPotify.png"));
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.fullScreenMode = false;
        backgroundComponentDragger = new BackgroundComponentDragger(this);
        ComponentBorderDragger controller = new ComponentBorderDragger(this,
                new Insets(5, 5, 5, 5), new Dimension(10, 10));
        frame.addMouseMotionListener(controller);
        mainPanel.addMouseMotionListener(backgroundComponentDragger);
        Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, 950, 600);
        this.setUndecorated(true);
        this.add(frame);
        this.add(mainPanel);
        this.setMinimumSize(new Dimension(950, 600));
        this.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                Dimension sizeIn = ((JFrame) e.getComponent()).getContentPane().getSize();
                if (sizeIn.getWidth() < 950)
                    sizeIn.width = 950;
                if (sizeIn.getHeight() < 600)
                    sizeIn.height = 600;
                frame.setSize(sizeIn);
                frame.revalidate();
                width = (int)sizeIn.getWidth();
                height = (int)sizeIn.getHeight();
                mainPanel.setSize(sizeIn);
                mainPanel.update();
                mainPanel.repaint();
            }
        });
        KeyStroke controlA = KeyStroke.getKeyStroke("control shift Q");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(controlA, "EXIT"); //$NON-NLS-1$
        getRootPane().getActionMap().put("EXIT", new AbstractAction(){ //$NON-NLS-1$
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        this.setVisible(false);
    }

    public void setFullScreenMode(Boolean fullScreenMode)
    {
        this.fullScreenMode = fullScreenMode;
    }

    public Boolean getFullScreenMode()
    {
        return fullScreenMode;
    }

    public FrameComponent getFrame() {
        return frame;
    }

    public void setFrame(FrameComponent frame) {
        this.frame = frame;
    }

    //    private class CreateMaps
//    {
//        private ActionMaps actionMaps = new ActionMaps();
//        private InputMaps inputMaps ;
//
//        public CreateMaps(JComponent comp)
//        {
//            inputMaps = new InputMaps(comp);
//            HashMap<KeyStroke,String> strokes = new HashMap<>();
//            KeyStroke exit = KeyStroke.getKeyStroke("control shift Q");
//            strokes.put(exit,"Exit");
//            HashMap<String,AbstractAction> actions = new HashMap<>();
//            AbstractAction abstractAction = new AbstractAction()
//            {
//                @Override
//                public void actionPerformed(ActionEvent e)
//                {
//                    System.exit(0);
//                }
//            };
//            actions.put("Exit",abstractAction);
//        }
//
//        public ActionMaps getActionMaps()
//        {
//            return actionMaps;
//        }
//
//        public InputMaps getInputMaps()
//        {
//            return inputMaps;
//        }
//    }

    public MainPanel getMainPanel()
    {
        return mainPanel;
    }
    
}
