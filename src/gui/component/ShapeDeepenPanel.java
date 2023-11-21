package gui.component;

import javax.swing.*;

/**
 * @author 李冠良
 * @program Chat
 * @description
 * @date
 */
public class ShapeDeepenPanel extends JPanel {
    private boolean hasBeenTrigger = false;
    private float opacity = 0.3f;
    private float beDeepenMaxValue = 0.7f;
    private float beShallowMinValue = 0.3f;
    private Timer timerEntered = new Timer(10, e -> {
        hasBeenTrigger = true;
        opacity += 0.04f;
        this.repaint();
        if (opacity > 0.7f) {
            ((Timer) e.getSource()).stop();
        }
    });
    private Timer timerExited = new Timer(10, e -> {
        opacity -= 0.04f;
        this.repaint();
        if (opacity < 0.3f) {
            ((Timer) e.getSource()).stop();
        }
    });

    public boolean setInitialOpacity(float initialOpacity) {
        if (!hasBeenTrigger) {
            opacity = initialOpacity;
            return true;
        }
        return false;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public float getBeDeepenMaxValue() {
        return beDeepenMaxValue;
    }

    public void setBeDeepenMaxValue(float beDeepenMaxValue) {
        this.beDeepenMaxValue = beDeepenMaxValue;
    }

    public float getBeShallowMinValue() {
        return beShallowMinValue;
    }

    public void setBeShallowMinValue(float beShallowMinValue) {
        this.beShallowMinValue = beShallowMinValue;
    }

    public Timer getTimerEntered() {
        return timerEntered;
    }

    public Timer getTimerExited() {
        return timerExited;
    }

}