package View;

import Controller.Main;
import Model.FriendsActivity;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AlbumsTableCellRenderer extends JPanel implements TableCellRenderer {
    private boolean isHeader;
    private int rolledOverRow;
    private int rolledOverCollumn;
    private FriendsActivity friendsActivity;
    private JLabel userName = new JLabel();
    private JLabel title = new JLabel();
    private JLabel album = new JLabel();
    private JLabel artist = new JLabel();
    private JLabel image = new JLabel();

    GroupLayout layout = new GroupLayout(this);

    AlbumsTableCellRenderer(boolean isHeader, int rolledOverRow) {
        this.isHeader = isHeader;
        this.rolledOverRow = rolledOverRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(new Color(24, 24, 24));
        if ((row + 1) * (column + 1) <= Main.albums.getAlbums().size()) {
            if (value != null) {
                friendsActivity = (FriendsActivity) value;
                album.setText(friendsActivity.getMusicAlbum());
                artist.setText(friendsActivity.getMusicArtist());
                if (friendsActivity.getMusicImage() != null)
                    image.setIcon(ImageEditor.rescaleImage(ImageEditor.circleMaskImage(friendsActivity.getMusicImage()), 170, 170));
                else
                    image.setIcon(Icons.rescaleIcon(Icons.NO_ARTWORK, 170, 170));
                if (getLayout() != null) {
                    getLayout().removeLayoutComponent(this);
                    setLayout(null);
                }
                album.setForeground(Color.white);
                artist.setForeground(new Color(120, 120, 120));
                album.setBackground(new Color(24, 24, 24));
                artist.setBackground(new Color(24, 24, 24));
                album.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
                artist.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
                album.setHorizontalAlignment(SwingConstants.CENTER);
                artist.setHorizontalAlignment(SwingConstants.CENTER);
                layout.setHorizontalGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 38, 38)
                                .addComponent(image, 170, 170, 170))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 38, 38)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(album, 100, 170, 170)
                                        .addComponent(artist, 100, 170, 170))));
                layout.setVerticalGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(image, 170, 170, 170)
                        .addGap(10, 10, 10)
                        .addGap(10, 10, 10)
                        .addComponent(album, 20, 20, 20)
                        .addComponent(artist, 20, 20, 20)
                        .addGap(10, 10, 10));
                this.setLayout(layout);
                if (row == rolledOverRow && column == rolledOverCollumn)
                    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
                else
                    this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(24, 24, 24)));
            }
            else
            {
                setBackground(new Color(24, 24, 24));
                setOpaque(false);
                setVisible(false);
            }
        }
        else
        {
            setBackground(new Color(24, 24, 24));
            setOpaque(false);
            setVisible(false);
        }
        return this;
    }

    public void setRolledOverRow(int rolledOverRow) {
        this.rolledOverRow = rolledOverRow;
    }

    public void setRolledOverCollumn(int rolledOverCollumn) {
        this.rolledOverCollumn = rolledOverCollumn;
    }
}
