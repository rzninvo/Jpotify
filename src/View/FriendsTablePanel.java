package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FriendsTablePanel extends JPanel {
    private DefaultTableModel defaultTableModel;
    private ModernScrollPane modernScrollPane;
    private FriendsTable friendsTable;
    private GroupLayout layout;
    FriendsTablePanel(DefaultTableModel defaultTableModel, int frameHeight)
    {
        super();
        this.defaultTableModel = defaultTableModel;
        friendsTable = new FriendsTable(defaultTableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        friendsTable.setBackground(new Color(18, 18, 18));
        modernScrollPane = new ModernScrollPane(friendsTable, new Color(18, 18, 18));
        modernScrollPane.setHorizontalScrollBar(null);
        modernScrollPane.setBackground(new Color(18, 18, 18));
        modernScrollPane.setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(18, 18, 18));
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(modernScrollPane, 240, 240, 240));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(modernScrollPane, 220, 220, 1000)
        );
        this.setLayout(layout);
    }
}
