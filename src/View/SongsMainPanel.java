package View;

import javax.swing.*;
import java.awt.*;

public class SongsMainPanel extends JPanel {
    private GroupLayout layout;
    private SongsTablePanel songsTablePanel;
    private SongsPanel songsPanel;
    private FilterPanel filterPanel;

    SongsMainPanel() {
        super();
        songsPanel = new SongsPanel();
        songsTablePanel = new SongsTablePanel();
        filterPanel = new FilterPanel(1066, 30, songsTablePanel.getSongsTable());
        layout = new GroupLayout(this);
        setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(songsTablePanel, 500, 1066, 1066)
                .addComponent(filterPanel, 500, 1066, 1066)
                .addComponent(songsPanel, 500, 1066, 1066));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(songsPanel, 80, 80, 80)
                .addComponent(filterPanel, 40, 40, 40)
                .addComponent(songsTablePanel, 200, 596, 596));
        this.setLayout(layout);
    }

    public SongsTablePanel getSongsTablePanel()
    {
        return songsTablePanel;
    }

    public SongsPanel getSongsPanel()
    {
        return songsPanel;
    }
}
