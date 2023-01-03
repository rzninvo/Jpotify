package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableForIpCellRenderer extends JLabel implements TableCellRenderer {
    boolean isRolledOver;
    private int column;
    private int row;
    private int rolledOverRow;
    private boolean isHeader;

    TableForIpCellRenderer(boolean isHeader, int rolledOverRow)
    {
        this.isHeader = isHeader;
        this.rolledOverRow = rolledOverRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object text, boolean isSelected, boolean hasFocus, int row, int column) {
        this.row = row;
        this.column = column;
        setBorder(null);
        setText((String) text);
        if (isSelected) {
            setBackground(new Color(80,80,80));
            setForeground(new Color(255, 255, 255));
        }
        else
        {
            setBackground(new Color(40, 40, 40));
            setForeground(new Color(170, 170, 170));
        }
        return this;
    }
}
