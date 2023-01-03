package View;

import Controller.LoadingLibrary;
import Controller.Main;
import Listeners.UserLoginListener;
import Model.Albums;
import Model.Library;
import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

public class CentrePanel extends JPanel implements UserLoginListener {
    private CustomTextField search = new CustomTextField(175, 24, Icons.rescaleIcon(Icons.SEARCH2_ICON, 15, 15)
            , Icons.rescaleIcon(Icons.CLOSE2_ICON, 10, 10));

    private SongsMainPanel songsMainPanel;
    private AlbumsMainPanel albumsMainPanel;
    private ListenerForMouse listenerForMouse;
    private int frameWidth, frameHeight;
    static int state = 0;
    private JLabel previous = new JLabel("‹");
    private JLabel next = new JLabel("›");
    private JLabel userPic = new JLabel(Icons.rescaleIcon(Icons.USER4_ICON, 25, 25));
    private JLabel userMenu = new JLabel("⌵") {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(40, 40, 40));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(-1 * super.getWidth() / 2 - 3, super.getHeight() + 10);
        }
    };
    private TransparentButton close = new TransparentButton("✕", false);
    private TransparentButton restoreDown = new TransparentButton("◻", false);
    private TransparentButton minimize = new TransparentButton("⚊", false);

    @Override
    public void setUser(String user) {
        userName.setText(user);
    }

    private class CustomLabelForCentrePanel extends JLabel {
        private boolean focused;
        private Color color;

        CustomLabelForCentrePanel(String text) {
            super(text);
            this.focused = false;
            this.color = new Color(255, 255, 255);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gd = (Graphics2D) g.create();
            if (focused) {
                gd.setColor(color);
                StringMetrics s = new StringMetrics(gd);
                gd.drawLine(0, (int) s.getHeight(this.getText()) + 5, (int) s.getWidth(this.getText()), (int) s.getHeight(this.getText()) + 5);
            }
            gd.dispose();
        }

        public boolean isFocused() {
            return focused;
        }

        public void setFocused(boolean focused) {
            this.focused = focused;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    private CustomLabelForCentrePanel userName = new CustomLabelForCentrePanel("Roham") {
        @Override
        public JToolTip createToolTip() {
            JToolTip tip = super.createToolTip();
            tip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            tip.setBackground(new Color(40, 40, 40));
            tip.setForeground(new Color(180, 180, 180));
            tip.setBorder(null);
            return tip;
        }

        @Override
        public Point getToolTipLocation(MouseEvent event) {
            return new Point(-10, super.getHeight() + 10);
        }
    };

    CentrePanel(int width, int height, MainPanel mainPanel) {
        super();
        frameHeight = height;
        frameWidth = width;
        listenerForMouse = new ListenerForMouse();
        search.setBackground(new Color(24, 24, 24));
        search.textField.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        userPic.setAlignmentY(CENTER_ALIGNMENT);
        userName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 14));
        userName.addMouseListener(listenerForMouse);
        userName.setAlignmentY(BOTTOM_ALIGNMENT);
        userMenu.setFont(new Font("Sefir", Font.PLAIN, 24));
        userMenu.addMouseListener(listenerForMouse);
        close.addMouseListener(listenerForMouse);
        restoreDown.addMouseListener(listenerForMouse);
        minimize.addMouseListener(listenerForMouse);
        close.setBackground(new Color(24, 24, 24));
        restoreDown.setBackground(new Color(24, 24, 24));
        minimize.setBackground(new Color(24, 24, 24));
        close.setSize(45, 30);
        restoreDown.setSize(45, 30);
        minimize.setSize(45, 30);
        close.setHorizontalAlignment(SwingConstants.CENTER);
        restoreDown.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        close.setVerticalAlignment(SwingConstants.CENTER);
        restoreDown.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);
        close.setForeground(new Color(255, 255, 255));
        restoreDown.setForeground(new Color(255, 255, 255));
        minimize.setForeground(new Color(255, 255, 255));
        userMenu.setToolTipText("Menu");
        userName.setToolTipText("Profile");
        previous.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 45));
        next.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 45));
        userName.setForeground(new Color(255, 255, 255));
        userMenu.setForeground(new Color(180, 180, 180));
        previous.setForeground(new Color(155, 155, 155));
        next.setForeground(new Color(155, 155, 155));
        songsMainPanel = new SongsMainPanel();
        albumsMainPanel = new AlbumsMainPanel(this);
        setBackground(new Color(18, 18, 18));
        setVisible(true);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public void update() {
        this.getLayout().removeLayoutComponent(this);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        if (state == 0)
            remove(albumsMainPanel);
        else
            remove(songsMainPanel);
        if (frameWidth >= 1070) {
            if (minimize != null)
                remove(minimize);
            if (close != null)
                remove(close);
            if (restoreDown != null)
                remove(restoreDown);
            albumsMainPanel.repaint();
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap(20, 20)
                            .addComponent(previous, 20, 20, 20)
                            .addGap(11, 11, 11)
                            .addComponent(next, 20, 20, 20)
                            .addGap(17, 17, 17)
                            .addComponent(search, 175, 175, 175)
                            .addGap(100, 660, 660)
                            .addComponent(userPic, 25, 25, 25)
                            .addGap(10, 10, 10)
                            .addComponent(userName, 50, 50, 50)
                            .addGap(12, 12, 12)
                            .addComponent(userMenu, 20, 20, 20)
                            .addContainerGap(35, 35))
                    .addComponent((state == 0) ? songsMainPanel : albumsMainPanel, 500, 1066, 1066));

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addGroup(layout.createParallelGroup()
                                                    .addComponent(previous, 20, 20, 20)
                                                    .addComponent(next, 20, 20, 20)
                                                    .addComponent(search, 24, 24, 24)))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(layout.createParallelGroup()
                                                    .addComponent(userPic, 25, 25, 25)
                                                    .addComponent(userName, 25, 25, 25)
                                                    .addComponent(userMenu, 20, 20, 20))))
                            .addComponent((state == 0) ? songsMainPanel : albumsMainPanel, 300, 516, 716));
        } else {
            layout.setHorizontalGroup(layout.createParallelGroup().
                    addGroup(layout.createSequentialGroup()
                            .addContainerGap(20, 20)
                            .addComponent(previous, 20, 20, 20)
                            .addGap(11, 11, 11)
                            .addComponent(next, 20, 20, 20)
                            .addGap(17, 17, 17)
                            .addComponent(search, 175, 175, 175)
                            .addGap(50, 660, 660)
                            .addComponent(userPic, 25, 25, 25)
                            .addGap(11, 11, 11)
                            .addComponent(userName, 50, 50, 50)
                            .addGap(13, 13, 13)
                            .addComponent(userMenu, 20, 20, 20)
                            .addGap(20, 20, 20)
                            .addComponent(minimize, 45, 45, 45)
                            .addComponent(restoreDown, 45, 45, 45)
                            .addComponent(close, 45, 45, 45))
                    .addComponent((state == 0) ? songsMainPanel : albumsMainPanel, 737, 737, 1066));

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                    .addComponent(minimize, 30, 30, 30)
                                    .addComponent(restoreDown, 30, 30, 30)
                                    .addComponent(close, 30, 30, 30)
                                    .addGroup(layout.createParallelGroup()
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(13, 13, 13)
                                                    .addGroup(layout.createParallelGroup()
                                                            .addComponent(previous, 20, 20, 20)
                                                            .addComponent(next, 20, 20, 20)
                                                            .addComponent(search, 24, 24, 24)))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(10, 10, 10)
                                                    .addGroup(layout.createParallelGroup()
                                                            .addComponent(userPic, 25, 25, 25)
                                                            .addComponent(userName, 25, 25, 25)
                                                            .addComponent(userMenu, 20, 20, 20)))))
                            .addComponent((state == 0) ? songsMainPanel : albumsMainPanel, 200, 716, 716));
        }
        repaint();
    }

    public void updateTable(String playListName, Library album, boolean albumRequest) throws InvalidDataException, IOException, UnsupportedTagException {
        if (!albumRequest) {
            if (!playListName.equals(songsMainPanel.getSongsPanel().getLabelText())) {
                if ((!playListName.equals("Songs")) && (!playListName.equals("Albums")) && (!playListName.equals("Artists"))
                        && (!playListName.equals("YOUR LIBRARY")) && (!playListName.equals("PLAYLISTS")) && (!playListName.equals("Made For You"))
                        && (!playListName.equals("Shared Playlist")) && (!playListName.equals("Favourites"))) {
                    for (int i = 0; i < Main.playLists.size(); i++) {
                        if (playListName.equals(Main.playLists.get(i).getName())) {
                            getSongsMainPanel().getSongsTablePanel().addSongs(LoadingLibrary.generateTable(Main.playLists.get(i).getMusics()));
                            getSongsMainPanel().getSongsPanel().setLabelText(Main.playLists.get(i).getName());
                            break;
                        }
                    }
                    songsMainPanel.getSongsTablePanel().getSongsTable().updateTableModel();
                }
                if (playListName.equals("Songs")) {
                    getSongsMainPanel().getSongsTablePanel().addSongs(LoadingLibrary.generateTable(Main.musics));
                    songsMainPanel.getSongsTablePanel().getSongsTable().updateTableModel();
                    getSongsMainPanel().getSongsPanel().setLabelText("Songs");
                }
                if (playListName.equals("Shared Playlist")) {
                    getSongsMainPanel().getSongsTablePanel().addSongs(LoadingLibrary.generateTable(Main.sharedPlaylist.getMusics()));
                    songsMainPanel.getSongsTablePanel().getSongsTable().updateTableModel();
                    getSongsMainPanel().getSongsPanel().setLabelText("Shared Songs");
                }
                if (playListName.equals("Favourites")) {
                    getSongsMainPanel().getSongsTablePanel().addSongs(LoadingLibrary.generateTable(Main.favourites.getMusics()));
                    songsMainPanel.getSongsTablePanel().getSongsTable().updateTableModel();
                    getSongsMainPanel().getSongsPanel().setLabelText("Favourites");
                }
            }
        }
        else
        {
            getSongsMainPanel().getSongsTablePanel().addSongs(LoadingLibrary.generateTable(album.getMusics()));
            songsMainPanel.getSongsTablePanel().getSongsTable().updateTableModel();
            getSongsMainPanel().getSongsPanel().setLabelText(album.getName());
        }
    }

    private class ListenerForMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == close) {
                System.exit(0);
            }
            if (e.getSource() == restoreDown) {
                Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
                ((JFrame) (getParent().getParent().getParent().getParent().getParent())).setBounds(0, 0, (int) dimPant.getWidth(), (int) dimPant.getHeight() - 40);
                ((JFrame) (getParent().getParent().getParent().getParent().getParent())).setSize((int) dimPant.getWidth(), (int) dimPant.getHeight() - 40);
                ((MainFrame) (getParent().getParent().getParent().getParent().getParent())).setFullScreenMode(true);
                restoreDown.setBackground(new Color(24, 24, 24));

            }
            if (e.getSource() == minimize) {
                ((JFrame) (getParent().getParent().getParent().getParent().getParent())).setState(Frame.ICONIFIED);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == userName) {
                userName.setForeground(new Color(155, 155, 155));
                userName.setColor(new Color(155, 155, 155));
                userName.repaint();
            }
            if (e.getSource() == userMenu) {
                userMenu.setForeground(new Color(105, 105, 105));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == userName) {
                userName.setForeground(new Color(255, 255, 255));
                userName.setColor(new Color(255, 255, 255));
                userName.repaint();
            }
            if (e.getSource() == userMenu) {
                userMenu.setForeground(new Color(255, 255, 255));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == close) {
                close.setBackground(new Color(255, 22, 14));
            }
            if (e.getSource() == restoreDown) {
                restoreDown.setBackground(new Color(100, 100, 100));
            }
            if (e.getSource() == minimize) {
                minimize.setBackground(new Color(100, 100, 100));
            }
            if (e.getSource() == userName) {
                userName.setFocused(true);
                userName.repaint();
            }
            if (e.getSource() == userMenu) {
                userMenu.setForeground(new Color(255, 255, 255));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == close) {
                close.setBackground(new Color(24, 24, 24));
            }
            if (e.getSource() == restoreDown) {
                restoreDown.setBackground(new Color(24, 24, 24));
            }
            if (e.getSource() == minimize) {
                minimize.setBackground(new Color(24, 24, 24));
            }
            if (e.getSource() == userName) {
                userName.setFocused(false);
                userName.repaint();
            }
            if (e.getSource() == userMenu) {
                userMenu.setForeground(new Color(180, 180, 180));
            }
        }
    }

    public SongsMainPanel getSongsMainPanel() {
        return songsMainPanel;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
