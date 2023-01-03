package View;

import Listeners.SongsTableListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class SongsTablePanel extends JPanel implements SongsTableListener
{
    private GroupLayout layout;
    private SongsTable songsTable;
    private ModernScrollPane modernScrollPane;
    private Object data[][]=null;
    static DefaultTableModel defaultTableModel;
    static DefaultListModel defaultListModel;
    SongsTablePanel()
    {
        super();
        this.defaultListModel = defaultListModel;
        String columns[] = {"","","TITLE", "ARTIST", "ALBUM", "\uD83D\uDCC6", "","\uD83D\uDD52"};
        defaultTableModel = new DefaultTableModel(data,columns);
        songsTable = new SongsTable(defaultTableModel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        songsTable.setBorder(null);
        modernScrollPane = new ModernScrollPane(songsTable);
        modernScrollPane.setBorder(BorderFactory.createEmptyBorder());
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addContainerGap(32, 32)
                .addComponent(modernScrollPane, 200, 1035, 1035));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(modernScrollPane, 200, 596, 596));
        this.setLayout(layout);
        this.setBorder(null);
        this.setBackground(new Color(24, 24, 24));
    }

    public SongsTable getSongsTable() {
        return songsTable;
    }

    @Override
    public void addSongs(String[][] data)
    {
        this.data = data;
        DefaultTableModel defaultTableModel = (DefaultTableModel) songsTable.getModel();
        if (songsTable.getRowCount() > 0) {
            for (int i = songsTable.getRowCount() - 1; i > -1; i--) {
                defaultTableModel.removeRow(i);
            }
        }
        for (int i = 0; i < data.length; i++)
        {
            defaultTableModel.addRow(data[i]);
        }
    }
}
