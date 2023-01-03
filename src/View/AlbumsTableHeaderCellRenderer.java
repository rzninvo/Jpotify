package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class AlbumsTableHeaderCellRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(new Color(18, 18, 18));
        setForeground(new Color(18, 18, 18));
        setFont(new Font("Proxima Nova Rg", Font.BOLD, 20));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)));
        setText((String)value);
        return this;
    }
}
