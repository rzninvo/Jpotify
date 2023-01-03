package View;

import Controller.AudioPlayer;
import Controller.FileAndFolderBrowsing;
import Listeners.PlayPanelListener;
import Listeners.ResetPlaystate;
import Model.Music;
import Model.Sort;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

public class PlayPanel extends JPanel implements ResetPlaystate
{
    private boolean shuffleState = false;
    private int repeatState = 0;
    static int playState = 0;
    private PlayPanelListener playPanelListener = null;
    private MainPanel mainPanel;
    private JSlider musicSlider = new JSlider();
    private SongPanelForPlayPanel songPanelForPlayPanel = new SongPanelForPlayPanel();
    private LightSliderUI lightSliderUI;
    public PlayPanelListener getPlayPanelListener() {
        return playPanelListener;
    }

    static JLabel shuffle = new JLabel("\uD83D\uDD00") {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(-1 * super.getWidth() + 3, super.getHeight());
        }
    };
    static JLabel skip_backward = new JLabel("⏮") {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(-1 * super.getWidth(), super.getHeight());
        }
    };

    static JLabel play = new JLabel(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35)) {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(0, super.getHeight());
        }
    };
    static JLabel skip_forward = new JLabel("⏭") {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(-10, super.getHeight());
        }
    };
    JLabel repeat = new JLabel("\uD83D\uDD01") {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(24, 24, 24));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(-1 * super.getWidth() + 5, super.getHeight());
        }
    };

    public void setPlayPanelListener(PlayPanelListener playPanelListener) {
        this.playPanelListener = playPanelListener;
    }

    public PlayPanel(MainPanel mainPanel, int width, Vector<Music> playlist, int maxFrameCount) {
        super();
        this.mainPanel = mainPanel;
        setBackground(new Color(40, 40, 40));
        this.setSize(width, 88);
        ListenerForMouse listenerForMouse = new ListenerForMouse();
        skip_backward.setFont(new Font(skip_backward.getFont().getName(), Font.PLAIN, 20));
        skip_forward.setFont(new Font(skip_forward.getFont().getName(), Font.PLAIN, 20));
        shuffle.setFont(new Font(skip_backward.getFont().getName(), Font.PLAIN, 15));
        repeat.setFont(new Font(skip_forward.getFont().getName(), Font.PLAIN, 15));
        skip_backward.setForeground(new Color(155, 155, 155));
        skip_forward.setForeground(new Color(155, 155, 155));
        repeat.setForeground(new Color(155, 155, 155));
        shuffle.setForeground(new Color(155, 155, 155));
        play.setAlignmentY(CENTER_ALIGNMENT);
        skip_backward.setAlignmentY(CENTER_ALIGNMENT);
        skip_forward.setAlignmentY(CENTER_ALIGNMENT);
        repeat.setAlignmentY(BOTTOM_ALIGNMENT);
        shuffle.setAlignmentY(BOTTOM_ALIGNMENT);
        skip_forward.addMouseListener(listenerForMouse);
        skip_backward.addMouseListener(listenerForMouse);
        play.addMouseListener(listenerForMouse);
        repeat.addMouseListener(listenerForMouse);
        shuffle.addMouseListener(listenerForMouse);
        skip_forward.setToolTipText("Next");
        skip_backward.setToolTipText("Previous");
        repeat.setToolTipText("Repeat");
        shuffle.setToolTipText("Shuffle");
        play.setToolTipText("Play");
        lightSliderUI = new LightSliderUI(musicSlider){
            @Override
            protected Color getFocusColor()
            {
                return new Color(40,40,40);
            }
        };
        musicSlider.setUI(lightSliderUI);
        musicSlider.setBackground(new Color(40, 40, 40));
        musicSlider.setMinimum(0);
        musicSlider.setMaximum(100);
        musicSlider.setValue(0);
        musicSlider.setEnabled(true);
        shuffleState = false;
        repeatState = 0;
        playState = 0;
        setVisible(true);
    }

    public void update() {
        this.getLayout().removeLayoutComponent(this);
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap((getWidth() / 2) - 115, (getWidth() / 2) - 115)
                        .addComponent(shuffle, 25, 25, 25)
                        .addGap(23, 23, 23)
                        .addComponent(skip_backward, 25, 25, 25)
                        .addGap(20, 20, 20)
                        .addComponent(play, 35, 35, 35)
                        .addGap(25, 25, 25)
                        .addComponent(skip_forward, 25, 25, 25)
                        .addGap(23, 23, 23)
                        .addComponent(repeat, 25, 25, 25))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap((getWidth() / 2) - 255, (getWidth() / 2) - 255)
                        .addComponent(musicSlider, 500, 500, 500))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(10, 10))
                .addComponent(songPanelForPlayPanel, 350, 350, 350));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(5, 15, 15)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(play, 35, 35, 35)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup()
                                            .addComponent(skip_backward, 25, 25, 25)
                                            .addComponent(skip_forward, 25, 25, 25)))
                                .addGroup(layout.createSequentialGroup().addGap(7, 7, 7)
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(shuffle, 25, 25, 25)
                                                .addComponent(repeat, 25, 25, 25)))
                                .addComponent(songPanelForPlayPanel, 70, 70, 70)))
                .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(musicSlider, 20, 20, 20)
                        .addContainerGap(30, 30)));
        this.setLayout(layout);
    }

    @Override
    public void rest()
    {
        SongsPanel.customLabelForSongsPanel.setText("PLAY");
        play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
        mainPanel.getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().repaint();
        playState = 0;
    }

    private class ListenerForMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == shuffle) {
                try
                {
                    playPanelListener.state(shuffleState, repeatState, playState, 0);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                if (!shuffleState) {
                    shuffle.setForeground(new Color(1, 180, 53));
                    shuffleState = true;
                } else {
                    shuffle.setForeground(new Color(255, 255, 255));
                    shuffleState = false;
                }

            }
            if (e.getSource() == skip_backward) {
                try
                {
                    playPanelListener.state(shuffleState, repeatState, playState, 1);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                skip_backward.setForeground(new Color(255, 255, 255));
                try {
//                    player.stop();
//                    player.interrupt();
                } catch (Exception ex) {

                } finally {
                    if (playState != 2) {
                        play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                        SongsPanel.customLabelForSongsPanel.setText("PAUSE");
                        mainPanel.getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().repaint();
                    }

                }
                playState = 2;
            }
            if (e.getSource() == play) {
                try
                {
                    playPanelListener.state(shuffleState, repeatState, playState, 2);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                if (playState != 2) {
                    SongsPanel.customLabelForSongsPanel.setText("PAUSE");
                    play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                    mainPanel.getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().repaint();
                    playState = 2;
                } else {
                    SongsPanel.customLabelForSongsPanel.setText("PLAY");
                    play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                    mainPanel.getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().repaint();
                    playState = 1;
                }

            }
            if (e.getSource() == skip_forward) {
                try
                {
                    playPanelListener.state(shuffleState, repeatState, playState, 3);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                skip_forward.setForeground(new Color(255, 255, 255));
                try {
//                    player.stop();
//                    player.interrupt();
                } catch (Exception ex) {

                } finally {
                    if (playState != 2) {
                        SongsPanel.customLabelForSongsPanel.setText("PAUSE");
                        mainPanel.getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().repaint();
                        play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                    }
                }
                playState = 2;
            }
            if (e.getSource() == repeat) {
                try
                {
                    playPanelListener.state(shuffleState, repeatState, playState, 4);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                if (repeatState == 0) {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(1, 180, 53));
                    repeatState = 1;
                } else if (repeatState == 1) {
                    repeat.setText("\uD83D\uDD02");
                    repeatState = 2;
                    repeat.setForeground(new Color(1, 180, 49));
                } else if (repeatState == 2) {
                    repeat.setText("\uD83D\uDD01");
                    repeatState = 0;
                    repeat.setForeground(new Color(255, 255, 255));
                }
            }
        }


        @Override
        public void mousePressed(MouseEvent e) {
            mouseActions(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseActions(e);
        }

        private void mouseActions(MouseEvent e) {
            refreshIcons(e);
            if (e.getSource() == repeat) {
                if (e.getSource() == repeat) {
                    if (repeatState == 0) {
                        repeat.setText("\uD83D\uDD01");
                        repeat.setForeground(new Color(155, 155, 155));
                    }
                    if (repeatState == 1) {
                        repeat.setText("\uD83D\uDD01");
                        repeat.setForeground(new Color(1, 155, 49));
                    }
                    if (repeatState == 2) {
                        repeat.setText("\uD83D\uDD02");
                        repeat.setForeground(new Color(1, 155, 49));
                    }
                }
            }
        }

        private void refreshIcons(MouseEvent e) {
            if (e.getSource() == shuffle) {
                if (!shuffleState) {
                    shuffle.setForeground(new Color(155, 155, 155));
                } else {
                    shuffle.setForeground(new Color(1, 155, 49));
                }
            }
            if (e.getSource() == skip_backward) {
                skip_backward.setForeground(new Color(155, 155, 155));
            }
            if (e.getSource() == play) {
                if (playState != 2)
                    play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                else
                    play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
            }
            if (e.getSource() == skip_forward) {
                skip_forward.setForeground(new Color(155, 155, 155));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == shuffle) {
                if (!shuffleState) {
                    shuffle.setForeground(new Color(255, 255, 255));
                } else {
                    shuffle.setForeground(new Color(1, 180, 50));
                }
            }
            if (e.getSource() == skip_backward) {
                skip_backward.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == play) {
                if (playState != 2)
                    play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 37, 37));
                else
                    play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 37, 37));
            }
            if (e.getSource() == skip_forward) {
                skip_forward.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == repeat) {
                if (repeatState == 0) {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(255, 255, 255));
                }
                if (repeatState == 1) {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(1, 180, 55));
                }
                if (repeatState == 2) {
                    repeat.setText("\uD83D\uDD02");
                    repeat.setForeground(new Color(1, 180, 56));
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            refreshIcons(e);
            if (e.getSource() == repeat) {
                if (repeatState == 0) {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(155, 155, 155));
                }
                if (repeatState == 1) {
                    repeat.setText("\uD83D\uDD01");
                    repeat.setForeground(new Color(1, 155, 49));
                }
                if (repeatState == 2) {
                    repeat.setText("\uD83D\uDD02");
                    repeat.setForeground(new Color(1, 155, 49));
                }
            }
        }
    }

    public JSlider getMusicSlider()
    {
        return musicSlider;
    }

    public LightSliderUI getLightSliderUI()
    {
        return lightSliderUI;
    }

    public SongPanelForPlayPanel getSongPanelForPlayPanel() {
        return songPanelForPlayPanel;
    }
}
