package gui.animation;

import javax.swing.*;
import java.util.LinkedList;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 该组件为JPanel的子类，将指定LinkedList中的组件，随时间在该组件内的区域内平移，以达到循环显示的效果
 * @date 2023/11/15
 */

public class Translation extends JPanel {
    public static final int DIRECTION_LEFT=1;
    public static final int DIRECTION_RIGHT=2;
    public static final int DIRECTION_UPPER=3;
    public static final int DIRECTION_BELOW=4;
    private LinkedList<? extends JComponent> linkedList;
    private int intervalTime;
    private int intervalDistance;
    private Timer timer;
    private boolean isRecycle=true;
    public Translation(LinkedList<? extends JComponent> linkedList, int intervalTime, int intervalDistance, boolean isRecycle) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.isRecycle = isRecycle;
        this.setLayout(null);
        timer=new Timer(intervalTime,e-> translationBehavior());
    }
    public Translation(LinkedList<? extends JComponent> linkedList, int intervalTime, int intervalDistance) {
        this.linkedList = linkedList;
        this.intervalTime = intervalTime;
        this.intervalDistance = intervalDistance;
        this.setLayout(null);
    }
    public void translationBehavior(){

    }
}
class TestTranslation{
    public static void main(String[] args) {
        new Translation(new LinkedList<JButton>(),1,1);
    }
}