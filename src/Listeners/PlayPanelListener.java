package Listeners;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.IOException;

public interface PlayPanelListener
{
    void state(boolean shuffleState,int repeatState,int playState, int plPaSk) throws InterruptedException, UnsupportedTagException, InvalidDataException, TagException, IOException;
}
