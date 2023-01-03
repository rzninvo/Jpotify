package Listeners;

import Model.Music;

import java.util.Vector;

public interface PlayListChanged
{
    void setPlaylist(Vector<Music> musics);
}
