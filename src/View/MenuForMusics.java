package View;

import Listeners.AddLibrariesListener;
import Listeners.AddSongToLibrary;
import Model.Library;
import Model.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class MenuForMusics extends JPopupMenu implements AddLibrariesListener
{
    private JMenu addToPlaylist = new JMenu("Add to playlist");
    private AddSongToLibrary addSongToLibrary = null;
    private Music music;
    public MenuForMusics()
    {
        this.add(addToPlaylist);
        this.setBackground(new Color(24, 24, 24));
        this.setForeground(Color.white);
        this.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setPreferredSize(new Dimension(200, 40));
        this.setMaximumSize(new Dimension(200, 40));
        this.setMinimumSize(new Dimension(200, 40));
//        ListenerForMouse listenerForMouse = new ListenerForMouse();
//        addToPlaylist.addMouseListener(listenerForMouse);
    }

    @Override
    public void addLibraries(Vector<Library> libraries)
    {
        addToPlaylist.removeAll();
        addToPlaylist.setForeground(Color.white);
        addToPlaylist.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        addToPlaylist.setBorder(BorderFactory.createEmptyBorder());
        addToPlaylist.setPreferredSize(new Dimension(200, 40));
        addToPlaylist.setMaximumSize(new Dimension(200, 40));
        addToPlaylist.setMinimumSize(new Dimension(200, 40));
        int counter = 0;
        for(Library library:libraries)
        {
            JSeparator separator = new JSeparator();
            JMenuItem jMenuItem = new JMenuItem(library.getName());
            jMenuItem.setBackground(new Color(24, 24, 24));
            jMenuItem.setForeground(new Color(120, 120, 120));
            jMenuItem.setBorder(BorderFactory.createEmptyBorder());
            jMenuItem.setPreferredSize(new Dimension(200, 40));
            jMenuItem.setMaximumSize(new Dimension(200, 40));
            jMenuItem.setForeground(Color.white);
            jMenuItem.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            jMenuItem.setMinimumSize(new Dimension(200, 40));
            addToPlaylist.add(jMenuItem);
            separator.setBackground(new Color(80, 80, 80));
            separator.setForeground(new Color(80, 80, 80));
            if (counter != (libraries.size() - 1))
                addToPlaylist.add(separator);
            jMenuItem.addMouseListener(new ListenerForMouse(jMenuItem));
            counter++;
        }
        addToPlaylist.getPopupMenu().setBorder(BorderFactory.createEmptyBorder());
    }

    private class ListenerForMouse implements MouseListener
    {
        JMenuItem jMenuItem;

        public ListenerForMouse(JMenuItem jMenuItem)
        {
            this.jMenuItem = jMenuItem;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            menuActions(e);
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            menuActions(e);
        }

        private void menuActions(MouseEvent e)
        {
            addSongToLibrary.addSongToLib(music,jMenuItem.getText());
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
    public void setAddSongToLibrary(AddSongToLibrary addSongToLibrary)
    {
        this.addSongToLibrary = addSongToLibrary;
    }

    public void setMusic(Music music)
    {
        this.music = music;
    }
}

/*

import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JPopupMenu;
        import javax.swing.JToggleButton;
        import javax.swing.event.PopupMenuEvent;
        import javax.swing.event.PopupMenuListener;

public class MenuButton extends JToggleButton {

    JPopupMenu popup;

    public MenuButton(String name, JPopupMenu menu) {
        super(name);
        this.popup = menu;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JToggleButton b = MenuButton.this;
                if (b.isSelected()) {
                    popup.show(b, 0, b.getBounds().height);
                } else {
                    popup.setVisible(false);
                }
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                MenuButton.this.setSelected(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }
}
*/