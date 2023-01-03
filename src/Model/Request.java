package Model;

import java.io.Serializable;
import java.util.Vector;

public class Request implements Serializable
{
    private Music music = null;
    private Integer fileSize = null;
    private Vector<Music> sharedLibrary = null;
//    private Library sharedLibrary = null;
    private User user;
    private User wants;
    private int reqsMusic;

    public Request(User user)
    {
        this.user = user;
        reqsMusic = 0;
        //to share user
    }

    public Request(Music music, User user)
    {
        this.user = user;
        reqsMusic = 1;
        this.music = music;
        //to share music
    }

    public Request( User user,User wants,Music music)
    {
        this.user = user;
        this.wants=wants;
        reqsMusic = 2;
        this.music = music;
        //to get music
    }

    public Request(Integer fileSize, Music music, User user)
    {
        this.user = user;
        reqsMusic = 3;
        this.music = music;
        this.fileSize = fileSize;
        //to send music
    }


//    public Request(Library sharedLibrary, User user)
    public Request(Library sharedLibrary, User user)
    {
        this.user = user;
        this.sharedLibrary = sharedLibrary.getMusics();
        this.reqsMusic = 4;
        //to share playlist
    }

    public User getUser()
    {
        return user;
    }

    public Music getMusic()
    {
        return music;
    }

    public Integer getFileSize()
    {
        return fileSize;
    }

    public Vector<Music> getSharedLibrary()
    {
        return sharedLibrary;
    }

    public User getWants()
    {
        return wants;
    }

    public int getReqsMusic()
    {
        return reqsMusic;
    }
}
