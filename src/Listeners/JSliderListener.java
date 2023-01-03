package Listeners;

import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.IOException;

public interface JSliderListener
{
    void playPause(Music music,int state) throws IOException, TagException, InterruptedException, InvalidDataException, UnsupportedTagException;
}
