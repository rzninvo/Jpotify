package View;

import Controller.Main;
import Listeners.LoginPanelListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;

public class LoginMainPanel extends JPanel {
    private GroupLayout layout;
    private LoginPanel loginPanel;
    private TableForIpPanel tableForIpPanel;
    private DefaultTableModel defaultTableModel;
    private boolean inserted;
    private TransparentButton close = new TransparentButton("✕", false);
    private TransparentButton restoreDown = new TransparentButton("◻", false);
    private TransparentButton minimize = new TransparentButton("⚊", false);
    private ListenerLoginMainPanel listenerLoginMainPanel = new ListenerLoginMainPanel();
    private LoginPanelListener loginPanelListener = null;
    private MainFrame mainFrame;
    Object data[][] = {};
    String headers[] = {"Connected Devices"};

    LoginMainPanel(int width, int height) {
        super();
        setBackground(new Color(18, 18, 18));
        defaultTableModel = new DefaultTableModel(data, headers);
        loginPanel = new LoginPanel();
        tableForIpPanel = new TableForIpPanel(defaultTableModel);
        close.setBackground(new Color(18, 18, 18));
        restoreDown.setBackground(new Color(18, 18, 18));
        minimize.setBackground(new Color(18, 18, 18));
        close.setSize(45, 30);
        restoreDown.setSize(45, 30);
        minimize.setSize(45, 30);
        close.setForeground(new Color(255, 255, 255));
        restoreDown.setForeground(new Color(255, 255, 255));
        minimize.setForeground(new Color(255, 255, 255));
        close.setHorizontalAlignment(SwingConstants.CENTER);
        restoreDown.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        close.setVerticalAlignment(SwingConstants.CENTER);
        restoreDown.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);
        close.addMouseListener(listenerLoginMainPanel);
        minimize.addMouseListener(listenerLoginMainPanel);
        restoreDown.addMouseListener(listenerLoginMainPanel);
        setSize(width, height);
        layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(610, 610)
                        .addComponent(loginPanel, 500, 500, 1000)
                        .addComponent(tableForIpPanel, 180, 180, 180)
                        .addContainerGap(400, 400))
                .addComponent(minimize, 45, 45, 45)
                .addComponent(restoreDown, 45, 45, 45)
                .addComponent(close, 45, 45, 45));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(minimize, 30, 30, 30)
                        .addComponent(restoreDown, 30, 30, 30)
                        .addComponent(close, 30, 30, 30))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(410, 410)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(loginPanel, 500, 500, 1000)
                                .addComponent(tableForIpPanel, 500, 500, 500))
                        .addContainerGap(450, 450)));
        this.setLayout(layout);
    }

    private void textHandler(boolean flag, JTextField textField, String baseText) {
        Runnable textChecker = new Runnable() {
            @Override
            public void run() {
                if (!flag) {
                    if (textField.getCaretPosition() == 0) {
                        inserted = false;
                        textField.setText(baseText);
                        textField.setCaretPosition(0);
                        textField.setForeground(new Color(115, 115, 115));
                    }
                } else {
                    if (inserted == false) {
                        if (!textField.getText().equals(baseText)) {
                            textField.setText(textField.getText().replace(baseText, ""));
                            inserted = true;
                            textField.setForeground(new Color(170, 170, 170));
                        }
                    }
                }
            }
        };
        SwingUtilities.invokeLater(textChecker);
    }

    private class FocusListenerForLoginPanel implements FocusListener {
        JTextField textField;
        String baseText;

        FocusListenerForLoginPanel(JTextField textField, String baseText) {
            this.textField = textField;
            this.baseText = baseText;
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().equals("")) {
                textField.setText(baseText);
                textField.setForeground(new Color(115, 115, 115));
            }
            textField.setBackground(new Color(60, 60, 60));
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals(baseText)) {
                textField.setText("");
                inserted = true;
            }
            textField.setBackground(new Color(118, 118, 118));
        }
    }

    public class ListenerLoginMainPanel extends MouseInputAdapter {
        private CustomLabelForSongsPanel customLabelForSongsPanel;
        private CustomLabelForSongsPanel customAddLabelForSongsPanel;
        private JTextField userName;
        private JTextField ip;

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (e.getSource() == close) {
                System.exit(0);
            }
            if (e.getSource() == restoreDown) {
                if (((JFrame) (getParent().getParent().getParent().getParent())).getWidth() == Toolkit.getDefaultToolkit().getScreenSize().getWidth()
                        && ((JFrame) (getParent().getParent().getParent().getParent())).getHeight() == Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 40) {
                    ((LoginMainFrame) (getParent().getParent().getParent().getParent())).setFullScreenMode(false);
                    ((JFrame) (getParent().getParent().getParent().getParent())).setSize(950, 600);
                } else {
                    Dimension dimPant = Toolkit.getDefaultToolkit().getScreenSize();
                    ((JFrame) (getParent().getParent().getParent().getParent())).setBounds(0, 0, (int) dimPant.getWidth(), (int) dimPant.getHeight() - 40);
                    ((LoginMainFrame) (getParent().getParent().getParent().getParent())).setFullScreenMode(true);
                }
            }
            if (e.getSource() == minimize) {
                ((JFrame) (getParent().getParent().getParent().getParent())).setState(Frame.ICONIFIED);
            }
            if (e.getSource() == customLabelForSongsPanel) {
                if ((!userName.getText().equals("Username")) && userName.getText().length() > 2) {
                    ((JFrame) (getParent().getParent().getParent().getParent())).dispose();
                    Main.username = userName.getText();
                    Main.setStatus(1);
                    ArrayList<String> friends = new ArrayList<>();
                    for (int i = 0; i < tableForIpPanel.getTableForIp().getRowCount(); i++)
                    {
                        friends.add((String) tableForIpPanel.getTableForIp().getValueAt(i,0));
                    }
                    try {
                        loginPanelListener.login(userName.getText(), friends,mainFrame);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Main.getMainFrame().setVisible(true);
                }
            }
            if (e.getSource() == customAddLabelForSongsPanel)
            {
                if (ip.getText().length() > 6) {
                    boolean flag = false;
                    for ( int i = 0; i < tableForIpPanel.getTableForIp().getRowCount(); i++)
                    {
                        if (ip.getText().equals(tableForIpPanel.getTableForIp().getValueAt(i, 0)))
                            flag = true;
                    }
                    if (flag == false) {
                        defaultTableModel.addRow(new Object[]{ip.getText()});
                        ip.setText("IP");
                        ip.setCaretPosition(0);
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (e.getSource() == customLabelForSongsPanel) {
                customLabelForSongsPanel.setColor(new Color(27, 145, 67, 255));
                customLabelForSongsPanel.repaint();
            }
            if (e.getSource() == customAddLabelForSongsPanel) {
                customAddLabelForSongsPanel.setColor(new Color(27, 145, 67, 255));
                customAddLabelForSongsPanel.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (e.getSource() == customLabelForSongsPanel) {
                customLabelForSongsPanel.setColor(new Color(29, 185, 75, 255));
                customLabelForSongsPanel.repaint();
            }
            if (e.getSource() == customAddLabelForSongsPanel) {
                customAddLabelForSongsPanel.setColor(new Color(29, 185, 75, 255));
                customAddLabelForSongsPanel.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            if (e.getSource() == customLabelForSongsPanel) {
                customLabelForSongsPanel.setColor(new Color(29, 185, 75, 255));
                customLabelForSongsPanel.repaint();
            }
            if (e.getSource() == customAddLabelForSongsPanel) {
                customAddLabelForSongsPanel.setColor(new Color(29, 185, 75, 255));
                customAddLabelForSongsPanel.repaint();
            }
            if (e.getSource() == close) {
                close.setBackground(new Color(255, 18, 14));
            }
            if (e.getSource() == restoreDown) {
                restoreDown.setBackground(new Color(100, 100, 100));
            }
            if (e.getSource() == minimize) {
                minimize.setBackground(new Color(100, 100, 100));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            if (e.getSource() == customLabelForSongsPanel) {
                customLabelForSongsPanel.setColor(new Color(29, 178, 73, 255));
                customLabelForSongsPanel.repaint();
            }
            if (e.getSource() == customAddLabelForSongsPanel) {
                customAddLabelForSongsPanel.setColor(new Color(29, 178, 73, 255));
                customAddLabelForSongsPanel.repaint();
            }
            if (e.getSource() == close) {
                close.setBackground(new Color(18, 18, 18));
            }
            if (e.getSource() == restoreDown) {
                restoreDown.setBackground(new Color(18, 18, 18));
            }
            if (e.getSource() == minimize) {
                minimize.setBackground(new Color(18, 18, 18));
            }
        }

        public void setCustomLabelForSongsPanel(CustomLabelForSongsPanel customLabelForSongsPanel) {
            this.customLabelForSongsPanel = customLabelForSongsPanel;
        }

        public void setCustomAddLabelForSongsPanel(CustomLabelForSongsPanel customAddLabelForSongsPanel) {
            this.customAddLabelForSongsPanel = customAddLabelForSongsPanel;
        }

        public void setUserName(JTextField userName) {
            this.userName = userName;
        }

        public void setIp(JTextField ip) {
            this.ip = ip;
        }
    }

    private class LoginPanel extends JPanel {
        private JLabel logo = new JLabel(Icons.rescaleIcon(Icons.JPOTIFYLOGIN_ICON, 70, 70));
        private JLabel jPotify = new JLabel("JPotify®");
        private CustomLabelForSongsPanel customLoginLabel;
        private CustomLabelForSongsPanel customAddLabel;
        private JTextField userName;
        private JTextField ip;

        LoginPanel() {
            customLoginLabel = new CustomLabelForSongsPanel("LOG IN", 105, 30, new Color(29, 178, 73, 255));
            customAddLabel = new CustomLabelForSongsPanel("ADD IP", 105, 30, new Color(29, 178, 73, 255));
            customLoginLabel.addMouseListener(listenerLoginMainPanel);
            customAddLabel.addMouseListener(listenerLoginMainPanel);
            ip = new JTextField("IP");
            userName = new JTextField("Username");
            userName.setCaretPosition(0);
            userName.setCaretColor(Color.white);
            userName.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));
            userName.setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 15, 0, 0)), userName.getBorder()));
            userName.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {

                }

                public void removeUpdate(DocumentEvent e) {
                    textHandler(false, userName, "Username");
                }

                public void insertUpdate(DocumentEvent e) {
                    textHandler(true, userName, "Username");
                }
            });
            ip.setCaretPosition(0);
            ip.setCaretColor(Color.white);
            ip.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0, 0)));
            ip.setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 5, 0, 0)), userName.getBorder()));
            ip.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {

                }

                public void removeUpdate(DocumentEvent e) {
                    textHandler(false, ip, "IP");
                }

                public void insertUpdate(DocumentEvent e) {
                    textHandler(true, ip, "IP");
                }
            });
            userName.addFocusListener(new FocusListenerForLoginPanel(userName, "Username"));
            ip.addFocusListener(new FocusListenerForLoginPanel(ip, "IP"));
            userName.setBackground(new Color(60, 60, 60));
            userName.setForeground(new Color(115, 115, 115));
            ip.setBackground(new Color(60, 60, 60));
            ip.setForeground(new Color(115, 115, 115));
            jPotify.setForeground(Color.white);
            userName.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
            ip.setFont(new Font("Proxima Nova Rg", Font.BOLD, 13));
            jPotify.setFont(new Font("Proxima Nova Rg", Font.BOLD, 35));
            customLoginLabel.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            customAddLabel.setFont(new Font("Proxima Nova Rg", Font.BOLD, 15));
            listenerLoginMainPanel.setUserName(userName);
            listenerLoginMainPanel.setIp(ip);
            listenerLoginMainPanel.setCustomLabelForSongsPanel(customLoginLabel);
            listenerLoginMainPanel.setCustomAddLabelForSongsPanel(customAddLabel);
            jPotify.setHorizontalAlignment(SwingConstants.LEFT);
            jPotify.setVerticalAlignment(SwingConstants.CENTER);
            setBackground(new Color(18, 18, 18));
            GroupLayout loginLayout = new GroupLayout(this);
            loginLayout.setHorizontalGroup(loginLayout.createSequentialGroup()
                    .addContainerGap(140, 140)
                    .addGroup(loginLayout.createParallelGroup()
                            .addGroup(loginLayout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(logo, 70, 70, 70)
                                    .addGap(5, 5, 5)
                                    .addComponent(jPotify, 180, 180, 180))
                            .addComponent(userName, 340, 340, 340)
                            .addComponent(ip, 340, 340, 340)
                            .addGroup(loginLayout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(customAddLabel, 105, 105, 105)
                                    .addGap(55, 55, 55)
                                    .addComponent(customLoginLabel, 105, 105, 105)))
                    .addContainerGap(100, 100));
            loginLayout.setVerticalGroup(loginLayout.createSequentialGroup()
                    .addGroup(loginLayout.createParallelGroup()
                            .addGroup(loginLayout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addComponent(logo, 70, 70, 70))
                            .addGroup(loginLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jPotify, 180, 180, 180)))
                    .addComponent(userName, 30, 30, 30)
                    .addGap(30, 30, 30)
                    .addComponent(ip, 30, 30, 30)
                    .addGap(50, 50, 50)
                    .addGroup(loginLayout.createParallelGroup()
                            .addComponent(customLoginLabel, 30, 30, 30)
                            .addComponent(customAddLabel, 30, 30, 30)));
            this.setLayout(loginLayout);
        }
    }

    public void setLoginPanelListener(LoginPanelListener loginPanelListener) {
        this.loginPanelListener = loginPanelListener;
    }

    public void setMainFrame(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }
}
