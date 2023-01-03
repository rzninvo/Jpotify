package View;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {
    private boolean focused;
    private boolean westPanel;
    TransparentButton(String text, ImageIcon imageIcon, boolean westPanel){
        super(text, imageIcon);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.focused = false;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.westPanel = westPanel;
    }
    TransparentButton(String text, boolean westPanel){
        super(text);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.focused = false;
        this.westPanel = westPanel;
    }

    TransparentButton(ImageIcon imageIcon, boolean westPanel){
        super(imageIcon);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.focused = false;
        this.westPanel = westPanel;
    }

    public void setFocused(Boolean focused) {
        this.focused = focused;
    }

    public Boolean getFocused() {
        return focused;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        if (focused && westPanel) {
            gd.setColor(new Color(20, 169, 91, 255));
            gd.fillRect(0, 0, 3, getWidth());
        }
        gd.dispose();
    }
}
