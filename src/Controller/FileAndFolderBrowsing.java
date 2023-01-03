package Controller;

import Model.Library;
import Model.Music;
import org.farng.mp3.TagException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

public class FileAndFolderBrowsing
{
    private LoadingLibrary loadingLibrary = new LoadingLibrary();
    private static final String directory = "./Library/songs.bin";
    private static final String pathsDirectory = "./Library/Paths.bin";
    private ObjectInputStream objectInputStream;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    public void saveMusics(Vector<Music> songs)
    {
        try
        {
            fileOutputStream = new FileOutputStream(directory);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Music music : songs)
            {
                if (music.getLastPlayed() == null)
                    music.setLastPlayed(LocalDateTime.MIN);
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

    public void addFileFolder(String path, Vector<Music> songs) throws IOException, ClassNotFoundException, TagException
    {
        ArrayList<String> paths = new ArrayList<>();
        if (!(new File(path)).isFile())
        {
            Vector<Music> tempMusics = loadingLibrary.loadFilesFromFolders(path);
            for (Music temp : tempMusics)
                if (!songs.contains(temp))
                    songs.add(temp);
            paths.add(path);
        }
        else
        {
            Music temp = loadingLibrary.processFile(path);
            if (!songs.contains(temp) && temp!=null)
                songs.add(temp);
        }
        for (Music music : songs)
        {
            if (music.getAddDate() == null)
                music.setAddDate(LocalDateTime.now());
        }
        try
        {
            if (new File(pathsDirectory).exists())
            {
                fileInputStream = new FileInputStream(pathsDirectory);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (true)
                {
                    String tempPath = (String) objectInputStream.readObject();
                    paths.add(tempPath);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (objectInputStream != null)
                objectInputStream.close();
            if (fileInputStream != null)
                fileInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            fileOutputStream = new FileOutputStream(pathsDirectory);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (String pth : paths)
            {
                objectOutputStream.writeObject(pth);
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
        saveMusics(songs);
    }

    public void loadFiles(Vector<Music> songs) throws IOException
    {
        try
        {
            if (new File(directory).exists())
            {
                fileInputStream = new FileInputStream(directory);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (true)
                {
                    Music temp = (Music) objectInputStream.readObject();
                    if (new File(temp.getFileLocation()).exists())
                    {
                        if (temp.getAddDate()==null)
                            temp.setAddDate(LocalDateTime.now());
                        if (temp.getLastPlayed()==null)
                            temp.setLastPlayed(LocalDateTime.MIN);
                        songs.add(temp);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (new File(pathsDirectory).exists())
            {
                fileInputStream = new FileInputStream(pathsDirectory);
                objectInputStream = new ObjectInputStream(fileInputStream);
                while (true)
                {
                    String path = (String) objectInputStream.readObject();
                    Vector<Music> tempMusics = loadingLibrary.loadFilesFromFolders(path);
                    for (Music music : tempMusics)
                        if (!songs.contains(music))
                        {
                            if (music.getAddDate() == null)
                                music.setAddDate(LocalDateTime.now());
                            if (music.getLastPlayed() == null)
                                music.setLastPlayed(LocalDateTime.MIN);
                            songs.add(music);
                        }
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (EOFException e)
        {

        }
        catch (TagException e)
        {
            e.printStackTrace();
        }
//        System.out.println(songs.size());
    }

    public Vector<Library> loadLibraries() throws IOException
    {
        Vector<Library> libraries = new Vector<>();
        Library library = null;
        Vector<Music> songs = null;
        File folder = new File("./Library/");
        File[] listOfFiles = folder.listFiles();
//        System.out.println(listOfFiles.length);
        if (listOfFiles.length > 0)
            for (File file : listOfFiles)
            {
                if (!file.getName().equals("songs.bin") && !file.getName().equals("Paths.bin"))
                    try
                    {
                        library = new Library(file.getName().replace(".bin", "").substring(0,1).toUpperCase()+file.getName().replace(".bin", "").substring(1));
                        songs = new Vector<>();
                        fileInputStream = new FileInputStream(file);
                        objectInputStream = new ObjectInputStream(fileInputStream);
                        while (true)
                        {
                            songs.add((Music) objectInputStream.readObject());
                            System.out.println(songs);
                        }
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (EOFException e)
                    {
                        if (library != null && songs != null)
                        {
                            library.addMusics(songs);
                            libraries.add(library);
                        }
                        try
                        {
                            objectInputStream.close();
                            fileInputStream.close();
                        }
                        catch (Exception ex)
                        {

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }

            }
        return libraries;
    }
}
