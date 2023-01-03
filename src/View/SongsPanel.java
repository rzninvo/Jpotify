package View;

import Listeners.SongsPanelListener;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SongsPanel extends JPanel
{
    private JLabel songs = new JLabel("Songs");
    static CustomLabelForSongsPanel customLabelForSongsPanel;
    private GroupLayout layout;
    private SongsPanelListener songsPanelListener = null;
    SongsPanel()
    {
        super();
        songs.setForeground(Color.white);
        songs.setFont(new Font("Proxima Nova Rg", Font.BOLD, 30));
        customLabelForSongsPanel = new CustomLabelForSongsPanel("PLAY", 108, 32, new Color(29, 178, 73, 255));
        customLabelForSongsPanel.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        customLabelForSongsPanel.addMouseListener(new ListenerForSongsCustomLabel(customLabelForSongsPanel));
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(30, 30)
                .addComponent(songs, 200, 200, 200)
                .addGap(200, 690, 690)
                .addComponent(customLabelForSongsPanel, 108, 108, 108)
                .addContainerGap(10, 10));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(30, 30)
                .addGroup(layout.createParallelGroup()
                        .addComponent(songs, 30, 30, 30)
                        .addComponent(customLabelForSongsPanel, 32, 32, 32)));
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
    }

    public class ListenerForSongsCustomLabel extends MouseInputAdapter
    {
        private CustomLabelForSongsPanel customLabelForSongsPanel;

        ListenerForSongsCustomLabel(CustomLabelForSongsPanel label)
        {
            this.customLabelForSongsPanel = label;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            if (customLabelForSongsPanel.getText().equals("PLAY"))
            {
                customLabelForSongsPanel.setText("PAUSE");
                PlayPanel.playState = 2;
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                try
                {
                    songsPanelListener.playMusic(false, (String) SongsTablePanel.defaultTableModel.getValueAt(0,2), (String) SongsTablePanel.defaultTableModel.getValueAt(0,3));
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                //Pause the song
            }
            else
            {
                customLabelForSongsPanel.setText("PLAY");
                PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                PlayPanel.playState = 1;
                try
                {
                    songsPanelListener.playMusic(true, (String) SongsTablePanel.defaultTableModel.getValueAt(0,2), (String) SongsTablePanel.defaultTableModel.getValueAt(0,3));
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                catch (UnsupportedTagException ex)
                {
                    ex.printStackTrace();
                }
                catch (TagException ex)
                {
                    ex.printStackTrace();
                }
                catch (InvalidDataException ex)
                {
                    ex.printStackTrace();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                //Resume the song if any song is playing or play the first row
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            super.mouseEntered(e);
            customLabelForSongsPanel.setColor(new Color(29, 205, 75, 255));
            customLabelForSongsPanel.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            super.mouseExited(e);
            customLabelForSongsPanel.setColor(new Color(29, 178, 73, 255));
            customLabelForSongsPanel.repaint();
        }
    }

    public void setSongsPanelListener(SongsPanelListener songsPanelListener)
    {
        this.songsPanelListener = songsPanelListener;
    }

    public void setLabelText(String text)
    {
        songs.setText(text);
        repaint();
    }

    public String getLabelText()
    {
        return songs.getText();
    }
}
