package View;

import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.Constructor;

public class MainPanel extends JPanel {
    private WestPanel westPanel;
    private CentrePanel centrePanel;
    private FriendsPanel friendsPanel;
    private PlayPanel playPanel;
    private int prevWidth, prevHeight;
    private boolean firstApearence = true;

    MainPanel(int width, int height) {
        super();
        firstApearence = true;
        prevHeight = height;
        prevWidth = width;
        setSize(width, height);
        centrePanel = new CentrePanel(getWidth(), getHeight(), this);
        westPanel = new WestPanel(this);
        centrePanel.update();
        playPanel = new PlayPanel(this, getWidth(), Main.playlist, 100);
        this.setBackground(new Color(24, 24, 24));
        friendsPanel = new FriendsPanel(getHeight());
    }

    public void update() {
        if ((getWidth() != prevWidth || getHeight() != prevHeight) || firstApearence == true) {
            prevWidth = getWidth();
            prevHeight = getHeight();
            if (centrePanel == null) {
                centrePanel = new CentrePanel(getWidth(), getHeight(), this);
                centrePanel.update();
            }
            if (firstApearence) {
                playPanel.update();
                westPanel.update();
            }
            this.getLayout().removeLayoutComponent(this);
            this.setLayout(null);
            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            if (getWidth() >= 1070) {
                layout.setHorizontalGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(westPanel, 215, 215, Short.MAX_VALUE)
                                .addComponent(centrePanel, 600, 1450, Short.MAX_VALUE)
                                .addComponent(friendsPanel, 255, 255, Short.MAX_VALUE))
                        .addComponent(playPanel, 940, 1920, Short.MAX_VALUE));
                layout.setVerticalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(westPanel, 500, 952, Short.MAX_VALUE)
                                .addComponent(centrePanel, 500, 952, Short.MAX_VALUE)
                                .addComponent(friendsPanel, 500, 952, Short.MAX_VALUE))
                        .addComponent(playPanel, 72, 88, Short.MAX_VALUE));
            } else {
                layout.setHorizontalGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(westPanel, 215, 215, Short.MAX_VALUE)
                                .addComponent(centrePanel, 600, 1450, Short.MAX_VALUE))
                        .addComponent(playPanel, 940, 1920, Short.MAX_VALUE));
                layout.setVerticalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(westPanel, 500, 952, Short.MAX_VALUE)
                                .addComponent(centrePanel, 500, 952, Short.MAX_VALUE))
                        .addComponent(playPanel, 72, 88, Short.MAX_VALUE));
            }
            if (!firstApearence) {
                centrePanel.setFrameHeight(getHeight());
                centrePanel.setFrameWidth(getWidth());
                centrePanel.update();
                playPanel.setSize(getWidth(), playPanel.getHeight());
                playPanel.update();
                westPanel.update();
            }
            firstApearence = false;
        }
    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    public WestPanel getWestPanel() {
        return westPanel;
    }

    public CentrePanel getCentrePanel() {
        return centrePanel;
    }

    public FriendsPanel getFriendsPanel()
    {
        return friendsPanel;
    }
}
