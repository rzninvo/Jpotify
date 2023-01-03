package View;

import Controller.JSliderActions;
import Listeners.JSliderListener;
import Listeners.JsliderValueChanged;
import Model.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LightSliderUI extends BasicSliderUI implements JSliderListener
{

    public Color rangeColor = Color.white;
    private final BasicStroke stroke = new BasicStroke(2f);
    private JSliderActions jSliderActions = JSliderActions.getInstance();
    private transient boolean upperDragging;
    private JsliderValueChanged jsliderValueChanged = null;

    public LightSliderUI(JSlider b)
    {
        super(b);
        jSliderActions.setLightSliderUI(b);
        b.setValue(0);
//        b.addChangeListener(new ChangeListener()
//        {
//            @Override
//            public void stateChanged(ChangeEvent e)
//            {
//                JSlider source = (JSlider)e.getSource();
//                if (!source.get) {
//                }
//            }
//        });
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new LightSliderUI((JSlider) c);
    }


    @Override
    protected void calculateThumbSize()
    {
        super.calculateThumbSize();
        thumbRect.setSize(thumbRect.width, thumbRect.height);
    }

    /**
     * Creates a listener to handle track events in the specified slider.
     */
    @Override
    protected TrackListener createTrackListener(JSlider slider)
    {
        return new RangeTrackListener(this);
    }


    @Override
    protected void calculateThumbLocation()
    {
        // Call superclass method for lower thumb location.
        super.calculateThumbLocation();

        // Adjust upper value to snap to ticks if necessary.
        if (slider.getSnapToTicks())
        {
            int upperValue = slider.getValue() + slider.getExtent();
            int snappedValue = upperValue;
            int majorTickSpacing = slider.getMajorTickSpacing();
            int minorTickSpacing = slider.getMinorTickSpacing();
            int tickSpacing = 0;

            if (minorTickSpacing > 0)
            {
                tickSpacing = minorTickSpacing;
            }
            else if (majorTickSpacing > 0)
            {
                tickSpacing = majorTickSpacing;
            }

            if (tickSpacing != 0)
            {
                // If it's not on a tick, change the value
                if ((upperValue - slider.getMinimum()) % tickSpacing != 0)
                {
                    float temp = (float) (upperValue - slider.getMinimum()) / (float) tickSpacing;
                    int whichTick = Math.round(temp);
                    snappedValue = slider.getMinimum() + (whichTick * tickSpacing);
                }

                if (snappedValue != upperValue)
                {
                    slider.setExtent(snappedValue - slider.getValue());
                }
            }
        }

        // Calculate upper thumb location.  The thumb is centered over its
        // value on the track.
        if (slider.getOrientation() == JSlider.HORIZONTAL)
        {
            int upperPosition = xPositionForValue(slider.getValue() + slider.getExtent());
            thumbRect.x = upperPosition - (thumbRect.width / 2);
            thumbRect.y = trackRect.y;

        }
        else
        {
            int upperPosition = yPositionForValue(slider.getValue() + slider.getExtent());
            thumbRect.x = trackRect.x;
            thumbRect.y = upperPosition - (thumbRect.height / 2);
        }
        slider.repaint();
    }

    /**
     * Returns the size of a thumb.
     * Parent method not use size from LaF
     *
     * @return size of trumb
     */
    @Override
    protected Dimension getThumbSize()
    {
        return new Dimension(15, 15);
    }


    private Shape createThumbShape(int width, int height)
    {
        Ellipse2D shape = new Ellipse2D.Double(0, 0, width, height);
        return shape;
    }

    @Override
    public void paintTrack(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        g2d.setStroke(stroke);
        g2d.setPaint(Color.gray);
        Color oldColor = Color.gray;
        Rectangle trackBounds = trackRect;
        if (slider.getOrientation() == SwingConstants.HORIZONTAL)
        {
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2,
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
            int lowerX = thumbRect.width / 2;
            int upperX = thumbRect.x + (thumbRect.width / 2);
            int cy = (trackBounds.height / 2) - 2;
            g2d.translate(trackBounds.x, trackBounds.y + cy);
            g2d.setColor(rangeColor);
            g2d.drawLine(lowerX - trackBounds.x, 2, upperX - trackBounds.x, 2);
            g2d.translate(-trackBounds.x, -(trackBounds.y + cy));
            g2d.setColor(Color.gray);
        }
        g2d.setStroke(old);
    }


    /**
     * Overrides superclass method to do nothing.  Thumb painting is handled
     * within the <code>paint()</code> method.
     */
    @Override
    public void paintThumb(Graphics g)
    {
        Rectangle knobBounds = thumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;
        Graphics2D g2d = (Graphics2D) g.create();
        Shape thumbShape = createThumbShape(w - 1, h - 1);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(knobBounds.x, knobBounds.y);
        g2d.setColor(new Color(215, 215, 215, 255));
        g2d.fill(thumbShape);

        g2d.setColor(new Color(215, 215, 215, 255));
        g2d.draw(thumbShape);
        g2d.dispose();
    }

    @Override
    public void playPause(Music music, int state) throws IOException, TagException, InterruptedException, InvalidDataException, UnsupportedTagException
    {
        File file = null;
        if (state == 0)
        {
            file = new File(music.getFileLocation());
            jSliderActions.setMax((int) new Mp3File(file).getLengthInSeconds());
        }
        jSliderActions.setPlayState(state);
    }

    /**
     * Listener to handle model change events.  This calculates the thumb
     * locations and repaints the slider if the value change is not caused by dragging a thumb.
     */
    public class ChangeHandler implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent arg0)
        {
            calculateThumbLocation();
            slider.repaint();
        }
    }

    /**
     * Listener to handle mouse movements in the slider track.
     */

    public class RangeTrackListener extends TrackListener
    {

        LightSliderUI lightSliderUI;

        RangeTrackListener(LightSliderUI lightSliderUI)
        {
            this.lightSliderUI = lightSliderUI;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (!slider.isEnabled())
            {
                return;
            }
            currentMouseX -= thumbRect.width / 2; // Because we want the mouse location correspond to middle of the "thumb", not left side of it.
            rangeColor = Color.green;
            moveUpperThumb();
        }

        public void mousePressed(MouseEvent e)
        {
            if (!slider.isEnabled())
            {
                return;
            }

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (slider.isRequestFocusEnabled())
            {
                slider.requestFocus();
            }

            boolean upperPressed = false;
            if (slider.getMinimum() == slider.getValue())
            {
                if (thumbRect.contains(currentMouseX, currentMouseY))
                {
                    upperPressed = true;
                }
            }
            else
            {
                if (thumbRect.contains(currentMouseX, currentMouseY))
                {
                    upperPressed = true;
                }
            }

            if (upperPressed)
            {
                switch (slider.getOrientation())
                {
                    case JSlider.VERTICAL:
                        offset = currentMouseY - thumbRect.y;
                        break;
                    case JSlider.HORIZONTAL:
                        offset = currentMouseX - thumbRect.x;
                        break;
                }
                //upperThumbSelected = true;
                rangeColor = Color.green;
                upperDragging = true;
                return;
            }


            upperDragging = false;
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            upperDragging = false;
            slider.setValueIsAdjusting(false);
            rangeColor = Color.white;
            super.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (!slider.isEnabled())
            {
                return;
            }

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            if (upperDragging)
            {
                slider.setValueIsAdjusting(true);
                moveUpperThumb();

            }
        }

        @Override
        public boolean shouldScroll(int direction)
        {
            return false;
        }

        /**
         * Moves the location of the upper thumb, and sets its corresponding  value in the slider.
         */
        public void moveUpperThumb()
        {
            int thumbMiddle = 0;
            switch (slider.getOrientation())
            {

                case JSlider.HORIZONTAL:
                    int halfThumbWidth = thumbRect.width / 2;
                    int thumbLeft = currentMouseX - offset;
                    int trackLeft = trackRect.x;
                    int trackRight = trackRect.x + (trackRect.width - 1);
                    int hMax = xPositionForValue(slider.getMaximum() -
                            slider.getExtent());

                    if (drawInverted())
                    {
                        trackLeft = hMax;
                    }
                    else
                    {
                        trackRight = hMax;
                    }
                    thumbLeft = Math.max(thumbLeft, trackLeft - halfThumbWidth);
                    thumbLeft = Math.min(thumbLeft, trackRight - halfThumbWidth);

                    setThumbLocation(thumbLeft, thumbRect.y);//setThumbLocation

                    thumbMiddle = thumbLeft + halfThumbWidth;
                    slider.setValue(valueForXPosition(thumbMiddle));
                    JSliderActions.getInstance().setValue(slider.getValue());
                    jsliderValueChanged.setAudioPlayer(slider.getValue(),slider.getMaximum());
                    break;
                default:
                    return;
            }
        }
    }

    public void setJsliderValueChanged(JsliderValueChanged jsliderValueChanged)
    {
        this.jsliderValueChanged = jsliderValueChanged;
    }
}