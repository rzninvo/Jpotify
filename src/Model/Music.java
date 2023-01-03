package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Music implements Serializable
{
    private String fileLocation;
    private String artist;
    private String name;
    private String year;
    private String genre;
    private String album;
    private LocalDateTime addDate;
    private LocalDateTime lastPlayed;


    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public Music(String fileLocation, String artist, String name, String year, LocalDateTime addDate, LocalDateTime lastPlayed, String genre, String album)
    {
        this.album = album;
        this.genre = genre;
        this.fileLocation = fileLocation;
        this.artist = artist;
        this.name = name;
        this.addDate = addDate;
        this.lastPlayed = lastPlayed;
        this.year = year;
    }

    public LocalDateTime getAddDate()
    {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate)
    {
        this.addDate = addDate;
    }

    public LocalDateTime getLastPlayed()
    {
        return lastPlayed;
    }

    public void setLastPlayed(LocalDateTime lastPlayed)
    {
        this.lastPlayed = lastPlayed;
    }

    public String getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation)
    {
        this.fileLocation = fileLocation;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Music && ((Music) obj).getName() != null && ((Music) obj).getArtist() != null && ((Music) this).getArtist() != null && ((Music) this).getName() != null && ((Music) obj).getName().equals(this.getName()) && ((Music) obj).getArtist().equals(this.getArtist()))
            return true;
        return false;
    }
}
