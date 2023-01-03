package Listeners;

import Model.Music;
import Model.User;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public interface AddPlayingMusic
{
    void addMusicToActivity(Music music, User user) throws InvalidDataException, IOException, UnsupportedTagException;
}
