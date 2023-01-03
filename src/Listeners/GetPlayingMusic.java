package Listeners;

import Model.Music;

import java.io.IOException;

public interface GetPlayingMusic
{
    Music getPlayingMusic() throws IOException;
}
