package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableForIpPanel extends JPanel {
    private TableForIp tableForIp;
    private DefaultTableModel defaultTableModel;
    private ModernScrollPane modernScrollPane;
    private GroupLayout layout;

    TableForIpPanel(DefaultTableModel defaultTableModel) {
        super();
        this.defaultTableModel = defaultTableModel;
        tableForIp = new TableForIp(defaultTableModel)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableForIp.setSize(200, 500);
        tableForIp.setBackground(new Color(24, 24, 24));
        modernScrollPane = new ModernScrollPane(tableForIp);
        modernScrollPane.setHorizontalScrollBar(null);
        modernScrollPane.setBackground(new Color(24, 24, 24));
        modernScrollPane.setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(24, 24, 24));
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(modernScrollPane, 100, 100, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(modernScrollPane, 500, 500, 500)
        );
        this.setLayout(layout);
    }

    public TableForIp getTableForIp() {
        return tableForIp;
    }
}
