import gui.animation.Translation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @author 作者姓名
 * @program LibManagementSys
 * @description 测试类，测试各种不确定代码，不使用
 * @date 2023/11/13
 */
class TmpTest {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds(300, 150, 800, 500);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var linkedList = new LinkedList<JComponent>();
        //测试数据
        var jPanel1 = new JPanel();
        var jPanel2 = new JPanel();
        var jPanel3 = new JPanel();
        var jPanel4 = new JPanel();
        var jPanel5 = new JPanel();
        jPanel1.setBackground(Color.ORANGE);
        jPanel2.setBackground(Color.BLUE);
        jPanel3.setBackground(Color.PINK);
        jPanel4.setBackground(Color.CYAN);
        jPanel5.setBackground(Color.YELLOW);
        linkedList.add(jPanel1);
        linkedList.add(jPanel2);
        linkedList.add(jPanel3);
        linkedList.add(jPanel4);
        linkedList.add(jPanel5);
        //
        var translation = new Translation(linkedList, 1, 2, Translation.DIRECTION_LEFT, true);
        translation.setBounds(50, 50, 300, 300);
        frame.add(translation);
        frame.setVisible(true);
        translation.start();
    }
}