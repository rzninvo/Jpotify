package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewPortPanel extends JPanel {

    private CustomLabel yourLibrary = new CustomLabel("     YOUR LIBRARY", false, true);
    private CustomLabel madeForYou = new CustomLabel("     Made For You", false, true);
    private CustomLabel recentlyPlayed = new CustomLabel("     Recently Played", false, true);
    private CustomLabel songs = new CustomLabel("     Songs", false, true);
    private CustomLabel albums = new CustomLabel("     Albums", false, true);
    private CustomLabel artists = new CustomLabel("     Artists", false, true);
    private CustomLabel playlists = new CustomLabel("     PLAYLISTS", false, true);

    ViewPortPanel() {
        super();
        this.setSize(320, 300);
        yourLibrary.setForeground(new Color(145, 145, 145));
        yourLibrary.setFont(new Font("Proxima Nova Alt Lt", Font.BOLD, 13));
        madeForYou.setForeground(new Color(180, 180, 180));
        madeForYou.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        recentlyPlayed.setForeground(new Color(180, 180, 180));
        recentlyPlayed.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        songs.setForeground(new Color(180, 180, 180));
        songs.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        albums.setForeground(new Color(180, 180, 180));
        albums.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        artists.setForeground(new Color(180, 180, 180));
        artists.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        playlists.setForeground(new Color(145, 145, 145));
        playlists.setFont(new Font("Proxima Nova Alt Lt", Font.BOLD, 13));
        ListenerForMouse listenerForMouse = new ListenerForMouse();
        madeForYou.addMouseListener(listenerForMouse);
        recentlyPlayed.addMouseListener(listenerForMouse);
        songs.addMouseListener(listenerForMouse);
        albums.addMouseListener(listenerForMouse);
        artists.addMouseListener(listenerForMouse);
        this.setBackground(new Color(18, 18, 18));
        this.setForeground(new Color(18, 18, 18));
        this.setBorder(BorderFactory.createEmptyBorder());
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        this.setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(yourLibrary, 0, 20, Short.MAX_VALUE)
                .addComponent(madeForYou, 0, 20, Short.MAX_VALUE)
                .addComponent(recentlyPlayed, 0, 20, Short.MAX_VALUE)
                .addComponent(songs, 0, 20, Short.MAX_VALUE)
                .addComponent(albums, 0, 20, Short.MAX_VALUE)
                .addComponent(artists, 0, 20, Short.MAX_VALUE)
                .addComponent(playlists, 0, 20, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                //.addGap(15, 15, 15)
                .addComponent(yourLibrary, 13, 13, 13)
                .addGap(15, 15, 15)
                .addComponent(madeForYou, 20, 20, 20)
                .addGap(15, 15, 15)
                .addComponent(recentlyPlayed, 20, 20, 20)
                .addGap(15, 15, 15)
                .addComponent(songs, 20, 20, 20)
                .addGap(15, 15, 15)
                .addComponent(albums, 20, 20, 20)
                .addGap(15, 15, 15)
                .addComponent(artists, 20, 20, 20)
                .addGap(30, 30, 30)
                .addComponent(playlists, 13, 13, 13));
    }
    public void loseFocus()
    {
        madeForYou.setFocused(false);
        recentlyPlayed.setFocused(false);
        songs.setFocused(false);
        albums.setFocused(false);
        artists.setFocused(false);
        madeForYou.setForeground(new Color(180, 180, 180));
        recentlyPlayed.setForeground(new Color(180, 180, 180));
        songs.setForeground(new Color(180, 180, 180));
        albums.setForeground(new Color(180, 180, 180));
        artists.setForeground(new Color(180, 180, 180));
        madeForYou.repaint();
        recentlyPlayed.repaint();
        songs.repaint();
        albums.repaint();
        artists.repaint();
        getParent().repaint();
    }

    private class ListenerForMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == madeForYou) {
                madeForYou.setForeground(new Color(255, 255, 255));
                madeForYou.setFocused(true);
                recentlyPlayed.setFocused(false);
                songs.setFocused(false);
                albums.setFocused(false);
                artists.setFocused(false);
                recentlyPlayed.setForeground(new Color(180, 180, 180));
                songs.setForeground(new Color(180, 180, 180));
                albums.setForeground(new Color(180, 180, 180));
                artists.setForeground(new Color(180, 180, 180));
                madeForYou.repaint();
                recentlyPlayed.repaint();
                songs.repaint();
                albums.repaint();
                artists.repaint();
            }
            if (e.getSource() == recentlyPlayed) {
                recentlyPlayed.setForeground(new Color(255, 255, 255));
                recentlyPlayed.setFocused(true);
                madeForYou.setFocused(false);
                songs.setFocused(false);
                albums.setFocused(false);
                artists.setFocused(false);
                madeForYou.setForeground(new Color(180, 180, 180));
                songs.setForeground(new Color(180, 180, 180));
                albums.setForeground(new Color(180, 180, 180));
                artists.setForeground(new Color(180, 180, 180));
                madeForYou.repaint();
                recentlyPlayed.repaint();
                songs.repaint();
                albums.repaint();
                artists.repaint();
            }
            if (e.getSource() == songs) {
                songs.setForeground(new Color(255, 255, 255));
                songs.setFocused(true);
                recentlyPlayed.setFocused(false);
                madeForYou.setFocused(false);
                albums.setFocused(false);
                artists.setFocused(false);
                recentlyPlayed.setForeground(new Color(180, 180, 180));
                madeForYou.setForeground(new Color(180, 180, 180));
                albums.setForeground(new Color(180, 180, 180));
                artists.setForeground(new Color(180, 180, 180));
                madeForYou.repaint();
                recentlyPlayed.repaint();
                songs.repaint();
                albums.repaint();
                artists.repaint();
            }
            if (e.getSource() == albums) {
                albums.setForeground(new Color(255, 255, 255));
                albums.setFocused(true);
                madeForYou.setFocused(false);
                recentlyPlayed.setFocused(false);
                songs.setFocused(false);
                artists.setFocused(false);
                recentlyPlayed.setForeground(new Color(180, 180, 180));
                songs.setForeground(new Color(180, 180, 180));
                madeForYou.setForeground(new Color(180, 180, 180));
                artists.setForeground(new Color(180, 180, 180));
                madeForYou.repaint();
                recentlyPlayed.repaint();
                songs.repaint();
                albums.repaint();
                artists.repaint();
            }
            if (e.getSource() == artists) {
                artists.setForeground(new Color(255, 255, 255));
                artists.setFocused(true);
                madeForYou.setFocused(false);
                songs.setFocused(false);
                albums.setFocused(false);
                recentlyPlayed.setFocused(false);
                recentlyPlayed.setForeground(new Color(180, 180, 180));
                songs.setForeground(new Color(180, 180, 180));
                albums.setForeground(new Color(180, 180, 180));
                madeForYou.setForeground(new Color(180, 180, 180));
                madeForYou.repaint();
                recentlyPlayed.repaint();
                songs.repaint();
                albums.repaint();
                artists.repaint();
            }
            ((WestPanel)getParent().getParent().getParent()).loseFocus();
            (getParent().getParent().getParent()).repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == madeForYou) {
                madeForYou.setForeground(new Color(145, 145, 145));
            }
            if (e.getSource() == recentlyPlayed) {
                recentlyPlayed.setForeground(new Color(145, 145, 145));
            }
            if (e.getSource() == songs) {
                songs.setForeground(new Color(145, 145, 145));
            }
            if (e.getSource() == albums) {
                albums.setForeground(new Color(145, 145, 145));
            }
            if (e.getSource() == artists) {
                artists.setForeground(new Color(145, 145, 145));
            }
            getParent().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == madeForYou) {
                madeForYou.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == recentlyPlayed) {
                recentlyPlayed.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == songs) {
                songs.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == albums) {
                albums.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == artists) {
                artists.setForeground(new Color(255, 255, 255));
            }
            getParent().repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == madeForYou) {
                madeForYou.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == recentlyPlayed) {
                recentlyPlayed.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == songs) {
                songs.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == albums) {
                albums.setForeground(new Color(255, 255, 255));
            }
            if (e.getSource() == artists) {
                artists.setForeground(new Color(255, 255, 255));
            }
            getParent().repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == madeForYou) {
                if (madeForYou.isFocused())
                    madeForYou.setForeground(new Color(255, 255, 255));
                else
                    madeForYou.setForeground(new Color(180, 180, 180));
            }
            if (e.getSource() == recentlyPlayed) {
                if (recentlyPlayed.isFocused())
                    recentlyPlayed.setForeground(new Color(255, 255, 255));
                else
                    recentlyPlayed.setForeground(new Color(180, 180, 180));
            }
            if (e.getSource() == songs) {
                if (songs.isFocused())
                    songs.setForeground(new Color(255, 255, 255));
                else
                    songs.setForeground(new Color(180, 180, 180));
            }
            if (e.getSource() == albums) {
                if (albums.isFocused())
                    albums.setForeground(new Color(255, 255, 255));
                else
                    albums.setForeground(new Color(180, 180, 180));
            }
            if (e.getSource() == artists) {
                if (artists.isFocused())
                    artists.setForeground(new Color(255, 255, 255));
                else
                    artists.setForeground(new Color(180, 180, 180));
            }
            getParent().repaint();
        }
    }
}
