package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class TableForIp extends JTable{
    private DefaultTableModel defaultTableModel;
    TableForIp(DefaultTableModel defaultTableModel)
    {
        super(defaultTableModel);
        this.defaultTableModel = defaultTableModel;
        setDefaultRenderer(Object.class, new TableForIpCellRenderer(false, -1));
        getTableHeader().setDefaultRenderer(new TableForIpCellRenderer(true, -1));
        setIntercellSpacing(new Dimension(0, 0));
        setColumnSelectionAllowed(false);
        setBackground(new Color(24, 24, 24));
        setBorder(null);
        setShowGrid(false);
        setRowSelectionAllowed(true);
        getTableHeader().setBackground(new Color(24, 24, 24));
        getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)));setColumnSelectionAllowed(false);
        getTableHeader().setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 10)), getTableHeader().getBorder()));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        getTableHeader().setPreferredSize(new Dimension(100, 30));
        getTableHeader().setMaximumSize(new Dimension(100, 30));
        getTableHeader().setMinimumSize(new Dimension(100, 30));
        setRowHeight(30);
        getColumnModel().getColumn(0).setPreferredWidth(100);
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }
}
