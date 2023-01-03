package View;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    private boolean focused;
    private boolean westPanel;
    CustomLabel()
    {
        super();
    }
    CustomLabel(String text, boolean focused, boolean westPanel)
    {
        super(text);
        this.focused = focused;
        this.westPanel = westPanel;
    }

    public boolean isFocused() {
        return focused;
    }

    public boolean isWestPanel() {
        return westPanel;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public void setWestPanel(boolean westPanel) {
        this.westPanel = westPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        if (focused && westPanel) {
            gd.setColor(new Color(20, 169, 91, 255));
            gd.fillRect(0, 0, 3, getWidth());
        } else if (westPanel) {
            gd.setColor(new Color(18, 18, 18));
            gd.fillRect(0, 0, 3, getWidth());
        }
    }
}
