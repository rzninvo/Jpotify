package View;

import javax.swing.*;
import java.awt.*;

public class AlbumsMainPanel extends JPanel {
    private GroupLayout layout;
    private AlbumPanel albumsPanel;
    private AlbumsTablePanel albumsTablePanel;

    AlbumsMainPanel(CentrePanel centrePanel) {
        super();
        albumsPanel = new AlbumPanel();
        albumsTablePanel = new AlbumsTablePanel(centrePanel);
        layout = new GroupLayout(this);
        setBackground(new Color(18, 18, 18));
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(30, 30)
                        .addComponent(albumsTablePanel, 500, 1066, 1066))
                .addGroup(layout.createSequentialGroup()
                        .addGap((MainFrame.width > 1200) ? 14 : 12, 14, 14)
                        .addComponent(albumsPanel, 500, 1066, 1066)
                        .addGap(17, 17, 17)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(albumsPanel, 120, 120, 120)
                .addComponent(albumsTablePanel, 200, 596, 596));
        this.setLayout(layout);
    }

    public AlbumsTablePanel getAlbumsTablePanel() {
        return albumsTablePanel;
    }

    public AlbumPanel getAlbumsPanel() {
        return albumsPanel;
    }
}
