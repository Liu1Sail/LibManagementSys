package gui.animation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description Translation组件为JPanel的子类，将LinkedList中的组件，随时间在Translation组件内的区域内平移，并将已离开屏幕的组件移至队尾，以达到循环显示的效果。LinkedList中的组件将会填充Translation组件的范围。
 * @date 2023/11/15
 */

public class Translation extends JPanel {
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_UPPER = 3;
    public static final int DIRECTION_BELOW = 4;
    private LinkedList<? extends JComponent> linkedList;
    private int intervalTime;
    private int intervalDistance;
    private Timer timer;
    private boolean isRecycle = true;
    private int width;
    private int height;
    private int x = 0;

    public Translation(LinkedList<? extends JComponent> linkedList, int intervalTime, int intervalDistance, boolean isRecycle) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.isRecycle = isRecycle;
        this.setLayout(null);
        timer = new Timer(intervalTime, e -> translationBehavior());
    }

    public Translation(LinkedList<? extends JComponent> linkedList, int intervalTime, int intervalDistance) {
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
            a.setLocation(0 + i * width - x, 0);
            i++;
        }
        this.repaint();
//        System.out.println("!");
//        for (JComponent a : linkedList) {
//            System.out.println(a.getBounds());
//        }
    }

    public void initialLinkList() {
        width = this.getWidth();
        height = this.getHeight();
        int i = 0;
        for (JComponent a : linkedList) {
            a.setBounds(0 + i * width, 0, width, height);
            this.add(a);
            i++;
        }
        System.out.println("!");
        for (JComponent a : linkedList) {
            System.out.println(a.getBounds());
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
}
//
//未完成循环部分，已完成平移部分
//
class TestTranslation {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds(300, 150, 800, 500);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var linkedList = new LinkedList<JComponent>();
        //测试数据
        var jpanel1 = new JPanel();
        var jpanel2 = new JPanel();
        var jpanel3 = new JPanel();
        var jpanel4 = new JPanel();
        var jpanel5 = new JPanel();
        jpanel1.setBackground(Color.ORANGE);
        jpanel2.setBackground(Color.BLUE);
        jpanel3.setBackground(Color.PINK);
        jpanel4.setBackground(Color.CYAN);
        jpanel5.setBackground(Color.YELLOW);
        linkedList.add(jpanel1);
        linkedList.add(jpanel2);
        linkedList.add(jpanel3);
        linkedList.add(jpanel4);
        linkedList.add(jpanel5);
        //
        var translation = new Translation(linkedList, 1, 2, true);
        translation.setBounds(50, 50, 300, 300);
        frame.add(translation);
        frame.setVisible(true);
        translation.start();
    }
}