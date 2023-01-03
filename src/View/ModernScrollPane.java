package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * This is an implementation of a JScrollPane with a modern UI
 *
 * @author Philipp Danner
 *
 */
public class ModernScrollPane extends JScrollPane {

    private static final long serialVersionUID = 8607734981506765935L;

    private static final int SCROLL_BAR_ALPHA_ROLLOVER = 100;
    private static final int SCROLL_BAR_ALPHA = 70;
    private static final int THUMB_SIZE = 15;
    private static final int SB_SIZE = 15;

    public ModernScrollPane(Component view) {
        this(view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    public ModernScrollPane(Component view, Color color) {
        this(view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED, color);
    }

    public ModernScrollPane(int vsbPolicy, int hsbPolicy) {
        this(null, vsbPolicy, hsbPolicy);
    }

    public ModernScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        setBorder(null);

        // Set ScrollBar UI
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setUI(new ModernScrollBarUI(this));
        getVerticalScrollBar().setBorder(null);

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setUI(new ModernScrollBarUI(this));
        getHorizontalScrollBar().setBorder(null);
        viewport.setView(view);
        setBackground(new Color(24, 24, 24));
        viewport.setBackground(new Color(24, 24, 24));
    }

    public ModernScrollPane(Component view, int vsbPolicy, int hsbPolicy, Color color) {
        setBorder(null);

        // Set ScrollBar UI
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setOpaque(false);
        verticalScrollBar.setUI(new ModernScrollBarUI(this));
        getVerticalScrollBar().setBorder(null);

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setOpaque(false);
        horizontalScrollBar.setUI(new ModernScrollBarUI(this));
        getHorizontalScrollBar().setBorder(null);
        viewport.setView(view);
        setBackground(color);
        viewport.setBackground(color);
    }

    /**
     * Class extending the BasicScrollBarUI and overrides all necessary methods
     */
    private static class ModernScrollBarUI extends BasicScrollBarUI {

        private JScrollPane sp;

        public ModernScrollBarUI(ModernScrollPane sp) {
            this.sp = sp;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new InvisibleScrollBarButton(scrollbar.getOrientation(), "⌃");
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new InvisibleScrollBarButton(scrollbar.getOrientation(), "⌄");
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            int orientation = scrollbar.getOrientation();
            int x = trackBounds.x;
            int y = trackBounds.y;

            int width = orientation == JScrollBar.VERTICAL ? THUMB_SIZE : trackBounds.width;
            width = Math.max(width, THUMB_SIZE);

            int height = orientation == JScrollBar.VERTICAL ? trackBounds.height : THUMB_SIZE;
            height = Math.max(height, THUMB_SIZE);

            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setColor(new Color(24, 24, 24));
            graphics2D.fillRect(x, y, width, height);
            graphics2D.dispose();
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            int alpha = isThumbRollover() ? SCROLL_BAR_ALPHA_ROLLOVER : SCROLL_BAR_ALPHA;
            int orientation = scrollbar.getOrientation();
            int x = thumbBounds.x;
            int y = thumbBounds.y;

            int width = orientation == JScrollBar.VERTICAL ? THUMB_SIZE : thumbBounds.width;
            width = Math.max(width, THUMB_SIZE);

            int height = orientation == JScrollBar.VERTICAL ? thumbBounds.height : THUMB_SIZE;
            height = Math.max(height, THUMB_SIZE);

            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setColor(new Color(180, 180, 180, alpha));
            graphics2D.fillRect(x, y, width, height);
            graphics2D.dispose();
        }

        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            sp.repaint();
        }

        /**
         * Invisible Buttons, to hide scroll bar buttons
         */
        private static class InvisibleScrollBarButton extends JButton {

            private static final long serialVersionUID = 1552427919226628689L;

            private InvisibleScrollBarButton(int orientation, String text) {
                super();
                setBackground(new Color(18, 18, 18));
                setForeground(new Color(158, 158, 158));
                setOpaque(true);
                setFocusable(false);
                setBorder(BorderFactory.createEmptyBorder());
                setBorderPainted(false);
                setFocusPainted(false);
            }
        }
    }
}