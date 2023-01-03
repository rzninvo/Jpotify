package View;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class PlayListForWestPanel extends JList {
    private DefaultListModel defaultListModel;
    private int rolledOverRowIndex = -1;
    CentrePanel centrePanel;
    private int prevSelectedIndex = -1;

    PlayListForWestPanel(DefaultListModel defaultListModel, WestPanel westPanel, CentrePanel centrePanel) {
        super(defaultListModel);
        this.defaultListModel = defaultListModel;
        this.centrePanel = centrePanel;
        addMouseMotionListener(new MouseMotionListenerForPlayList(this));
        addMouseListener(new MouseListenerForPlayList());
        setCellRenderer(new PlayListCellRendererForWestPanel(westPanel));
        setOpaque(true);
        setVisibleRowCount(-1);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFixedCellHeight(30);
        setLayoutOrientation(VERTICAL);
        setBackground(new Color(18, 18, 18));
        setBorder(BorderFactory.createEmptyBorder());
    }

    public class MouseMotionListenerForPlayList implements MouseMotionListener {
        private JList list;

        MouseMotionListenerForPlayList(JList list) {
            this.list = list;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int row = locationToIndex(e.getPoint());
            if (row != rolledOverRowIndex) {
                rolledOverRowIndex = row;
                ((PlayListCellRendererForWestPanel) getCellRenderer().getListCellRendererComponent
                        (list, defaultListModel.getElementAt(row), row, false, false)).setRolledOverIndex(row);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }
    }

    public class MouseListenerForPlayList implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = locationToIndex(e.getPoint());
            if (index != prevSelectedIndex || index == 3) {
                try {
                    if (index != 5 && index != 6) {
                        if (index != 3) {
                            centrePanel.updateTable((String) defaultListModel.getElementAt(index), null, false);
                            centrePanel.setState(0);
                            centrePanel.update();
                        }
                        else
                        {
                            centrePanel.setState(1);
                            centrePanel.update();
                        }

                    }
                    else {
                        if (index == 5) {
                            centrePanel.updateTable("Favourites", null,false);
                            centrePanel.setState(0);
                            centrePanel.update();
                        }
                        if (index == 6) {
                            centrePanel.updateTable("Shared Playlist", null, false);
                            centrePanel.setState(0);
                            centrePanel.update();
                        }
                    }
                    prevSelectedIndex = index;
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
    }

}
