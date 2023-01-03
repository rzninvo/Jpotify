package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FriendsTable extends JTable{
    private DefaultTableModel defaultTableModel;
    FriendsTable(DefaultTableModel defaultTableModel)
    {
        super(defaultTableModel);
        this.defaultTableModel = defaultTableModel;
        setDefaultRenderer(Object.class, new FriendsTableCellRenderer(false, -1));
        getTableHeader().setDefaultRenderer(new FriendsTableHeaderCellRenderer());
        setIntercellSpacing(new Dimension(0, 0));
        setColumnSelectionAllowed(false);
        setBackground(new Color(18, 18, 18));
        setBorder(null);
        setShowGrid(false);
        setRowSelectionAllowed(true);
        getTableHeader().setBackground(new Color(18, 18, 18));
        getTableHeader().setPreferredSize(new Dimension(240, 73));
        getTableHeader().setMaximumSize(new Dimension(240, 73));
        getTableHeader().setMinimumSize(new Dimension(240, 73));
        getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)));
        setColumnSelectionAllowed(false);
        getTableHeader().setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 10)), getTableHeader().getBorder()));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        setRowHeight(100);
        getColumnModel().getColumn(0).setPreferredWidth(240);
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }
}
