package View;

import Listeners.*;
import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.RowSet;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.*;

public class SongsTable extends JTable implements PlayingMusicChanged
{

    private int rollOverRowIndex = -1;
    private int rollOverColumnIndex = -1;
    private int rollOverHeaderColumnIndex = -1;
    private int selectedHeaderColumnIndex = -1;
    private int selectedRowIndex = -1;
    private String selectedArtist = "";
    private String selectedSongName = "";
    private TableRowSorter<TableModel> sorter;
    private MenuForMusics menuForMusics;
    private SongsTableButtons songsTableButtons = null;
    private DefaultTableModel defaultTableModel;
    private PlayListChanged playListChanged = null;
    private boolean isPlayedFromTable = false;
    private GetPlayingMusic getPlayingMusic;

    public SongsTable(DefaultTableModel defaultTableModel)
    {
        super(defaultTableModel);
        this.defaultTableModel = defaultTableModel;
        menuForMusics = new MenuForMusics();
        setBorder(null);
        setBackground(new Color(24, 24, 24));
        RollOverListener lst = new RollOverListener(this);
        HeaderRollOverListener hlst = new HeaderRollOverListener(this);
        setColumnSelectionAllowed(false);
        setDefaultRenderer(Object.class, new SongsTableCellRenderer(false, -1));
        setIntercellSpacing(new Dimension(0, 1));
        setShowVerticalLines(true);
        setRowSelectionAllowed(true);
        setGridColor(new Color(40, 40, 40));
        getTableHeader().setDefaultRenderer(new SongsTableCellRenderer(true, -1));
        addMouseMotionListener(lst);
        addMouseListener(lst);
        tableHeader.addMouseMotionListener(hlst);
        tableHeader.addMouseListener(hlst);
        setRowHeight(39);
        getTableHeader().setBackground(new Color(24, 24, 24));
        getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 40, 40)));
        setColumnSelectionAllowed(false);
        getTableHeader().setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 0, 0, 28)), getTableHeader().getBorder()));
        getTableHeader().setPreferredSize(new Dimension(1004, 39));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        getColumnModel().getColumn(0).setPreferredWidth(50);
        getColumnModel().getColumn(0).setMaxWidth(50);
        getColumnModel().getColumn(0).setMinWidth(50);
        getColumnModel().getColumn(1).setPreferredWidth(40);
        getColumnModel().getColumn(1).setMaxWidth(40);
        getColumnModel().getColumn(1).setMinWidth(40);
        getColumnModel().getColumn(2).setPreferredWidth(320);
        getColumnModel().getColumn(2).setMaxWidth(320);
        getColumnModel().getColumn(2).setMinWidth(320);
        getColumnModel().getColumn(3).setPreferredWidth(160);
        getColumnModel().getColumn(3).setMaxWidth(160);
        getColumnModel().getColumn(3).setMinWidth(160);
        getColumnModel().getColumn(4).setPreferredWidth(210);
        getColumnModel().getColumn(4).setMaxWidth(210);
        getColumnModel().getColumn(4).setMinWidth(210);
        getColumnModel().getColumn(5).setPreferredWidth(90);
        getColumnModel().getColumn(5).setMaxWidth(90);
        getColumnModel().getColumn(5).setMinWidth(90);
        getColumnModel().getColumn(6).setPreferredWidth(40);
        getColumnModel().getColumn(6).setMaxWidth(40);
        getColumnModel().getColumn(6).setMinWidth(40);
        getColumnModel().getColumn(7).setPreferredWidth(80);
        getColumnModel().getColumn(7).setMaxWidth(80);
        getColumnModel().getColumn(7).setMinWidth(80);
        sorter = new TableRowSorter<>(getModel());
        setRowSorter(sorter);
    }

    public void newFilter(String text)
    {
        RowFilter<TableModel, Object> filter = null;
        try
        {
            filter = RowFilter.regexFilter(text);
        }
        catch (java.util.regex.PatternSyntaxException e)
        {
            return;
        }
        sorter.setRowFilter(filter);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
    {
        Component c = super.prepareRenderer(renderer, row, column);
        if (c instanceof SongsTableCellRenderer)
        {
            if (row == rollOverRowIndex)
            {
                if ((row == rollOverRowIndex && column == rollOverColumnIndex) && column < 5 || column == 6)
                {
                    ((SongsTableCellRenderer) c).setRolledOver(true);
                    if (column == 1)
                        ((SongsTableCellRenderer) c).setText("☓");
                    if (column == 0 && PlayPanel.playState == 2 && row == selectedRowIndex)
                        ((SongsTableCellRenderer) c).setText("\u23F8");
                }
                else
                {
                    ((SongsTableCellRenderer) c).setRolledOver(false);
                    if (column == 0 && PlayPanel.playState == 2 && row == selectedRowIndex)
                        ((SongsTableCellRenderer) c).setText("\uD83D\uDD0A");
                }
                c.setBackground(new Color(40, 40, 40));
                if (column == 6 || column == 1 || column == 0)
                    c.setForeground(Color.white);
            }
            else
            {
                ((SongsTableCellRenderer) c).setRolledOver(false);
                if (column == 0 && PlayPanel.playState == 2 && row == selectedRowIndex)
                    ((SongsTableCellRenderer) c).setText("\uD83D\uDD0A");
            }
        }
        return c;
    }

    @Override
    public void setRow(int row)
    {
        PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
        PlayPanel.playState = 2;
        SongsPanel.customLabelForSongsPanel.setText("PAUSE");
        if (row == selectedRowIndex)
        {
            selectedArtist = (String) this.getValueAt(row, 3);
            selectedSongName = (String) this.getValueAt(row, 2);
            rollOverRowIndex = -2;
            isPlayedFromTable = false;
        }
        else
        {
            selectedRowIndex = row;
            try
            {
                System.out.println(row);
                selectedArtist = (String) this.getValueAt(row, 3);
                selectedSongName = (String) this.getValueAt(row, 2);
                ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(this, this.getValueAt(row, 0), true
                        , true, row, 0)).setSelectedRow(row);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                row = 0;
                try
                {
                    selectedArtist = (String) this.getValueAt(row, 3);
                    selectedSongName = (String) this.getValueAt(row, 2);
                    ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(this, this.getValueAt(row, 0), true
                            , true, row, 0)).setSelectedRow(row);
                }
                catch (ArrayIndexOutOfBoundsException ex)
                {

                }
            }
            catch (IndexOutOfBoundsException e)
            {

            }
            isPlayedFromTable = true;
        }

        repaint();
    }


    private class HeaderRollOverListener extends MouseInputAdapter
    {
        JTable table;

        HeaderRollOverListener(JTable table)
        {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            int col = columnAtPoint(e.getPoint());
            if (selectedHeaderColumnIndex != col)
            {
                selectedHeaderColumnIndex = col;
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setSelectedHeaderColumn(col);
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setSortOrder(1);
                getTableHeader().repaint();
            }
            else
            {
                selectedHeaderColumnIndex = col;
                int sortOrder = ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).getSortOrder();
                if (sortOrder == 0)
                    sortOrder = 1;
                else if (sortOrder == 1)
                    sortOrder = 2;
                else if (sortOrder == 2)
                    sortOrder = 0;
                ((SongsTableCellRenderer)
                        (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                , getColumnName(col), false, true, -1, col))).setSortOrder(sortOrder);
            }
            if (selectedRowIndex >= 0)
            {
                for (int i = 0; i < table.getRowCount(); i++)
                {
                    if (table.getValueAt(i, 2).equals(selectedSongName) && table.getValueAt(i, 3).equals(selectedArtist))
                    {
                        ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(table, table.getValueAt(selectedRowIndex, 0), true
                                , true, selectedRowIndex, 0)).setSelectedRow(i);
                        selectedRowIndex = i;
                        ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(table, table.getValueAt(i, 0), true
                                , true, i, 0)).setSelectedRow(selectedRowIndex);
                        break;
                    }
                }
            }
            repaint();
            Vector<Music> musics = new Vector<>();
            for (int i = 0; i < table.getRowCount(); i++)
            {
                Music temp = new Music(null, (String) table.getValueAt(i, 3), (String) table.getValueAt(i, 2), null, null, null, null, null);
                musics.add(temp);
            }
            playListChanged.setPlaylist(musics);
        }

        public void mouseExited(MouseEvent e)
        {
            if (rollOverColumnIndex != -1)
                ((SongsTableCellRenderer) (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                        , getColumnName(rollOverColumnIndex), false, true, -1, rollOverColumnIndex))).setRolledOverHeaderColumn(-1);
            getTableHeader().repaint();
            rollOverHeaderColumnIndex = -1;
        }

        public void mouseMoved(MouseEvent e)
        {
            int col = columnAtPoint(e.getPoint());
            if (col != rollOverHeaderColumnIndex)
            {
                rollOverHeaderColumnIndex = col;
                if (col != -1)
                    ((SongsTableCellRenderer)
                            (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                                    , getColumnName(col), false, true, -1, col))).setRolledOverHeaderColumn(col);
                getTableHeader().repaint();
            }
        }
    }

    private class RollOverListener extends MouseInputAdapter
    {
        JTable table;

        RollOverListener(JTable table)
        {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            int row = rowAtPoint(e.getPoint());
            int col = columnAtPoint(e.getPoint());
            try
            {
                songsTableButtons.doAction(col, (String) table.getValueAt(row, 2), (String) table.getValueAt(row, 3));
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            catch (UnsupportedTagException ex)
            {
                ex.printStackTrace();
            }
            catch (TagException ex)
            {
                ex.printStackTrace();
            }
            catch (InvalidDataException ex)
            {
                ex.printStackTrace();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            if (col == 0)
            {
                if (isPlayedFromTable == false)
                {
                    SongsPanel.customLabelForSongsPanel.setText("PAUSE");
                    PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                    PlayPanel.playState = 2;
                    isPlayedFromTable = true;
                }
                else
                {
                    SongsPanel.customLabelForSongsPanel.setText("PLAY");
                    PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                    PlayPanel.playState = 1;
                    isPlayedFromTable = false;
                }
                if (row != selectedRowIndex && row >= 0)
                {
                    ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(table, table.getValueAt(row, 0), true
                            , true, row, 0)).setSelectedRow(row);
                    selectedRowIndex = row;
                    selectedArtist = (String) table.getValueAt(row, 3);
                    selectedSongName = (String) table.getValueAt(row, 2);
                    SongsPanel.customLabelForSongsPanel.setText("PAUSE");
                    PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PAUSE_ICON, 35, 35));
                    PlayPanel.playState = 2;
                    isPlayedFromTable = true;
                }
            }
            else if (col == 1)
            {
                if (row == selectedRowIndex)
                {
                    defaultTableModel.removeRow(row);
                    PlayPanel.play.setIcon(Icons.rescaleIcon(Icons.PLAY_ICON, 35, 35));
                    PlayPanel.playState = 0;
                    SongsPanel.customLabelForSongsPanel.setText("PLAY");
                    selectedRowIndex = -2;
                    selectedArtist = "";
                    selectedSongName = "";
                    rollOverRowIndex = -2;
                    isPlayedFromTable = false;
                }
                else
                {
                    defaultTableModel.removeRow(row);
                    if (isPlayedFromTable)
                    {
                        if (selectedRowIndex > row)
                            selectedRowIndex -= 1;
                        if (selectedRowIndex == -1)
                            selectedRowIndex = 0;
                        rollOverRowIndex = selectedRowIndex;
                        PlayPanel.playState = 2;
                        ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(table, table.getValueAt(selectedRowIndex, 0), true
                                , true, row, 0)).setSelectedRow(selectedRowIndex);
                    }
                }
            }
            else if (col == 6)
            {
                Music temp = new Music(null, (String) table.getValueAt(row, 3), (String) table.getValueAt(row, 2), null, null, null, null, null);
//                menuForMusics.getComponent().addMouseListener(new menuListener(row,table));
//                menuForMusics.addMouseListener(new menuListener(row,table));
                menuForMusics.setMusic(temp);
                menuForMusics.show(table, e.getX(), e.getY());
            }
            repaint();
        }

        public void mouseExited(MouseEvent e)
        {
            rollOverRowIndex = -1;
            rollOverColumnIndex = -1;
            repaint();
        }

        public void mouseMoved(MouseEvent e)
        {
            int row = rowAtPoint(e.getPoint());
            int col = columnAtPoint(e.getPoint());
            if (row != rollOverRowIndex || col != rollOverColumnIndex)
            {
                rollOverRowIndex = row;
                rollOverColumnIndex = col;
                if (rollOverColumnIndex != -1)
                    ((SongsTableCellRenderer) (getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table
                            , getColumnName(rollOverColumnIndex), false, true, -1, rollOverColumnIndex))).setRolledOverHeaderColumn(-1);
                rollOverHeaderColumnIndex = -1;
                repaint();
            }
        }
    }

    public void updateTableModel() throws IOException
    {
        boolean flag = false;
        rollOverRowIndex = -1;
        rollOverColumnIndex = -1;
        rollOverHeaderColumnIndex = -1;
        setDefaultRenderer(Object.class, new SongsTableCellRenderer(false, -1));
        setDefaultRenderer(Object.class, new SongsTableCellRenderer(false, -1));
        for (int i = 0; i < getModel().getRowCount(); i++)
        {
            Music music = getPlayingMusic.getPlayingMusic();
            if (music != null)
                if (getValueAt(i, 2).equals(music.getName()) && getValueAt(i, 3).equals(music.getArtist()))
                {
                    selectedRowIndex = i;
                    flag = true;
                    ((SongsTableCellRenderer) getDefaultRenderer(Object.class).getTableCellRendererComponent(this, this.getValueAt(i, 0), true
                            , true, i, 0)).setSelectedRow(i);
                    break;
                }
        }
        if (flag == false)
        {
            selectedRowIndex = -1;
            selectedArtist = "";
            selectedSongName = "";
        }
        repaint();
    }

    private class menuListener extends MouseInputAdapter
    {
        int row;
        JTable table;

        public menuListener(int row, JTable table)
        {
            this.row = row;
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
//            addSongToLibrary.addSongToLib(temp,e.getSource().toString());
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            mouseClicked(e);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            mouseClicked(e);
        }
    }

    public void setPlayListChanged(PlayListChanged playListChanged)
    {
        this.playListChanged = playListChanged;
    }

    public void setSongsTableButtons(SongsTableButtons songsTableButtons)
    {
        this.songsTableButtons = songsTableButtons;
    }

    public MenuForMusics getMenuForMusics()
    {
        return menuForMusics;
    }

    public void setGetPlayingMusic(GetPlayingMusic getPlayingMusic)
    {
        this.getPlayingMusic = getPlayingMusic;
    }
}