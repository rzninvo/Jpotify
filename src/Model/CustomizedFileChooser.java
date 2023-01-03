package Model;

import Controller.FileAndFolderBrowsing;
import org.farng.mp3.TagException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class CustomizedFileChooser extends JFileChooser
{
    public CustomizedFileChooser()
    {
        this.setDialogTitle("Choose a directory or mp3 file to add");
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.setMultiSelectionEnabled(true);
        this.addChoosableFileFilter(new FileNameExtensionFilter("mp3 audio files", "mp3"));
    }
    public void writeFiles(Vector<Music> musics) throws TagException, IOException, ClassNotFoundException
    {
        FileAndFolderBrowsing fileAndFolderBrowsing = new FileAndFolderBrowsing();
        File[] files = this.getSelectedFiles();
        for(File file:files)
            fileAndFolderBrowsing.addFileFolder(file.getAbsolutePath(),musics);
    }
}
