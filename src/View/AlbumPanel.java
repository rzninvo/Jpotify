package View;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AlbumPanel extends JPanel
{
    private JLabel albums = new JLabel("Albums");
    private GroupLayout layout;
    AlbumPanel()
    {
        super();
        albums.setForeground(Color.white);
        albums.setVerticalAlignment(SwingConstants.CENTER);
        albums.setFont(new Font("Proxima Nova Rg", Font.BOLD, 40));
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(30, 30)
                .addComponent(albums, 200, 200, 200)
                .addGap(200, 690, 690)
                .addContainerGap(10, 10));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(130, 130)
                .addGroup(layout.createParallelGroup()
                        .addComponent(albums, 30, 30, 30)));
        this.setLayout(layout);
        setBackground(new Color(24, 24, 24));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g.create();
        gd.setColor(new Color(18, 18, 18));
        gd.fillRect(0, 0 ,1066, 80);
        gd.dispose();
    }
}
