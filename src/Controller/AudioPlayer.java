package Controller;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class AudioPlayer implements Runnable {
    private int value;
    private int totalFrame;
    private String path;
    public AdvancedPlayer player;
    Thread t;
    public AudioPlayer(String path,int totalFrame) {
        this.path = path;
        this.totalFrame = totalFrame;
        value = 0;
    }

    public void playMusic(Thread t){
        this.t = t;
        t.start();
    }
    public  void pauseMusic(){
        t.suspend();
    }
    public void resume(){
        t.resume();
    }
    public void run() {
        FileInputStream FIS ;
        try {
            FIS = new FileInputStream(path);
            player = new AdvancedPlayer(FIS);
            player.play(value,totalFrame);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


    }
    public void setPlayLocation(int value){
        this.value = value;
    }
    public void stop(){
        t.stop();
    }

    public String getPath()
    {
        return path;
    }
}
