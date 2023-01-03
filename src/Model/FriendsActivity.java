package Model;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class FriendsActivity
{
    private BufferedImage musicImage;
    private String user;
    private String musicTitle;
    private String musicArtist;
    private String musicAlbum;

    public FriendsActivity(Music music,String user) throws IOException, InvalidDataException, UnsupportedTagException
    {
        this.user=user;
        musicTitle=music.getName();
        musicAlbum=music.getAlbum();
        musicArtist=music.getArtist();
        Mp3File song = new Mp3File(music.getFileLocation());
        if (song.hasId3v2Tag()){
            ID3v2 id3v2tag = song.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            //converting the bytes to an image
            try
            {
                musicImage = ImageIO.read(new ByteArrayInputStream(imageData));
            }
            catch (Exception e)
            {
                File imageFile = new File("./src/View/Icons/no_artwork.png");
                BufferedImage image = ImageIO.read(imageFile);
                musicImage = image;
            }
        }
//        musicImage = new ImageIcon(ImageIO.read(new ByteArrayInputStream(new MP3File(music.getFileLocation()).getID3v2Tag().get)).getScaledInstance((80, 80, Image.SCALE_SMOOTH)));
    }

    public BufferedImage getMusicImage()
    {
        return musicImage;
    }

    public String getUser()
    {
        return user;
    }

    public String getMusicTitle()
    {
        return musicTitle;
    }

    public String getMusicArtist()
    {
        return musicArtist;
    }

    public String getMusicAlbum()
    {
        return musicAlbum;
    }
}
