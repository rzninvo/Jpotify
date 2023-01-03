package View;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JPanel {
    JTextField textField;
    private ImageIcon searchIcon;
    private ImageIcon closeIcon;
    private JLabel close;

    CustomTextField(int width, int height, ImageIcon searchIcon, ImageIcon closeIcon) {
        super();
        this.setSize(width, height);
        textField = new SearchTextField(searchIcon, closeIcon);
        textField.setSize(this.getWidth() - this.getHeight(), this.getHeight());
        this.searchIcon = searchIcon;
        this.closeIcon = closeIcon;
        this.close = new JLabel(closeIcon);
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup().addGap(this.getHeight() / 2, this.getHeight() / 2, this.getHeight() / 2)
                .addComponent(textField, this.getWidth() - this.getHeight(), this.getWidth() - this.getHeight(),
                        this.getWidth() - this.getHeight())
                .addComponent(close, 10, 10, 10));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(textField, this.getHeight(), this.getHeight(), this.getHeight())
                .addComponent(close, 10, 10, 10));
        this.setLayout(layout);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.setColor(new Color(255, 255, 255, 255));
        gd.fillOval(0, 0, this.getHeight(), this.getHeight());
        gd.fillOval(this.getWidth() - this.getHeight(), 0, this.getHeight(), this.getHeight());
    }
}
