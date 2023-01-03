package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchTextField extends JTextField {
    ImageIcon searchIcon;
    ImageIcon closeIcon;
    boolean inserted;

    SearchTextField(ImageIcon searchIcon, ImageIcon closeIcon) {
        super();
        this.searchIcon = searchIcon;
        this.closeIcon = closeIcon;
        this.setText("Search");
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));
        this.setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 20, 0, 0)), this.getBorder()));
        this.inserted = false;
        this.addFocusListener(new FocusListenerForSearchTextField());
        this.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {

            }

            public void removeUpdate(DocumentEvent e) {
                textHandler(false);
            }

            public void insertUpdate(DocumentEvent e) {
                textHandler(true);
            }
        });
    }

    private void textHandler(boolean flag) {
        Runnable textChecker = new Runnable() {
            @Override
            public void run() {
                if (!flag) {
                    if (getCaretPosition() == 0) {
                        inserted = false;
                        setText("Search");
                        setCaretPosition(0);
                    }
                } else {
                    if (inserted == false) {
                        if (!getText().equals("Search")) {
                            setText(getText().replace("Search", ""));
                            inserted = true;
                        }
                    }
                }
            }
        };
        SwingUtilities.invokeLater(textChecker);
    }

    private class FocusListenerForSearchTextField implements FocusListener {
        @Override
        public void focusLost(FocusEvent e) {
            if (getText().equals(""))
                setText("Search");
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (getText().equals("Search")) {
                setText("");
                inserted = true;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.drawImage(searchIcon.getImage(), 0, 4, 15, 15, null);
        gd.dispose();
    }
}
