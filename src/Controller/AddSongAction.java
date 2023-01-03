package Controller;

import Listeners.AddSongToLibrary;
import Model.Library;
import Model.Music;
import View.PlayListForWestPanel;
import View.WestPanel;

import javax.swing.*;

public class AddSongAction implements AddSongToLibrary
{
    @Override
    public void addSongToLib(Music music, String name)
    {
        Library temp = Main.playLists.get(Main.playLists.indexOf(new Library(name)));
        System.out.println(music.getName());
        System.out.println(music.getArtist());
        music = Main.playlist.get(Main.playlist.indexOf(music));
        System.out.println(music.getName());
        if (temp.equals(Main.sharedPlaylist))
            Main.sharedPlaylist.addMusic(music);
        else if (temp.equals(Main.favourites))
            Main.favourites.addMusic(music);
        else
            temp.addMusic(music);
        temp.savePlaylist();
    }
}
