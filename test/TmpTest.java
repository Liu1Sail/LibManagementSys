import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 作者姓名
 * @program LibManagementSys
 * @description 测试类，测试各种不确定代码，不使用
 * @date 2023/11/13
 */

public class TmpTest {
    public TmpTest() {
        //测试环境
        JFrame frame = new JFrame();
        frame.setBounds(300, 200, 800, 500);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBackground(null);
        frame.setLayout(null);
        //测试内容
        var panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 20, 400, 400);
//        panel.setBackground(new Color(212, 239, 223));
        var first = new JPanel();
        var second = new JPanel();
        first.setBounds(0, 0, 400, 400);
        second.setBounds(400, 0, 400, 400);
        first.setBackground(Color.ORANGE);
        second.setBackground(Color.PINK);
        var timer = new Timer(1, new ActionListener() {
            private int x = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 2;
                if (x < -400) {
                    x = 0;
                }
                first.setLocation(x, 0);
                second.setLocation(400 + x, 0);
                panel.repaint();
            }
        });
        //
        panel.add(first);
        panel.add(second);
        frame.add(panel);
        //
        frame.setVisible(true);
        timer.start();
    }

    public static void main(String[] args) {
        new TmpTest();
    }
}

