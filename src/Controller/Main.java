package Controller;

import Listeners.SongsTableListener;
import Model.Albums;
import Model.Library;
import Model.Music;
import Model.User;
import Network.Client.MainClient;
import Network.Server.MainServer;
import View.LoginMainFrame;
import View.MainFrame;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main
{
    public static Vector<Music> musics = new Vector<>();
    public static Vector<Music> playlist;
    private static FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
    private static MainFrame mainFrame;
    private static LoginMainFrame loginMainFrame;
    public static Vector<Library> playLists;
    public static Albums albums;
    private static SongsTableListener songsTableListener = null;
    private static LoadingLibrary loadingLibrary = new LoadingLibrary();
    public static String username;
    public static String ip;
    static int status = 0;
    static MainClient mainClient;
    public static Library sharedPlaylist;
    public static Library favourites;

    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException
    {
        createDirs();
        int port = 6500;
        try
        {
            Thread t = new Thread(new MainServer(port));
            t.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    createAndShowGUI();
                }
                catch (UnsupportedTagException e)
                {
                    e.printStackTrace();
                }
                catch (InvalidDataException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() throws IOException, InvalidDataException, UnsupportedTagException, InterruptedException
    {
        fileAndFolderBrowsing.loadFiles(musics);
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (Music music : musics)
                    try
                    {
                        System.out.println(new LoadingLibrary().gatherMusicLyrics("u2", "with or without you"));
                        System.out.println("Gathering lyrics for " + music.getName());
                        System.out.println(new LoadingLibrary().gatherMusicLyrics(music.getArtist(), music.getName()));
                    }
                    catch (Exception e)
                    {

                    }
                for (Music music : musics)
                    try
                    {
                        new LoadingLibrary().gatherMusicLyrics("u2", "with or without you");
                        System.out.println("Gathering lyrics for " + music.getName());
                        System.out.println(new LoadingLibrary().gatherMusicLyrics(music.getArtist().replaceAll("[^a-zA-Z0-9 ._-]", ""), music.getName().replaceAll("[^a-zA-Z0-9 ._-]", "")));
                    }
                    catch (Exception e)
                    {

                    }
            }
        });
        t.start();
        playLists = fileAndFolderBrowsing.loadLibraries();
        sharedPlaylist = playLists.get(Main.getPlayLists().indexOf(new Library("Shared playlist")));
        favourites = playLists.get(Main.getPlayLists().indexOf(new Library("Favourites")));
        for (Music m : Main.sharedPlaylist.getMusics())
            System.out.println(m.getName());
        albums = new Albums(musics);
        loginMainFrame = new LoginMainFrame();
        playlist = musics;
        albums.loadAlbums();
        mainFrame = new MainFrame();
        loginMainFrame.setMainFrame(mainFrame);
        mainFrame.getMainPanel().update();
        setLinkers();
        songsTableListener.addSongs(loadingLibrary.generateTable(musics));
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().getMenuForMusics().addLibraries(playLists);
    }

    private static void createDirs() throws IOException
    {
        makeDir("./Lyrics/");
        makeDir("./Library/");
        makeDir("./SharedMusics/");
        makeDir("./Library/favourites.bin");
        makeDir("./Library/shared playlist.bin");
    }

    private static void makeDir(String path) throws IOException
    {
        File file = new File(path);
        if (!file.exists())
            if (file.isDirectory())
                file.mkdir();
            else
                file.createNewFile();
    }

    private static void setLinkers()
    {
        PlayPanelActions playPanelActions = new PlayPanelActions();
        mainFrame.getMainPanel().getPlayPanel().setPlayPanelListener(playPanelActions);
        TopLeftMenuActions topLeftMenuActions = new TopLeftMenuActions(albums);
        mainFrame.getMainPanel().getWestPanel().getMenuForWestPanel().setTopLeftMenuListener(topLeftMenuActions);
        songsTableListener = mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel();
        topLeftMenuActions.setSongsTableListener(mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel());
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().setSongsTableButtons(playPanelActions);
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsPanel().setSongsPanelListener(playPanelActions);
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().setPlayListChanged(playPanelActions);
        LoginActions loginActions = new LoginActions();
        loginMainFrame.getLoginMainPanel().setLoginPanelListener(loginActions);
        loginActions.setUserLoginListener(mainFrame.getMainPanel().getCentrePanel());
        playPanelActions.setPlayingMusicChanged(mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable());
        mainFrame.getMainPanel().getWestPanel().setAddNewPlaylistListener(topLeftMenuActions);
        topLeftMenuActions.setAddLibrariesListener(mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().getMenuForMusics());
        AddSongAction addSongAction = new AddSongAction();
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().getMenuForMusics().setAddSongToLibrary(addSongAction);
        playPanelActions.setLoadPlayingPanel(mainFrame.getMainPanel().getPlayPanel().getSongPanelForPlayPanel());
        playPanelActions.setjSliderListener(mainFrame.getMainPanel().getPlayPanel().getLightSliderUI());
        JSliderActions.getInstance().setMusicFinishedListener(playPanelActions);
        mainFrame.getMainPanel().getPlayPanel().getLightSliderUI().setJsliderValueChanged(playPanelActions);
        playPanelActions.setResetPlaystate(mainFrame.getMainPanel().getPlayPanel());
        mainFrame.getMainPanel().getCentrePanel().getSongsMainPanel().getSongsTablePanel().getSongsTable().setGetPlayingMusic(playPanelActions);

    }

    public static int getStatus()
    {
        return status;
    }

    public static void setStatus(int status)
    {
        Main.status = status;
    }

    public static MainFrame getMainFrame()
    {
        return mainFrame;
    }

    public static Vector<Library> getPlayLists()
    {
        return playLists;
    }
}
