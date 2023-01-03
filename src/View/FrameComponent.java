package View;

import javax.swing.*;
import java.awt.*;

public class FrameComponent extends JComponent {

    private static final long serialVersionUID = 3383070502274306213L;

    private Insets insets;

    @Override
    public boolean contains(int x, int y) {
        return x < insets.left || y < insets.top || getHeight() - y < insets.bottom || getWidth() - x < insets.right;
    }

    public FrameComponent(Insets insets) {
        this.insets = insets;
    }
}