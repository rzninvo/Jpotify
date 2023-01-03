package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PlayListCellRendererForWestPanel extends CustomLabel implements ListCellRenderer {
    private int rolledOverIndex = -1;
    static int previousSelectedIndex = -1;
    private static WestPanel westPanel;

    PlayListCellRendererForWestPanel(WestPanel westPanel) {
        setOpaque(true);
        this.westPanel = westPanel;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object text, int index, boolean isSelected, boolean cellHasFocus) {
        setBackground(new Color(18, 18, 18));
        setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        setText(text.toString());
        if (isSelected) {
            if ((index != 0) && (index != 7)) {
                setForeground(Color.white);
                setFocused(true);
                setWestPanel(true);
                repaint();
                previousSelectedIndex = index;
                westPanel.loseFocus();
            }
            else
            {
                isSelected = false;
                setForeground(new Color(105, 105, 105));
                setFocused(false);
                setWestPanel(true);
                if (previousSelectedIndex != -1)
                    list.setSelectedIndex(previousSelectedIndex);
                repaint();
            }
        }
        else if (index == rolledOverIndex) {
            if (index != 0 && index != 7) {
                setForeground(Color.white);
                setWestPanel(true);
                setFocused(false);
                repaint();
            }
            else
            {
                isSelected = false;
                setForeground(new Color(105, 105, 105));
                setFocused(false);
                setWestPanel(true);
                repaint();
            }
        } else {
            if (index != 0 && index != 7)
                setForeground(new Color(180, 180, 180));
            else
                setForeground(new Color(105, 105, 105));
            setWestPanel(true);
            setFocused(false);
            repaint();
        }
        setBorder(BorderFactory.createEmptyBorder());
        setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 20, 0, 0)), getBorder()));
        return this;
    }

    public void setRolledOverIndex(int rolledOverIndex) {
        this.rolledOverIndex = rolledOverIndex;
    }
}
