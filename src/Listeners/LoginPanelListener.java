package Listeners;

import View.MainFrame;

import java.io.IOException;
import java.util.ArrayList;

public interface LoginPanelListener
{
    void login(String user, ArrayList<String> friends, MainFrame mainFrame) throws IOException;
}
