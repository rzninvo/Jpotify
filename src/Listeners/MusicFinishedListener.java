package Listeners;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.IOException;

public interface MusicFinishedListener
{
    void doRepeat() throws InterruptedException, UnsupportedTagException, InvalidDataException, TagException, IOException;
}
