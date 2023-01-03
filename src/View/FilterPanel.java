package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class FilterPanel extends JPanel {
    private JTextField filter;
    boolean focused = false;
    FilterPanel(int width, int height, SongsTable table)
    {
        super();
        filter = new JTextField();
        filter.setBackground(new Color(24, 24, 24));
        filter.setForeground(new Color(170, 170, 170));
        filter.setBorder(null);
        filter.setText("Filter");
        filter.setFont(new Font("Proxima Nova Rg", Font.PLAIN, 15));
        filter.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        if (focused && (!filter.getText().equals("Filter")))
                            table.newFilter(filter.getText());
                    }
                    public void insertUpdate(DocumentEvent e) {
                        if (focused && (!filter.getText().equals("Filter")))
                            table.newFilter(filter.getText());
                    }
                    public void removeUpdate(DocumentEvent e) {
                        if (focused && (!filter.getText().equals("Filter")))
                            table.newFilter(filter.getText());
                    }
                });
        filter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    filter.setFocusable(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        filter.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filter.setFocusable(true);
                filter.requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        filter.addFocusListener(new FocusListenerForFilter(filter));
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(40, 40, 40)));
        setBackground(new Color(24, 24, 24));
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(filter, 988, 988, 988));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(filter, height, height, height));
        this.setLayout(layout);
    }

    private class FocusListenerForFilter implements FocusListener {
        JTextField filter;
        FocusListenerForFilter(JTextField filter)
        {
            this.filter = filter;
        }
        @Override
        public void focusLost(FocusEvent e) {
            filter.setText("Filter");
            focused = false;
            filter.setForeground(new Color(170, 170, 170));
            filter.setBackground(new Color(24, 24, 24));
        }

        @Override
        public void focusGained(FocusEvent e) {
            filter.setText("");
            filter.setForeground(new Color(255, 255, 255));
            filter.setBackground(new Color(80, 80, 80));
            focused = true;
        }
    }
}
