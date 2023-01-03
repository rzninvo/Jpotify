package View;

import Model.FriendsActivity;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FriendsTableCellRenderer extends JPanel implements TableCellRenderer {
    private boolean isHeader;
    private int rolledOverRow;
    private FriendsActivity friendsActivity;
    private JLabel userName = new JLabel();
    private JLabel title = new JLabel();
    private JLabel album = new JLabel();
    private JLabel artist = new JLabel();
    private JLabel image = new JLabel();

    GroupLayout layout = new GroupLayout(this);

    FriendsTableCellRenderer(boolean isHeader, int rolledOverRow) {
        this.isHeader = isHeader;
        this.rolledOverRow = rolledOverRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(new Color(18, 18, 18));
        friendsActivity = (FriendsActivity) value;
        userName.setText(friendsActivity.getUser());
        title.setText(friendsActivity.getMusicTitle());
        album.setText(friendsActivity.getMusicAlbum());
        artist.setText(friendsActivity.getMusicArtist());
        image.setIcon(ImageEditor.rescaleImage(ImageEditor.circleMaskImage(friendsActivity.getMusicImage()), 50, 50));
        if (getLayout() != null)
        {
            getLayout().removeLayoutComponent(this);
            setLayout(null);
        }
        userName.setForeground(Color.white);
        title.setForeground(new Color(120, 120, 120));
        album.setForeground(new Color(120, 120, 120));
        artist.setForeground(new Color(120, 120, 120));
        userName.setBackground(new Color(18, 18, 18));
        title.setBackground(new Color(18, 18, 18));
        album.setBackground(new Color(18, 18, 18));
        artist.setBackground(new Color(18, 18, 18));
        userName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 16));
        album.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        artist.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        title.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(image, 50, 50, 50)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(userName, 100, 150, 150)
                        .addComponent(title, 100, 150, 150)
                        .addComponent(album, 100, 150, 150)
                        .addComponent(artist, 100, 150, 150)));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(image, 50, 50, 50)
                        .addGap(25, 25, 25))
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(userName, 20, 20, 20)
                        .addComponent(title, 20, 20, 20)
                        .addComponent(artist, 20, 20, 20)
                        .addComponent(album, 20, 20, 20)
                        .addGap(10, 10, 10)));
        this.setLayout(layout);
        return this;
    }
}
