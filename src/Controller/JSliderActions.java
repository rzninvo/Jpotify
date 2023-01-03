package Controller;

import Listeners.MusicFinishedListener;
import View.LightSliderUI;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import javax.swing.*;
import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JSliderActions
{
    private int value = 0;
    private int min = 0;
    private int max;
    private JSlider jSlider = null;
    private int playState = -1;
    private MusicFinishedListener musicFinishedListener = null;

    public void setLightSliderUI(JSlider jSlider)
    {
        this.jSlider = jSlider;
    }

    private static JSliderActions jSliderActions = null;

    public static JSliderActions getInstance()
    {
        if (jSliderActions == null)
            jSliderActions = new JSliderActions();
        return jSliderActions;
    }

    private JSliderActions()
    {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                if (playState == 1 || playState == 0)
                {
                    value++;
                    jSlider.setValue(value);
                    if (value == jSlider.getMaximum())
                    {
                        try
                        {
                            musicFinishedListener.doRepeat();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        catch (UnsupportedTagException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InvalidDataException e)
                        {
                            e.printStackTrace();
                        }
                        catch (TagException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    public void setPlayState(int playState)
    {
        this.playState = playState;
        if (playState == 0 || playState == 4)
        {
            value = 0;
            jSlider.setValue(value);
        }
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public int getMin()
    {
        return min;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public int getMax()
    {
        return max;
    }

    public void setMax(int max)
    {
        this.max = max;
        jSlider.setMaximum(max);
    }

    public void setMusicFinishedListener(MusicFinishedListener musicFinishedListener)
    {
        this.musicFinishedListener = musicFinishedListener;
    }
}
