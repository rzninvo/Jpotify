package View;

import Controller.Main;
import Model.FriendsActivity;
import Model.Music;
import Model.User;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class AlbumsTablePanel extends JPanel {
    private DefaultTableModel defaultTableModel;
    private ModernScrollPane modernScrollPane;
    private AlbumsTable albumsTable;
    private GroupLayout layout;
    private FriendsActivity[][] data;
    AlbumsTablePanel(CentrePanel centrePanel)
    {
        super();
        String columns[] = {"","","", ""};
        addAlbums();
        defaultTableModel = new DefaultTableModel(data,columns);
        albumsTable = new AlbumsTable(defaultTableModel, centrePanel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        albumsTable.setBorder(null);
        modernScrollPane = new ModernScrollPane(albumsTable, new Color(18, 18, 18));
        modernScrollPane.setBorder(BorderFactory.createEmptyBorder());
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(32, 32)
                .addComponent(modernScrollPane, 200, 1035, 1035));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(modernScrollPane, 200, 596, 596));
        this.setLayout(layout);
        this.setBorder(null);
        this.setBackground(new Color(24, 24, 24));
    }
    public void addAlbums() {
        int calRows = (Main.albums.getAlbums().size() / 4);
        System.out.println(calRows);
        data = new FriendsActivity[calRows][4];
        for (int i = 0; i < calRows * 4; i++)
        {
            try {
                if (i < Main.albums.getAlbums().size()) {
                    FriendsActivity f = new FriendsActivity(Main.albums.getAlbums().get(i).getMusics().get(0), Main.username);
                    data[(i / 4)][(i % 4)] = f;
                }
                else
                {
                    data[(i / 4)][(i % 4)] = null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            }
        }
    }

    public AlbumsTable getAlbumsTable() {
        return albumsTable;
    }
}
