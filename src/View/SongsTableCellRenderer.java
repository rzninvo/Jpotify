package View;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SongsTableCellRenderer extends JLabel
        implements TableCellRenderer {
    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;
    boolean isHeader;
    private int rolledOverRow;
    private int rolledOverHeaderColumn;
    private int selectedHeaderColumn;
    private int selectedRow = -2;
    private int sortOrder = 0;
    boolean isRolledOver;
    private int column;
    private int row;


    public SongsTableCellRenderer(boolean isHeader, int rolledOverRow) {
        this.isHeader = isHeader;
        this.rolledOverRow = rolledOverRow;
        this.isRolledOver = false;
        setHorizontalAlignment(LEFT);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(
            JTable table, Object text,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        this.row = row;
        this.column = column;
        if (!isHeader) {
            if (column != 6 && column != 5 && column != 7 && column != 0 && column != 1)
                setFont(new Font("NotoSans-Regular", Font.BOLD, 15));
            else if (column == 6)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 7));
            else if (column == 7)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 16));
            else if (column == 5)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 16));
            else if (column == 0)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 20));
            else if (column == 1)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 20));
            else
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 15));
        } else {
            if (column < 5)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 13));
            else if (column == 5)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 16));
            else if (column == 7)
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 16));
            else
                setFont(new Font("NotoSans-Regular", Font.PLAIN, 13));
        }
        setBorder(null);
        setText((String) text);
        if (isSelected) {
            setBackground(new Color(60, 60, 60));
            if (row != selectedRow) {
                if (column < 5 && column > 1)
                    setForeground(Color.white);
                else
                    setForeground(new Color(170, 170, 170));
            } else {
                if (column == 6 || column <= 1)
                    setForeground(new Color(170, 170, 170));
                else
                    setForeground(new Color(29, 178, 73, 255));
            }
            if (isHeader)
                setForeground(Color.white);
        } else {
            setBackground(new Color(24, 24, 24));
            if (column != 6 && column != 0) {
                if (row != selectedRow)
                    setForeground(Color.white);
                else
                    setForeground(new Color(29, 178, 73, 255));
            }
            else {
                if (row != selectedRow)
                    setForeground(new Color(24, 24, 24));
                else {
                    if (column == 0)
                        setForeground(new Color(170, 170, 170));
                    else if (column == 6)
                        setForeground(new Color(24, 24, 24));
                }
            }
            if ((isHeader && column != 6))
                setForeground(new Color(170, 170, 170));
            if (column == 5 || column == 7 || column == 1)
            {
                if (row != selectedRow)
                    setForeground(new Color(170, 170, 170));
                else
                {
                    if (column == 1)
                        setForeground(new Color(170, 170, 170));
                    else
                        setForeground(new Color(29, 178, 73, 255));
                }
            }
        }
        if (isHeader && column == selectedHeaderColumn) {
            if (sortOrder == 0) {
                setForeground(new Color(170, 170, 170));
            } else
                setForeground(Color.white);
        }
        if (isHeader && column == rolledOverHeaderColumn) {
            setForeground(Color.white);
        }
        if (column != 6 && column != 4 && column != 7 && column != 2 && column != 3 && column != 5 && column != 0)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 20)), getBorder()));
        else if (column == 0)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 15, 0, 0)), getBorder()));
        else if (column == 5)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 0)), getBorder()));
        else if (column == 6)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 11, 0, 0)), getBorder()));
        else if (column == 4)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 50)), getBorder()));
        else if (column == 7)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 35, 0, 0)), getBorder()));
        else if (column == 2)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 5)), getBorder()));
        else if (column == 3)
            setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 5)), getBorder()));
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        if (isRolledOver && column != 6 && column > 1 && isHeader == false) {
            gd.setColor(Color.white);
            StringMetrics s = new StringMetrics(gd);
            int calculateX = 0;
            if (this.getText() != null) {
                calculateX = (int) s.getWidth(this.getText());
                gd.drawLine(0, (int) s.getHeight(this.getText()) + 10,
                        (calculateX < getWidth()) ? calculateX : calculateX - getBorder().getBorderInsets(this).right, (int) s.getHeight(this.getText()) + 10);
            }
        } else if (column == 6 || column == 0) {
            setForeground(Color.WHITE);
        }
        if (isHeader && column == selectedHeaderColumn) {
            if (sortOrder != 0)
                gd.setColor(new Color(29, 178, 73, 255));
            if (sortOrder == 0 || getText().equals(""))
                gd.setColor(new Color(24, 24, 24));
            StringMetrics s = new StringMetrics(gd);
            int calculateX = (int) s.getWidth(this.getText());
            int calculateY = (int) s.getHeight(this.getText());
            int startX = (column == 7) ? ((getWidth() / 2) - 5) : 0;
            gd.drawLine(startX, (int) s.getHeight(this.getText()) + 10,
                    (calculateX < getWidth()) ? startX + calculateX : calculateX - getBorder().getBorderInsets(this).right, (int) s.getHeight(this.getText()) + 10);
            if (sortOrder == 2) {
                gd.drawLine(calculateX + 10 + startX, 15, calculateX + 16 + +startX, 15 + 8);
                gd.drawLine(calculateX + 16 + +startX, 15 + 8, calculateX + 22 + +startX, 15);
            }
            if (sortOrder == 1) {
                gd.drawLine(calculateX + 10 + startX, 15 + 8, calculateX + 16 + startX, 15);
                gd.drawLine(calculateX + 16 + startX, 15, calculateX + 22 + startX, 15 + 8);
            }
        }
        gd.dispose();
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getSelectedHeaderColumn() {
        return selectedHeaderColumn;
    }

    public void setSelectedHeaderColumn(int selectedHeaderColumn) {
        this.selectedHeaderColumn = selectedHeaderColumn;
    }

    public int getRolledOverHeaderColumn() {
        return rolledOverHeaderColumn;
    }

    public void setRolledOverHeaderColumn(int rolledOverHeaderColumn) {
        this.rolledOverHeaderColumn = rolledOverHeaderColumn;
    }

    public int getRolledOverRow() {
        return rolledOverRow;
    }

    public void setRolledOverRow(int rolledOverRow) {
        this.rolledOverRow = rolledOverRow;
    }

    public boolean isRolledOver() {
        return isRolledOver;
    }

    public void setRolledOver(boolean rolledOver) {
        isRolledOver = rolledOver;
    }
}