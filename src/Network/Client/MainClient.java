package Network.Client;

import Controller.Main;
import Model.Music;
import Model.User;
import View.MainFrame;
//import com.sun.deploy.util.SessionState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class MainClient
{
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Socket client;
    private Vector<Socket> sockets = new Vector<>();
    private Vector<String> friends = new Vector<>();
    static Vector<Music> musics = new Vector<>();
    private Sharing sharing;
    private int port;
    public static User user;
    static MainFrame mainFrame;

    public MainClient(Vector<Music> musics, User user, MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        this.user = user;
        this.musics = musics;
        this.user.setIp(InetAddress.getLocalHost().getHostAddress());
        System.out.println(user.getIp());
        port = 6500;
        client = new Socket("localhost", port);
        try
        {
            outputStream = new ObjectOutputStream(client.getOutputStream());
            inputStream = new ObjectInputStream(client.getInputStream());
            this.user.setObjectOutputStream(outputStream);
            this.user.setObjectInputStream(inputStream);
            sharing = new Sharing();
            Thread t = new Thread(sharing);
            t.start();
            sharing.hiServer();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addFriend(String ip) throws IOException
    {
        friends.add(ip);
        Socket temp = new Socket(ip, port);
        ServerHandler serverHandler = new ServerHandler(temp);
        Thread thread = new Thread(serverHandler);
        sockets.add(temp);
        serverHandler.setAddPlayingMusic(mainFrame.getMainPanel().getFriendsPanel());
        thread.start();
        serverHandler.hiFriend();
    }

    public Sharing getSharing()
    {
        return sharing;
    }
}