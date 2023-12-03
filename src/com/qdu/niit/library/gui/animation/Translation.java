package com.qdu.niit.library.gui.animation;

import javax.swing.*;
import java.util.ArrayDeque;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description Translation组件为JPanel的子类，将LinkedList中的组件，随时间在Translation组件内的区域内平移,
 * 并将已离开屏幕的组件移至队尾，以达到循环显示的效果。LinkedList中的组件将会填充Translation组件的范围。<br>
 * LinkedList的类型变量应为JComponent类型，元素为JComponent或其子类对象。<br>
 * 可以利用传入新的timer重写动画行为
 * @date 2023/11/15
 */

@SuppressWarnings({"unused"})
public class Translation extends JPanel {
    private final ArrayDeque<ProcessSave> taskQueue = new ArrayDeque<>();
    public static final int CONSTANT_SPEED = 1;
    private static int Translation_Style = CONSTANT_SPEED;
    public static final int TO_LEFT = 1;
    public static final int TO_RIGHT = 2;
    public static final int TO_UPPER = 3;
    public static final int TO_BELOW = 4;
    private int direction = TO_LEFT;
    private JComponent targetComponent;
    private int intervalTime;
    private int intervalDistance;
    private int maxMoveDistance;
    private int width;
    private int height;
    private int x;
    private int y;
    private Timer timer;
    private int moveDistance = 0;
    private boolean isStart = false;

    public void rootPanelInitial() {
        this.setLayout(null);
        //把根面板的背景设为不显示，否则在根面板存在背景色时，会在最右侧显示一条与背景色颜色相同的线，且无法通过调整移动组件的宽度解决
        //初步判断可能与组件的坐标是从0开始，左闭右开的，但通过增大子组件坐标无法解决问题。
        //怀疑与Swing面板的实现有关，暂且通过不显示背景解决。
        this.setOpaque(false);
    }

    private void initial() {
        switch (direction) {
            case TO_LEFT -> timer = new Timer(0, e -> translationToLeft());
            case TO_RIGHT -> timer = new Timer(0, e -> translationToRight());
            case TO_UPPER -> timer = new Timer(0, e -> translationToUpper());
            case TO_BELOW -> timer = new Timer(0, e -> translationToBelow());
        }
        timer.setDelay(intervalTime);
        width = this.getWidth();
        height = this.getHeight();
        x = this.getX();
        y = this.getY();
        targetComponent.setBounds(0, 0, width, height);
        this.add(targetComponent);
        moveDistance = 0;
    }

    /**
     * @param targetComponent  要被平移的组件
     * @param intervalTime     平移间隔时间
     * @param intervalDistance 一次平移的距离
     * @param maxMoveDistance  平移最大距离
     * @param direction        平移的组件向哪个方向平移
     */
    public Translation(JComponent targetComponent, int intervalTime, int intervalDistance, int maxMoveDistance, int direction) {
        this.targetComponent = targetComponent;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.maxMoveDistance = maxMoveDistance;
        this.direction = direction;
        rootPanelInitial();
    }

    public Translation(JComponent targetComponent, int intervalTime, int intervalDistance, int maxMoveDistance) {
        this.targetComponent = targetComponent;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.maxMoveDistance = maxMoveDistance;
        rootPanelInitial();
    }

    private void translationToLeft() {
        if (moveDistance <= maxMoveDistance) {
            moveDistance += intervalDistance;
            this.setLocation(x - moveDistance, y);
            this.repaint();
        } else {
            this.stop();
            this.repaint();
            if (taskQueue.size() > 0) {
                setDirection(taskQueue.pop().direction);
                initial();
                this.start();
            }
        }
    }

    private void translationToRight() {
        if (moveDistance <= maxMoveDistance) {
            moveDistance += intervalDistance;
            this.setLocation(x + moveDistance, y);
            this.repaint();
        } else {
            this.stop();
            this.repaint();
            if (taskQueue.size() > 0) {
                setDirection(taskQueue.pop().direction);
                initial();
                this.start();
            }
        }
    }

    private void translationToUpper() {
        if (moveDistance <= maxMoveDistance) {
            moveDistance += intervalDistance;
            this.setLocation(x, y - moveDistance);
            this.repaint();
        } else {
            this.stop();
            this.repaint();
            if (taskQueue.size() > 0) {
                setDirection(taskQueue.pop().direction);
                initial();
                this.start();
            }
        }
    }

    private void translationToBelow() {
        if (moveDistance <= maxMoveDistance) {
            moveDistance += intervalDistance;
            this.setLocation(x, y + moveDistance);
            this.repaint();
        } else {
            this.stop();
            this.repaint();
            if (taskQueue.size() > 0) {
                setDirection(taskQueue.pop().direction);
                initial();
                this.start();
            }
        }
    }

    /**
     * 重写setBounds方法，用于在Translation组件的位置设定后，立刻设定待移动组件的位置和大小<br>
     * 避免在构造函数中初始化待移动组件导致获取到的Translation的长和宽为0。
     *
     * @param x      the new <i>x</i>-coordinate of this component
     * @param y      the new <i>y</i>-coordinate of this component
     * @param width  the new {@code width} of this component
     * @param height the new {@code height} of this
     *               component
     */
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (!isStart) {
            initial();
        }
    }

    public void start() {
        initial();
        isStart = true;
        timer.start();
    }

    public void waitOrStart(int direction) {
        if (isStart) {
            taskQueue.add(new ProcessSave(direction));
        }
        else{
            this.setDirection(direction);
            start();
        }
    }

    public void restart() {
        initial();
        isStart = true;
        timer.restart();
    }

    public void stop() {
        isStart = false;
        timer.stop();
    }

    public JComponent getTargetComponent() {
        return targetComponent;
    }

    public void setTargetComponent(JComponent targetComponent) {
        this.targetComponent = targetComponent;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getIntervalDistance() {
        return intervalDistance;
    }

    public void setIntervalDistance(int intervalDistance) {
        this.intervalDistance = intervalDistance;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if (!isStart) {
            this.direction = direction;
            initial();
        }
    }

    public int getMaxMoveDistance() {
        return maxMoveDistance;
    }

    public void setMaxMoveDistance(int maxMoveDistance) {
        this.maxMoveDistance = maxMoveDistance;
    }

    public boolean isStart() {
        return isStart;
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * 可以利用传入新的timer重写动画行为
     *
     * @param timer 新的Timer对象，暂时不可用
     */
    public void setTimer(Timer timer) {
        if (!isStart) {
            this.timer = timer;
        }
    }

    public static int getTranslation_Style() {
        return Translation_Style;
    }

    public static void setTranslation_Style(int translation_Style) {
        Translation_Style = translation_Style;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "direction=" + direction +
                ", targetComponent=" + targetComponent +
                ", intervalTime=" + intervalTime +
                ", intervalDistance=" + intervalDistance +
                ", maxMoveDistance=" + maxMoveDistance +
                ", width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                ", timer=" + timer +
                ", moveDistance=" + moveDistance +
                ", isStart=" + isStart +
                '}';
    }

    private static class ProcessSave {
        private int direction;

        public ProcessSave(int direction) {
            this.direction = direction;
        }
    }
}