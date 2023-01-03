package Controller;

import Model.ID3v1;
import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LoadingLibrary
{
    Vector<Music> loadFilesFromFolders(String path) throws IOException, ClassNotFoundException, TagException
    {
        ArrayList<String> audios = new ArrayList<>();
        Vector<Music> musics = new Vector<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                String[] audio = listOfFiles[i].getAbsolutePath().split("\\.");
                if (audio.length != 0 && audio[audio.length - 1].equals("mp3"))
                {
//                    System.out.println(listOfFiles[i].getAbsolutePath());
                    audios.add(listOfFiles[i].getAbsolutePath());
                }
            }
        }
        for (String directory : audios)
        {
            Music music = this.processFile(directory);
            if (music != null && music.getAddDate() == null)
                music.setAddDate(LocalDateTime.now());
            if (music != null && !musics.contains(music))
                musics.add(music);
        }
        return musics;
    }


    private final static String songLyricsURL = "http://www.songlyrics.com";


    private List<String> getSongLyrics(String band, String songTitle) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect(songLyricsURL + "/" + band.replace(" ", "-").toLowerCase() + "/" + songTitle.replace(" ", "-").toLowerCase() + "-lyrics/");

        //specify user agent
        connection.userAgent("Mozilla/5.0");

        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document doc = connection.get();
        Elements elements = doc.select("p.songLyricsV14");
        Element p;
        if (elements.size() > 0)
        {
            p = elements.get(0);
        }
        else
            throw new IOException("null");
        for (Node e : p.childNodes())
        {
            if (e instanceof TextNode)
            {
                lyrics.add(((TextNode) e).getWholeText());
            }
        }
        return lyrics;
    }

    private List<String> getLyrics(String singer, String song) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect("https://genius.com/" + singer.substring(0, 1).toUpperCase() + singer.replace(" ", "-").substring(1).toLowerCase() + "-" + song.replace(" ", "-").toLowerCase() + "-lyrics");

        //specify user agent
        connection.userAgent("Mozilla/5.0");
        connection.header("Host", "genius.com");
        connection.timeout(1000);
        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document lyricPage = connection.get();
        Elements lyricTags = lyricPage.select("div.lyrics > p > a");
        for (int i = 0; i < lyricTags.size(); i++)
        {
            for (Node e : lyricTags.get(i).childNodes())
            {
                if (e instanceof TextNode)
                {
                    lyrics.add(((TextNode) e).getWholeText());
                }
            }
        }
        return lyrics;
    }

    private List<String> getPersianSongLyrics(String band, String songTitle) throws IOException
    {
        Connection connection;
        //connect to the website
        connection = Jsoup.connect("https://www.texahang.org/متن-آهنگ-" + band.replace(" ", "-") + "-بنام-" + songTitle.replace(" ", "-") + "/");
        //specify user agent
        connection.userAgent("Mozilla/5.0");
        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document doc = connection.get();
        Elements lyricTags = doc.select("div.main-post > p");
        for (int i = 0; i < lyricTags.size(); i++)
        {
            for (Node e : lyricTags.get(i).childNodes())
            {
                if (e instanceof TextNode)
                {
                    lyrics.add(((TextNode) e).getWholeText());
                }
            }
        }
        return lyrics;
    }

    private List<String> getPersianLyrics(String band, String songTitle) throws IOException
    {
        Connection connection;

        //connect to the website
        connection = Jsoup.connect("https://nex1music.ir/آهنگ-" + band.replace(" ", "-") + "-" + songTitle.replace(" ", "-") + "/");

        //specify user agent
        connection.userAgent("Mozilla/5.0");

        //get the HTML document
        List<String> lyrics = new ArrayList<>();
        Document doc = connection.get();
        Elements elements = doc.select("div.lyrics");
        Element p;
        if (elements.size() > 0)
        {
            p = elements.get(0);
        }
        else
            throw new IOException("null");
//        for (Node e : p.childNodes())
//        {
//            if (e instanceof TextNode)
//            {
//                lyrics.add(((TextNode) e).getWholeText());
//            }
//        }
        lyrics.add(p.text());
        return lyrics;
    }

    private static int counter = 0;

    private List<String> findLyrics(String singer, String song)
    {
        List<String> lyrics = null;
        try
        {
            if (counter < 4)
            {
                lyrics = this.getSongLyrics(singer, song);
            }
            else if (counter < 7)
            {
                try
                {
                    lyrics = this.getPersianSongLyrics(singer, song);
                }
                catch (IOException e)
                {
                    try
                    {
                        lyrics = this.getPersianLyrics(singer, song);
                    }
                    catch (IOException ex)
                    {
                        counter++;
                        lyrics = this.findLyrics(singer, song);
                    }
                }
            }
        }
        catch (IOException e)
        {
            counter++;
            try
            {
                lyrics = this.getLyrics(singer, song);
            }
            catch (IOException ex)
            {
                lyrics = this.findLyrics(singer, song);
            }
        }
        return lyrics;
    }

    private void restCounter()
    {
        counter = 0;
    }

    public List<String> gatherMusicLyrics(String singer, String song) throws IOException
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<String> lyrics = null;
        String directory = "./Lyrics/" + song.replace(" ", "_").toLowerCase() + "_" + singer.replace(" ", "_").toLowerCase() + ".txt";
        try
        {
            fileReader = new FileReader(directory);
            bufferedReader = new BufferedReader(fileReader);
            lyrics = new ArrayList<>();
            String temp = bufferedReader.readLine();
            while (temp != null)
            {
                lyrics.add(temp);
                temp = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            lyrics = this.findLyrics(singer, song);
            this.restCounter();
            if (lyrics != null)
            {
                FileWriter fileWriter = new FileWriter(directory);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (String str : lyrics)
                {
                    printWriter.println(str);
                }
                printWriter.close();
                fileWriter.close();
            }
            else
            {
                lyrics = new ArrayList<>();
                lyrics.add("Please check Your Connection and try again");
            }
        }
        catch (EOFException e)
        {
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lyrics;
    }

    private String normalized(String str)
    {
//        String normalized = str.replaceAll("[^a-zA-Z0-9 ._-]", "");
//        normalized = String.format("%s" + File.separator, str);
        return str;
    }

    Music processFile(String directory) throws IOException, TagException
    {
        File file = new File(directory);
        MP3File mp3File = new MP3File(file);
        String title = null, artist = null, album = null, year = null, genre = null;
        /* extra point
        BufferedInputStream bufferedReader = new BufferedInputStream(new FileInputStream(file));
        int len = (int) (file.length()-128);
        byte[] bytes = new byte[len];
        bufferedReader.read(bytes);
        bytes = new byte[3];
        bufferedReader.read(bytes);
        bytes = new byte[30];
        bufferedReader.read(bytes);
        title = new String(bytes);
        bytes = new byte[30];
        bufferedReader.read(bytes);
        artist = new String(bytes);
        bytes = new byte[30];
        bufferedReader.read(bytes);
        album = new String(bytes);
        bytes = new byte[4];
        bufferedReader.read(bytes);
        year = new String(bytes);
        bytes = new byte[30];
        bufferedReader.read(bytes);
        String comment = new String(bytes);
        byte[] zeroByte = new byte[1];
        bufferedReader.read(zeroByte);
        album = new String(bytes);
        byte[] track = new byte[1];
        bufferedReader.read(track);
        bytes = new byte[1];
        bufferedReader.read(bytes);
        genre = new String(bytes);
        System.out.println(genre);
        bufferedReader.close();
         */

        if (mp3File.getID3v1Tag() != null)
        {
            title = normalized(mp3File.getID3v1Tag().getTitle());
        }
        else if (mp3File.getID3v2Tag().getSongTitle() != null && !mp3File.getID3v2Tag().getSongTitle().trim().equals(""))
        {
            title = normalized(mp3File.getID3v2Tag().getSongTitle());
        }
        else return null;

        if (mp3File.getID3v1Tag() != null)
        {
            artist = normalized(mp3File.getID3v1Tag().getArtist());
        }
        else if (mp3File.getID3v2Tag().getLeadArtist() != null && !mp3File.getID3v2Tag().getLeadArtist().trim().equals(""))
        {
            artist = normalized(mp3File.getID3v2Tag().getLeadArtist());
        }
        else return null;

        if (mp3File.getID3v1Tag() != null)
        {
            album = normalized(mp3File.getID3v1Tag().getAlbum());
        }
        else if (mp3File.getID3v2Tag().getAlbumTitle() != null && !mp3File.getID3v2Tag().getAlbumTitle().trim().equals(""))
        {
            album = normalized(mp3File.getID3v2Tag().getAlbumTitle());
        }
        if (mp3File.getID3v2Tag().getYearReleased() != null && !mp3File.getID3v2Tag().getYearReleased().trim().equals(""))
        {
            year = normalized(mp3File.getID3v2Tag().getYearReleased());
        }
        else if (mp3File.getID3v1Tag() != null)
        {
            year = normalized(mp3File.getID3v1Tag().getYear());
        }
        if (mp3File.getID3v2Tag().getSongTitle() != null && !mp3File.getID3v2Tag().getSongTitle().trim().equals(""))
        {
            genre = normalized(mp3File.getID3v2Tag().getSongGenre());
        }
        else if (mp3File.getID3v1Tag() != null)
        {
            genre = normalized(new ID3v1().getGENRES(mp3File.getID3v1Tag().getGenre()));
        }
        return new Music(directory, artist, title, year, LocalDateTime.now(), null, genre, album);
    }

    public static String[][] generateTable(Vector<Music> musics) throws InvalidDataException, IOException, UnsupportedTagException
    {
        String[][] data = new String[musics.size()][8];
        int counter = 0;
        Vector<Music> musicVector = new Vector<>();
        for (Music music : musics)
        {
            musicVector.add(music);
            Mp3File mp3File = new Mp3File(music.getFileLocation());
            data[counter][0] = "▶";
            data[counter][1] = "✓";
            data[counter][2] = music.getName();
            data[counter][3] = music.getArtist();
            data[counter][4] = music.getAlbum();
            data[counter][5] = String.valueOf(music.getAddDate());
            data[counter][6] = "● ● ●";
            data[counter][7] = (mp3File.getLengthInSeconds() / 60) + ":" + (mp3File.getLengthInSeconds() - mp3File.getLengthInSeconds() / 60 * 60);
            if (data[counter][7].length() == 3)
                data[counter][7] += "0";
            counter++;
        }
        if (musicVector.size() > 0)
            Main.playlist = musicVector;
        return data;
    }
}

