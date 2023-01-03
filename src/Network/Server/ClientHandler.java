package Network.Server;

import Model.Request;
import Model.User;
import Network.Client.MainClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable
{
    static Vector<User> users = new Vector<>();
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    //    private static User thisUser;
    private Socket socket;
    private boolean flag = false;
    private int fileSize;

    public ClientHandler(Socket client) throws Exception
    {
        if (client == null) throw new Exception("client can't be null");
        this.socket = client;
        objectInputStream = new ObjectInputStream(client.getInputStream());
        objectOutputStream = new ObjectOutputStream(client.getOutputStream());
        System.out.println("wait for client to send its username!");
    }

    public void run()
    {
        try
        {
            while (!socket.isClosed())
            {
                System.out.println("im listening");
                if (flag)
                {
                    byte[] bytes = new byte[fileSize];
                    int count = objectInputStream.read(bytes);
                    objectOutputStream.write(bytes);
                    System.out.println(count);
                    System.out.println("reached here");
                    flag = false;
                }
                else
                {
                    Request request = (Request) objectInputStream.readObject();
                    System.out.println(request.getReqsMusic() + " :||||");
                    if (request.getReqsMusic() == 0)
                    {
                        request.getUser().setObjectOutputStream(objectOutputStream);
                        request.getUser().setObjectInputStream(objectInputStream);
                        users.add(request.getUser());
                        System.out.println(socket.getInetAddress());
                        System.out.println("Welcome to server " + request.getUser().getUserName());
                    }
                    else
                    {
                        if (request.getReqsMusic() == 1 || request.getReqsMusic() == 4)
                        {
                            for (User user : users)
                                if (!user.equals(request.getUser()))
                                    user.getObjectOutputStream().writeObject(request);
                        }
                        else if (request.getReqsMusic() == 2)
                        {
                            for (User user : users)
                                if (user.equals(request.getUser()))
                                    user.getObjectOutputStream().writeObject(request);
                        }
                        else if (request.getReqsMusic() == 3)
                        {
                            for (User user : users)
                                if (user.equals(request.getUser()))
                                {
                                    user.getObjectOutputStream().writeObject(request);
                                }
                            flag = true;
                            fileSize = request.getFileSize();
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
