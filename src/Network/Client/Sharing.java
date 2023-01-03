package Network.Client;

import Controller.FileAndFolderBrowsing;
import Controller.Main;
import Listeners.AddPlayingMusic;
import Listeners.RequestToGetMusic;
import Model.*;
import View.MainFrame;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sharing implements Runnable, RequestToGetMusic
{
    private FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private Vector<User> users = new Vector<>();
    static Music music;
    private AddPlayingMusic addPlayingMusic = null;
    private static int count = -1;

    public Sharing()
    {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public synchronized void run()
            {
                try
                {
                    Request request = null;
                    try
                    {
                        count++;
                        System.out.println("playing music is " + music);
                        if (count != 0)
                            if (music != null && count % 2 == 1)
                            {
                                request = new Request(music, new User(MainClient.user.getUserName(), MainClient.user.getIp()));
                                System.out.println("sending music " + music.getName());
                                shareMusic(request);
                            }
                            else if (Main.sharedPlaylist != null && Main.sharedPlaylist.getMusics().size() > 0 && count % 2 == 0)
                            {
                                request = new Request(Main.sharedPlaylist, new User(MainClient.user.getUserName(), MainClient.user.getIp()));
                                shareMusic(request);
                            }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }



    public void hiServer() throws IOException
    {
        System.out.println(MainClient.user.getUserName());
        MainClient.user.getObjectOutputStream().writeObject(new Request(new User(MainClient.user.getUserName(), MainClient.user.getIp())));
    }

    public synchronized void shareMusic(Request request) throws IOException
    {
        try
        {
            MainClient.user.getObjectOutputStream().writeObject(request);
            System.out.println("sending request to " + request.getUser().getUserName());
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Request request = (Request) MainClient.user.getObjectInputStream().readObject();
                if (request.getReqsMusic() == 1)
                {
                    System.out.println(request.getUser().getUserName() + " is playing music" + request.getMusic().getName());
                    addPlayingMusic.addMusicToActivity(request.getMusic(), request.getUser());
                }
                else if (request.getReqsMusic() == 2)
                {
                    System.out.println(request.getUser().getUserName() + " wants music " + request.getMusic().getName());
                    File file = new File(MainClient.musics.get(MainClient.musics.indexOf(request.getMusic())).getFileLocation());
                    FileInputStream inputStream = (new FileInputStream(file));
                    System.out.println(file.length());
                    int fileSize = (int) file.length();
                    System.out.println(request.getWants().getUserName());
                    Request req = new Request(fileSize, request.getMusic(), request.getWants());
                    shareMusic(req);
                    System.out.println("Sending the file");
                    byte[] bytes = new byte[fileSize];
                    inputStream.read(bytes);
                    MainClient.user.getObjectOutputStream().write(bytes);
                    System.out.println("writing finished");
//                    int count,counter=0;
//                    while ((count = inputStream.read(bytes,0,bytes.length)) != -1) {
//                        counter+=count;
//                        System.out.println("sending "+counter);
//                        MainClient.user.getObjectOutputStream().write(bytes, 0, count);
//                    }
                    inputStream.close();
                }
                else if (request.getReqsMusic() == 3)
                {
                    System.out.println(request.getMusic().getName() + " Receiving from " + request.getUser().getUserName());
//                    byte[] byteArray = new byte[request.getFileSize()];
//                    MainClient.user.getObjectInputStream().read(byteArray);
                    OutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mp3"));
                    request.getMusic().setFileLocation("./SharedMusics/" + request.getMusic().getName().toLowerCase() + ".mp3");
                    byte[] bytes = new byte[8 * 1024];
                    int count;
                    while ((count = MainClient.user.getObjectInputStream().read(bytes)) > 0)
                    {
                        outputStream.write(bytes, 0, count);
                        System.out.println("trying to reach here");
                    }
                    System.out.println("finally reached here");
                    outputStream.close();
                    fileAndFolderBrowsing.addFileFolder(request.getMusic().getFileLocation(), MainClient.musics);
                }
                else if (request.getReqsMusic() == 4)
                {
                    Main.sharedPlaylist.setMusics(request.getSharedLibrary());
                    for (Music m : Main.sharedPlaylist.getMusics())
                        System.out.println(m.getName());
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
            catch (TagException e)
            {
                e.printStackTrace();
            }
            catch (InvalidDataException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedTagException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void setMusic(Music music)
    {
        Sharing.music = music;
    }

    public void setAddPlayingMusic(AddPlayingMusic addPlayingMusic)
    {
        this.addPlayingMusic = addPlayingMusic;
    }

    @Override
    public synchronized void send(Request request) throws IOException
    {
        shareMusic(request);
    }
}