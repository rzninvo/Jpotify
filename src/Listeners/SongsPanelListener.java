package Listeners;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.IOException;

public interface SongsPanelListener
{
    void playMusic(boolean isPaused,String name,String artist) throws InterruptedException, UnsupportedTagException, TagException, InvalidDataException, IOException;
}
