package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BackgroundComponentDragger implements MouseMotionListener {

    private Component controlledComponent;

    private Point originPoint;

    public BackgroundComponentDragger(Component component) {

        this.controlledComponent = component;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        boolean fullScreenMode;
        if (controlledComponent instanceof  MainFrame)
            fullScreenMode = ((MainFrame)controlledComponent).getFullScreenMode();
        else
            fullScreenMode = ((LoginMainFrame)controlledComponent).getFullScreenMode();
        if(!fullScreenMode)
        {
            Point currentFramePosition = controlledComponent.getLocation();
            Point newFramePosition = new Point(currentFramePosition.x + e.getX()
                    - originPoint.x, currentFramePosition.y + e.getY() - originPoint.y);
            controlledComponent.setLocation(newFramePosition);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boolean fullScreenMode;
        if (controlledComponent instanceof  MainFrame)
            fullScreenMode = ((MainFrame)controlledComponent).getFullScreenMode();
        else
            fullScreenMode = ((LoginMainFrame)controlledComponent).getFullScreenMode();
        if(!fullScreenMode) {
            originPoint = e.getPoint();
        }
    }
}