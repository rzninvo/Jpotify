package Model;

import java.util.Vector;

public class Albums
{
    private Vector<Library> albums = new Vector<>();
    private Vector<Music> musics;

    public Albums(Vector<Music> musics)
    {
        this.musics = musics;
    }

    public void loadAlbums()
    {
        try
        {
            for (Music music : musics)
            {
                Library temp = new Library(music.getAlbum());
                temp.addMusic(music);
                if (albums.size() != 0 && albums.contains(temp) && !albums.get(albums.indexOf(temp)).getMusics().contains(music))
                    albums.get(albums.indexOf(temp)).addMusic(music);
                else
                    albums.add(temp);
            }
        }
        catch (Exception e)
        {

        }
    }

    public Vector<Library> getAlbums()
    {
        return albums;
    }
}
