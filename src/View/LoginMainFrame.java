package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginMainFrame extends JFrame {
    private LoginMainPanel loginMainPanel;
    private String WINDOW_TITLE = "JPotify";
    private FrameComponent frame;
    private BackgroundComponentDragger backgroundComponentDragger;
    private Boolean fullScreenMode;
    private MainFrame mainFrame;
    public LoginMainFrame() {
        super();
        loginMainPanel = new LoginMainPanel(970, 600);
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
        loginMainPanel.addMouseMotionListener(backgroundComponentDragger);
        Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, 950, 600);
        this.setUndecorated(true);
        this.add(frame);
        this.add(loginMainPanel);
        this.setMinimumSize(new Dimension(970, 600));
        this.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                Dimension sizeIn = ((JFrame) e.getComponent()).getContentPane().getSize();
                if (sizeIn.getWidth() < 970)
                    sizeIn.width = 970;
                if (sizeIn.getHeight() < 600)
                    sizeIn.height = 600;
                frame.setSize(sizeIn);
                frame.revalidate();
                loginMainPanel.setSize(sizeIn);
                loginMainPanel.repaint();
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

        this.setVisible(true);
    }
    public void setFullScreenMode(Boolean fullScreenMode)
    {
        this.fullScreenMode = fullScreenMode;
    }

    public Boolean getFullScreenMode()
    {
        return fullScreenMode;
    }

    public LoginMainPanel getLoginMainPanel()
    {
        return loginMainPanel;
    }

    public void setMainFrame(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        loginMainPanel.setMainFrame(mainFrame);
    }
}
