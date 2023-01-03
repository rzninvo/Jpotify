package View;

import Listeners.TopLeftMenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MenuForWestPanel extends JMenuBar
{
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenu help = new JMenu("help");
    private JMenu playback = new JMenu("Playback");
    private JMenu menu = new JMenu("");
    private JMenuItem newSong = new JMenuItem("New Song");
    private JMenuItem newPlayList = new JMenuItem("New Playlist");
    private JMenuItem newFriend = new JMenuItem("Add New Friend");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem search = new JMenuItem("Search");
    private JMenuItem play = new JMenuItem("Play");
    private JMenuItem next = new JMenuItem("Next");
    private JMenuItem previous = new JMenuItem("Previous");
    private JMenuItem seekForward = new JMenuItem("SeekForward");
    private JMenuItem seekBackward = new JMenuItem("SeekBackward");
    private JMenuItem shuffle = new JMenuItem("Shuffle");
    private JMenuItem repeat = new JMenuItem("Repeat");
    private JMenuItem volumeUp = new JMenuItem("Volume Up");
    private JMenuItem volumeDown = new JMenuItem("Volume Down");
    private JMenuItem jpotifyHelp = new JMenuItem("JPotify Help");
    private JMenuItem aboutJPotify = new JMenuItem("About JPotify");
    private TopLeftMenuListener topLeftMenuListener;
    MenuForWestPanel()
    {
        super();
        this.file.setMnemonic(KeyEvent.VK_F);
        this.file.add(newSong);
        this.file.add(newPlayList);
        this.file.add(newFriend);
        this.file.addSeparator();
        this.file.add(exit);
        this.newSong.setMnemonic(KeyEvent.VK_S);
        this.newPlayList.setMnemonic(KeyEvent.VK_P);
        this.exit.setMnemonic(KeyEvent.VK_E);
        this.edit.setMnemonic(KeyEvent.VK_E);
        this.search.setMnemonic(KeyEvent.VK_S);
        this.edit.add(search);
        this.help.setMnemonic(KeyEvent.VK_H);
        this.jpotifyHelp.setMnemonic(KeyEvent.VK_J);
        this.aboutJPotify.setMnemonic(KeyEvent.VK_A);
        this.help.add(jpotifyHelp);
        this.help.addSeparator();
        this.help.add(aboutJPotify);
        this.playback.setMnemonic(KeyEvent.VK_P);
        this.play.setMnemonic(KeyEvent.VK_P);
        this.next.setMnemonic(KeyEvent.VK_N);
        this.shuffle.setMnemonic(KeyEvent.VK_S);
        this.repeat.setMnemonic(KeyEvent.VK_R);
        this.playback.add(play);
        this.playback.addSeparator();
        this.playback.add(next);
        this.playback.add(previous);
        this.playback.add(seekForward);
        this.playback.add(seekBackward);
        this.playback.addSeparator();
        this.playback.add(shuffle);
        this.playback.add(repeat);
        this.playback.addSeparator();
        this.playback.add(volumeUp);
        this.playback.add(volumeDown);
        this.menu.add(file);
        this.menu.add(edit);
        this.menu.add(playback);
        this.menu.add(help);
        this.setBackground(new Color(18, 18, 18));
        this.setForeground(new Color(18, 18, 18));
        this.setSize(40, 40);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.menu.setIcon(Icons.rescaleIcon(Icons.MENU_ICON, 35, 35));
        this.menu.setSize(35, 50);
        this.add(menu);
        ListenerForMouse listenerForMouse = new ListenerForMouse();
        exit.addMouseListener(listenerForMouse);
        newSong.addMouseListener(listenerForMouse);
        newFriend.addMouseListener(listenerForMouse);
        newPlayList.addMouseListener(listenerForMouse);
    }

    public void setTopLeftMenuListener(TopLeftMenuListener topLeftMenuListener)
    {
        this.topLeftMenuListener = topLeftMenuListener;
    }

    private class ListenerForMouse implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            try
            {
                menuActions(e);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            try
            {
                menuActions(e);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        private void menuActions(MouseEvent e) throws IOException
        {
            if (e.getSource() == exit)
                topLeftMenuListener.state(0);
            else if (e.getSource() == newSong)
                topLeftMenuListener.state(1);
            else if(e.getSource() == newFriend)
                topLeftMenuListener.state(2);
            else if (e.getSource() == newPlayList)
                topLeftMenuListener.state(3);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
    }
}
