package com.qdu.niit.library.gui.animation;

import javax.swing.*;
import java.util.LinkedList;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description RollDisplay组件为JPanel的子类，将LinkedList中的组件，随时间在RollDisplay组件内的区域内平移,
 * 并将已离开屏幕的组件移至队尾，以达到循环显示的效果。LinkedList中的组件将会填充RollDisplay组件的范围。<br>
 * LinkedList的类型变量应为JComponent类型，元素为JComponent或其子类对象。<br>
 * 可以利用传入新的timer重写动画行为
 * @date 2023/11/15
 */

@SuppressWarnings({"unused"})
public class RollDisplay extends JPanel {
    public static final int CONSTANT_SPEED=1;
    private int rollDisplay_Style=CONSTANT_SPEED;
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_UPPER = 3;
    public static final int DIRECTION_BELOW = 4;
    private int direction = DIRECTION_LEFT;
    private LinkedList<JComponent> linkedList;
    private int intervalTime;
    private int intervalDistance;
    private boolean isRecycle = true;
    private int width;
    private int height;
    private Timer timer;
    private int moveDistance = 0;
    private boolean isStart = false;
    public void rootPanelInitial(){
        this.setLayout(null);
        //把根面板的背景设为不显示，否则在根面板存在背景色时，会在最右侧显示一条与背景色颜色相同的线，且无法通过调整移动组件的宽度解决
        //初步判断可能与组件的坐标是从0开始，左闭右开的，但通过增大子组件坐标无法解决问题。
        //怀疑与Swing面板的实现有关，暂且通过不显示背景解决。
        this.setOpaque(false);
    }
    /**
     * @param linkedList       包含要被平移的组件的LinkedList链表
     * @param intervalTime     平移间隔时间
     * @param intervalDistance 一次平移的距离
     * @param direction        平移的组件来自哪个方向
     * @param isRecycle        平移是否循环
     */
    public RollDisplay(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance, int direction, boolean isRecycle) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.direction = direction;
        this.isRecycle = isRecycle;
        rootPanelInitial();
        switch (direction) {
            case DIRECTION_LEFT -> timer = new Timer(intervalTime, e -> translationFromLeft());
            case DIRECTION_RIGHT -> timer = new Timer(intervalTime, e -> translationFromRight());
            case DIRECTION_UPPER -> timer = new Timer(intervalTime, e -> translationFromUpper());
            case DIRECTION_BELOW -> timer = new Timer(intervalTime, e -> translationFromBelow());
        }
    }

    public RollDisplay(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance, int direction) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.direction = direction;
        rootPanelInitial();
        switch (direction) {
            case DIRECTION_LEFT -> timer = new Timer(intervalTime, e -> translationFromLeft());
            case DIRECTION_RIGHT -> timer = new Timer(intervalTime, e -> translationFromRight());
            case DIRECTION_UPPER -> timer = new Timer(intervalTime, e -> translationFromUpper());
            case DIRECTION_BELOW -> timer = new Timer(intervalTime, e -> translationFromBelow());
        }
    }

    public RollDisplay(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        rootPanelInitial();
        initialComponentPos();
        timer = new Timer(intervalTime, e -> translationFromLeft());
    }

    private void translationFromLeft() {
        int i = 0;
        moveDistance += intervalDistance;
        for (JComponent a : linkedList) {
            a.setLocation(i * width - moveDistance, 0);
            i++;
        }
        this.repaint();
        if (moveDistance >= width && isRecycle) {
            moveDistance = 0;
            JComponent tmp = linkedList.removeFirst();
            linkedList.add(tmp);
        }
    }

    private void translationFromRight() {
        int i = 0;
        moveDistance += intervalDistance;
        for (JComponent a : linkedList) {
            a.setLocation(-i * width + moveDistance, 0);
            i++;
        }
        this.repaint();
        if (moveDistance >= width && isRecycle) {
            moveDistance = 0;
            JComponent tmp = linkedList.removeFirst();
            linkedList.add(tmp);
        }
        this.repaint();
    }

    private void translationFromUpper() {
        int i = 0;
        moveDistance += intervalDistance;
        for (JComponent a : linkedList) {
            a.setLocation(0, -i * height+moveDistance);
            i++;
        }
        this.repaint();
        if (moveDistance >= height && isRecycle) {
            moveDistance = 0;
            JComponent tmp = linkedList.removeFirst();
            linkedList.add(tmp);
        }
        this.repaint();
    }

    private void translationFromBelow() {
        int i = 0;
        moveDistance += intervalDistance;
        for (JComponent a : linkedList) {
            a.setLocation(0, i * height - moveDistance);
            i++;
        }
        this.repaint();
        if (moveDistance >= height && isRecycle) {
            moveDistance = 0;
            JComponent tmp = linkedList.removeFirst();
            linkedList.add(tmp);
        }
        this.repaint();
    }

    private void initialComponentPos() {
        width = this.getWidth();
        height = this.getHeight();
        int i = 0;
        switch (direction) {
            case DIRECTION_LEFT -> {
                for (JComponent a : linkedList) {
                    a.setBounds(i * width, 0, width, height);
                    this.add(a);
                    i++;
                }
            }
            case DIRECTION_RIGHT -> {
                for (JComponent a : linkedList) {
                    a.setBounds(-i * width, 0, width, height);
                    this.add(a);
                    i++;
                }
            }
            case DIRECTION_UPPER -> {
                for (JComponent a : linkedList) {
                    a.setBounds(0, -i * height, width, height);
                    this.add(a);
                    i++;
                }
            }
            case DIRECTION_BELOW -> {
                for (JComponent a : linkedList) {
                    a.setBounds(0, i * height, width, height);
                    this.add(a);
                    i++;
                }
            }
            default -> {
                direction = DIRECTION_LEFT;
                for (JComponent a : linkedList) {
                    a.setBounds(i * width, 0, width, height);
                    this.add(a);
                    i++;
                }
            }
        }
    }

    /**
     * 重写setBounds方法，用于在RollDisplay组件的位置设定后，立刻设定待移动组件的位置和大小<br>
     * 避免在构造函数中初始化待移动组件导致获取到的RollDisplay的长和宽为0。
     * @param x the new <i>x</i>-coordinate of this component
     * @param y the new <i>y</i>-coordinate of this component
     * @param width the new {@code width} of this component
     * @param height the new {@code height} of this
     *          component
     */
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        initialComponentPos();
    }

    public void start() {
        initialComponentPos();
        timer.start();
        isStart = true;
    }

    public void restart() {
        initialComponentPos();
        timer.restart();
        isStart = true;
    }

    public void stop() {
        timer.stop();
        isStart = false;
    }

    public LinkedList<JComponent> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<JComponent> linkedList) {
        this.linkedList = linkedList;
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
            initialComponentPos();
            switch (direction) {
                case DIRECTION_LEFT -> timer = new Timer(intervalTime, e -> translationFromLeft());
                case DIRECTION_RIGHT -> timer = new Timer(intervalTime, e -> translationFromRight());
                case DIRECTION_UPPER -> timer = new Timer(intervalTime, e -> translationFromUpper());
                case DIRECTION_BELOW -> timer = new Timer(intervalTime, e -> translationFromBelow());
            }
        }
    }

    public boolean isRecycle() {
        return isRecycle;
    }

    public void setRecycle(boolean recycle) {
        isRecycle = recycle;
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * 可以利用传入新的timer重写动画行为
     *
     * @param timer 新的Timer对象
     */
    public void setTimer(Timer timer) {
        if (!isStart) {
            this.timer = timer;
        }
    }
    public int getRollDisplay_Style() {
        return rollDisplay_Style;
    }
    public void setRollDisplay_Style(int rollDisplay_Style) {
        this.rollDisplay_Style = rollDisplay_Style;
    }
}