package Listeners;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.IOException;

public interface SongsTableButtons
{
    void doAction(int col,String name,String artist) throws InterruptedException, UnsupportedTagException, TagException, InvalidDataException, IOException;
}
