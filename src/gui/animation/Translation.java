package gui.animation;

import javax.swing.*;
import java.util.LinkedList;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description Translation组件为JPanel的子类，将LinkedList中的组件，随时间在Translation组件内的区域内平移， 并将已离开屏幕的组件移至队尾，以达到循环显示的效果。LinkedList中的组件将会填充Translation组件的范围。
 * LinkedList的类型变量应为JComponent类型，元素为JComponent或其子类对象。
 * @date 2023/11/15
 */

@SuppressWarnings({"unused",""})
public class Translation extends JPanel {
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_UPPER = 3;
    public static final int DIRECTION_BELOW = 4;
    private LinkedList<JComponent> linkedList;
    private int intervalTime;
    private int intervalDistance;
    private int direction = DIRECTION_LEFT;
    private boolean isRecycle = true;
    private int width;
    private int height;
    private Timer timer;
    private int x = 0;

    /**
     * @param linkedList       包含要被平移的组件的LinkedList链表
     * @param intervalTime     平移间隔时间
     * @param intervalDistance 一次平移的距离
     * @param direction        平移的组件来自哪个方向
     * @param isRecycle        平移是否循环
     */
    public Translation(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance, int direction, boolean isRecycle) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.direction = direction;
        this.isRecycle = isRecycle;
        this.setLayout(null);
        timer = new Timer(intervalTime, e -> translationBehavior());
    }

    public Translation(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance, int direction) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.direction = direction;
        this.setLayout(null);
        timer = new Timer(intervalTime, e -> translationBehavior());
    }

    public Translation(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance, boolean isRecycle) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.isRecycle = isRecycle;
        this.setLayout(null);
        timer = new Timer(intervalTime, e -> translationBehavior());
    }

    public Translation(LinkedList<JComponent> linkedList, int intervalTime, int intervalDistance) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.setLayout(null);
        timer = new Timer(intervalTime, e -> translationBehavior());
    }

    public void translationBehavior() {
        int i = 0;
        x += intervalDistance;
        for (JComponent a : linkedList) {
            a.setLocation(i * width - x, 0);
            i++;
        }
        this.repaint();
        if (x >= width && isRecycle) {
            x = 0;
            JComponent tmp = linkedList.removeFirst();
            linkedList.add(tmp);
        }
        this.repaint();
    }

    public void initialLinkList() {
        width = this.getWidth();
        height = this.getHeight();
        int i = 0;
        for (JComponent a : linkedList) {
            a.setBounds(i * width, 0, width, height);
            this.add(a);
            i++;
        }
    }

    public void start() {
        initialLinkList();
        timer.start();
    }

    public void restart() {
        initialLinkList();
        timer.restart();
    }

    public void stop() {
        timer.stop();
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
        this.direction = direction;
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

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}