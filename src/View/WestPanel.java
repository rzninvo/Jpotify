package View;

import Controller.Main;
import Listeners.AddNewPlaylistListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WestPanel extends JPanel {
    private TransparentButton home;
    private TransparentButton browse;
    private TransparentButton radio;
    private TransparentButton addNewPlaylist;
    private MenuForWestPanel menuForWestPanel;
    private ViewPortPanel viewPortPanel;
    private ModernScrollPane scrollPaneForWestPanel;
    private JSeparator newPlaylistSeparator;
    private GroupLayout layout;
    private AddNewPlaylistListener addNewPlaylistListener = null;
    private PlayListPanel playListPanel;
    public static DefaultListModel<String> defaultListModel;
    private  MainPanel mainPanel;
    WestPanel(MainPanel mainPanel) {
        super();
        this.mainPanel = mainPanel;
        newPlaylistSeparator = new JSeparator();
        newPlaylistSeparator.setBackground(new Color(40, 40, 40));
        newPlaylistSeparator.setForeground(new Color(40, 40, 40));
        newPlaylistSeparator.setSize(215, 1);
        home = new TransparentButton("   Home", Icons.rescaleIcon(Icons.HOME_ICON, 23, 23), true);
        browse = new TransparentButton("   Browse", Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23), true);
        radio = new TransparentButton("   Radio", Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23), true);
        addNewPlaylist = new TransparentButton("  New Playlist", Icons.rescaleIcon(Icons.ADD_ICON, 28, 28), false);
        home.setSize(215, 23);
        browse.setSize(215, 23);
        radio.setSize(215, 23);
        addNewPlaylist.setSize(215, 40);
        home.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        browse.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        radio.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
        addNewPlaylist.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        home.setHorizontalAlignment(SwingConstants.LEFT);
        browse.setHorizontalAlignment(SwingConstants.LEFT);
        radio.setHorizontalAlignment(SwingConstants.LEFT);
        addNewPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
        addNewPlaylist.setVerticalAlignment(SwingConstants.CENTER);
        home.setForeground(new Color(180, 180, 180));
        browse.setForeground(new Color(180, 180, 180));
        radio.setForeground(new Color(180, 180, 180));
        addNewPlaylist.setForeground(new Color(180, 180, 180));
        ListenerForMouse listenerForMouse = new ListenerForMouse();
        home.addMouseListener(listenerForMouse);
        browse.addMouseListener(listenerForMouse);
        radio.addMouseListener(listenerForMouse);
        addNewPlaylist.addMouseListener(listenerForMouse);
        menuForWestPanel = new MenuForWestPanel();
        defaultListModel = new DefaultListModel<String>();
        defaultListModel.addElement("YOUR LIBRARY");
        defaultListModel.addElement("Made For You");
        defaultListModel.addElement("Songs");
        defaultListModel.addElement("Albums");
        defaultListModel.addElement("Artists");
        defaultListModel.addElement("Favourite Songs");
        defaultListModel.addElement("Shared Songs");
        defaultListModel.addElement("PLAYLISTS");
        for (int i = 0; i < Main.getPlayLists().size(); i++)
        {
            String name = Main.getPlayLists().get(i).getName();
            if ((!name.equals("Shared playlist")) && (!name.equals("Favourites")))
                defaultListModel.addElement(name);
        }
        playListPanel = new PlayListPanel(defaultListModel, this, mainPanel.getCentrePanel());
        layout = new GroupLayout(this);
        this.setLayout(layout);
        setBackground(new Color(18, 18, 18));
    }

    public void update()
    {
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(menuForWestPanel, 35, 35, 35)
                                .addComponent(home, 215, 215, 215)
                                .addComponent(browse, 215, 215, 215)
                                .addComponent(radio, 215, 215, 215))
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(playListPanel, 215, 215, 215))
                .addComponent(newPlaylistSeparator, 215, 215, 215)
                .addComponent(addNewPlaylist, 215, 215, 215));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menuForWestPanel, 35, 35, 35)
                .addGap(25, 25, 25)
                .addComponent(home, 25, 25, 25)
                .addGap(20, 20, 20)
                .addComponent(browse, 25, 25, 25)
                .addGap(20, 20, 20)
                .addComponent(radio, 25, 25, 25)
                .addGap(30, 30, 30)
                .addComponent(playListPanel, 0, 470, 470)
                .addComponent(newPlaylistSeparator, 1, 1, 1)
                .addGap(10, 10, 10)
                .addComponent(addNewPlaylist, 40, 40, 40)
                .addContainerGap(15, 15));
    }

    public void loseFocus() {
        home.setForeground(new Color(180, 180, 180));
        home.setIcon(Icons.rescaleIcon(Icons.HOME_ICON, 23, 23));
        browse.setForeground(new Color(180, 180, 180));
        browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23));
        radio.setForeground(new Color(180, 180, 180));
        radio.setIcon(Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23));
        home.setFocused(false);
        browse.setFocused(false);
        radio.setFocused(false);
        home.repaint();
        browse.repaint();
        radio.repaint();
    }

    private class ListenerForMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == home) {
                home.setForeground(new Color(255, 255, 255));
                home.setIcon(Icons.rescaleIcon(Icons.HOME_FOCUSED_ICON, 23, 23));
                browse.setForeground(new Color(180, 180, 180));
                browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23));
                radio.setForeground(new Color(180, 180, 180));
                radio.setIcon(Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23));
                home.setFocused(true);
                browse.setFocused(false);
                radio.setFocused(false);
                home.repaint();
                browse.repaint();
                radio.repaint();
                playListPanel.getList().setSelectedIndex(0);
                PlayListCellRendererForWestPanel.previousSelectedIndex = -1;
            }
            if (e.getSource() == browse) {
                browse.setForeground(new Color(255, 255, 255));
                browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_FOCUSED_ICON, 23, 23));
                home.setForeground(new Color(180, 180, 180));
                home.setIcon(Icons.rescaleIcon(Icons.HOME_ICON, 23, 23));
                radio.setForeground(new Color(180, 180, 180));
                radio.setIcon(Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23));
                browse.setFocused(true);
                home.setFocused(false);
                radio.setFocused(false);
                home.repaint();
                browse.repaint();
                radio.repaint();
                playListPanel.getList().setSelectedIndex(0);
                PlayListCellRendererForWestPanel.previousSelectedIndex = -1;
            }
            if (e.getSource() == radio) {
                radio.setForeground(new Color(255, 255, 255));
                radio.setIcon(Icons.rescaleIcon(Icons.RADIO_FOCUSED_ICON, 23, 23));
                browse.setForeground(new Color(180, 180, 180));
                browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23));
                home.setForeground(new Color(180, 180, 180));
                home.setIcon(Icons.rescaleIcon(Icons.HOME_ICON, 23, 23));
                radio.setFocused(true);
                browse.setFocused(false);
                home.setFocused(false);
                home.repaint();
                browse.repaint();
                radio.repaint();
                playListPanel.getList().setSelectedIndex(0);
                PlayListCellRendererForWestPanel.previousSelectedIndex = -1;
            }
            if (e.getSource() == addNewPlaylist) {
                addNewPlaylistListener.newPlaylist();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == home) {
                home.setForeground(new Color(145, 145, 145));
                home.setIcon(Icons.rescaleIcon(Icons.HOME_FOCUSED_ICON, 25, 25));
            }
            if (e.getSource() == browse) {
                browse.setForeground(new Color(145, 145, 145));
                browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_FOCUSED_ICON, 25, 25));
            }
            if (e.getSource() == radio) {
                radio.setForeground(new Color(145, 145, 145));
                radio.setIcon(Icons.rescaleIcon(Icons.RADIO_FOCUSED_ICON, 25, 25));
            }
            if (e.getSource() == addNewPlaylist) {
                addNewPlaylist.setForeground(new Color(145, 145, 145));
                addNewPlaylist.setIcon(Icons.rescaleIcon(Icons.ADD_ICON, 30, 30));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == home) {
                home.setForeground(new Color(255, 255, 255));
                home.setIcon(Icons.rescaleIcon(Icons.HOME_FOCUSED_ICON, 25, 25));
            }
            if (e.getSource() == browse) {
                browse.setForeground(new Color(255, 255, 255));
                browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_FOCUSED_ICON, 25, 25));
            }
            if (e.getSource() == radio) {
                radio.setForeground(new Color(255, 255, 255));
                radio.setIcon(Icons.rescaleIcon(Icons.RADIO_FOCUSED_ICON, 25, 25));
            }
            if (e.getSource() == addNewPlaylist) {
                addNewPlaylist.setForeground(new Color(255, 255, 255));
                addNewPlaylist.setIcon(Icons.rescaleIcon(Icons.ADD_ICON, 30, 30));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == home) {
                if (!home.getFocused()) {
                    home.setForeground(new Color(255, 255, 255));
                    home.setIcon(Icons.rescaleIcon(Icons.HOME_ICON, 25, 25));
                } else {
                    home.setForeground(new Color(255, 255, 255));
                    home.setIcon(Icons.rescaleIcon(Icons.HOME_FOCUSED_ICON, 25, 25));
                }
            }
            if (e.getSource() == browse) {
                if (!browse.getFocused()) {
                    browse.setForeground(new Color(255, 255, 255));
                    browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_ICON, 25, 25));
                } else {
                    browse.setForeground(new Color(255, 255, 255));
                    browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_FOCUSED_ICON, 25, 25));
                }
            }
            if (e.getSource() == radio) {
                if (!radio.getFocused()) {
                    radio.setForeground(new Color(255, 255, 255));
                    radio.setIcon(Icons.rescaleIcon(Icons.RADIO_ICON, 25, 25));
                } else {
                    radio.setForeground(new Color(255, 255, 255));
                    radio.setIcon(Icons.rescaleIcon(Icons.RADIO_FOCUSED_ICON, 25, 25));
                }
            }
            if (e.getSource() == addNewPlaylist) {
                addNewPlaylist.setForeground(new Color(255, 255, 255));
                addNewPlaylist.setIcon(Icons.rescaleIcon(Icons.ADD_ICON, 30, 30));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == home) {
                if (!home.getFocused()) {
                    home.setForeground(new Color(180, 180, 180));
                    home.setIcon(Icons.rescaleIcon(Icons.HOME_ICON, 23, 23));
                } else {
                    home.setForeground(new Color(255, 255, 255));
                    home.setIcon(Icons.rescaleIcon(Icons.HOME_FOCUSED_ICON, 23, 23));
                }
            }
            if (e.getSource() == browse) {
                if (!browse.getFocused()) {
                    browse.setForeground(new Color(180, 180, 180));
                    browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_ICON, 23, 23));
                } else {
                    browse.setForeground(new Color(255, 255, 255));
                    browse.setIcon(Icons.rescaleIcon(Icons.BROWSE_FOCUSED_ICON, 23, 23));
                }
            }
            if (e.getSource() == radio) {
                if (!radio.getFocused()) {
                    radio.setForeground(new Color(180, 180, 180));
                    radio.setIcon(Icons.rescaleIcon(Icons.RADIO_ICON, 23, 23));
                } else {
                    radio.setForeground(new Color(255, 255, 255));
                    radio.setIcon(Icons.rescaleIcon(Icons.RADIO_FOCUSED_ICON, 23, 23));
                }
            }
            if (e.getSource() == addNewPlaylist) {
                addNewPlaylist.setForeground(new Color(180, 180, 180));
                addNewPlaylist.setIcon(Icons.rescaleIcon(Icons.ADD_ICON, 28, 28));
            }
        }
    }

    public MenuForWestPanel getMenuForWestPanel()
    {
        return menuForWestPanel;
    }

    public void setAddNewPlaylistListener(AddNewPlaylistListener addNewPlaylistListener)
    {
        this.addNewPlaylistListener = addNewPlaylistListener;
    }

    public PlayListPanel getPlayListPanel() {
        return playListPanel;
    }

    public DefaultListModel<String> getDefaultListModel() {
        return defaultListModel;
    }
}
