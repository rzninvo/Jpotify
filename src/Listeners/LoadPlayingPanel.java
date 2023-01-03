package Listeners;

import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public interface LoadPlayingPanel
{
    void addMusicToActivity(Music music) throws InvalidDataException, IOException, UnsupportedTagException;
}
