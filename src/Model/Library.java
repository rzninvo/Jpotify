package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Library implements Serializable
{
    private Vector<Music> musics = new Vector<>();
    private String name;

    public Library(String name)
    {
        this.name = name;
    }

    public void addMusic(Music music)
    {
        this.musics.add(music);
    }

    public void addMusics(Vector<Music> musics)
    {
        this.musics.addAll(musics);
    }

    public void savePlaylist()
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("./Library/"+name.toLowerCase().trim()+".bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Music music:musics)
            {
                objectOutputStream.writeObject(music);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void removeMusics(ArrayList<Music> musics)
    {
        try
        {
            for (Music music:musics)
            {
                this.musics.remove(music);
            }
        }
        catch (Exception e)
        {

        }
        savePlaylist();
    }

    public void suicide()
    {
        try
        {
            File file = new File("./Library/"+this.name+".bin");
            if (!name.equals("Favourites") && !name.equals("Shared playlist"))
                file.delete();
        }catch (Exception e)
        {

        }
    }
    public void rename(String newName)
    {
        suicide();
        name=newName;
        savePlaylist();
    }

    public Vector<Music> getMusics()
    {
        return musics;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Library && ((Library) obj).getName().equals(this.getName()))
            return true;
        return false;
    }

    public void setMusics(Vector<Music> musics)
    {
        this.musics = musics;
    }
}
