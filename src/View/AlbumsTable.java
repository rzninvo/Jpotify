package View;

import Controller.Main;
import Model.FriendsActivity;
import Model.Library;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class AlbumsTable extends JTable {
    private DefaultTableModel defaultTableModel;
    private CentrePanel centrePanel;
    private int rollOverRowIndex;
    private int rollOverColumnIndex;


    AlbumsTable(DefaultTableModel defaultTableModel, CentrePanel centrePanel) {
        super(defaultTableModel);
        this.centrePanel = centrePanel;
        this.defaultTableModel = defaultTableModel;
        setDefaultRenderer(Object.class, new AlbumsTableCellRenderer(false, -1));
        getTableHeader().setDefaultRenderer(new AlbumsTableHeaderCellRenderer());
        this.addMouseListener(new MouseListener(this));
        this.addMouseMotionListener(new MouseListener(this));
        setIntercellSpacing(new Dimension(0, 0));
        setColumnSelectionAllowed(false);
        setBackground(new Color(24, 24, 24));
        setBorder(null);
        setShowGrid(false);
        setRowSelectionAllowed(true);
        getTableHeader().setBackground(new Color(24, 24, 24));
        getTableHeader().setPreferredSize(new Dimension(1004, 39));
        getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)));
        setColumnSelectionAllowed(false);
        getTableHeader().setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 10)), getTableHeader().getBorder()));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        setRowHeight(250);
        getColumnModel().getColumn(0).setPreferredWidth(258);
        getColumnModel().getColumn(1).setPreferredWidth(258);
        getColumnModel().getColumn(2).setPreferredWidth(258);
        getColumnModel().getColumn(3).setPreferredWidth(258);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        return c;
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public class MouseListener extends MouseInputAdapter {
        private AlbumsTable table;
        MouseListener(AlbumsTable table)
        {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int row = rowAtPoint(e.getPoint());
            int col = columnAtPoint(e.getPoint());
            CentrePanel.state = 0;
            try {
                centrePanel.updateTable("yo", Main.albums.getAlbums().get((row * 4) + col), true);
                centrePanel.update();
            } catch (InvalidDataException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (UnsupportedTagException e1) {
                e1.printStackTrace();
            }
        }

        public void mouseMoved(MouseEvent e)
        {
            int row = rowAtPoint(e.getPoint());
            int col = columnAtPoint(e.getPoint());
            if (row != rollOverRowIndex || col != rollOverColumnIndex)
            {
                rollOverRowIndex = row;
                rollOverColumnIndex = col;
                ((AlbumsTableCellRenderer)table.getDefaultRenderer(Object.class).getTableCellRendererComponent(table, table.getValueAt(row, col), false, false, row, col)).setRolledOverRow(row);
                ((AlbumsTableCellRenderer)table.getDefaultRenderer(Object.class).getTableCellRendererComponent(table, table.getValueAt(row, col), false, false, row, col)).setRolledOverCollumn(col);
                System.out.println("Row" + row + "Col" + col);
                repaint();
            }
        }
    }
}
