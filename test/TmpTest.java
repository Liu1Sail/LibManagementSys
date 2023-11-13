import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 作者姓名
 * @program LibManagementSys
 * @description 测试类，测试各种不确定代码，不使用
 * @date 2023/11/13
 */

public class TmpTest {
    public TmpTest(){
        //测试环境
        JFrame frame=new JFrame();
        frame.setBounds(300,200,800,500);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBackground(null);
        var container=new Container();
        var password=new JPasswordField();
        frame.add(container);
        //测试内容
        password.setBounds(100,100,100,100);
        password.setEchoChar('\0');
        password.setText("输入");
        container.add(password);
        var button=new JButton();
        button.setBounds(200,200,100,100);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(new String(password.getPassword()));
            }
        });
        container.add(button);
        //
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TmpTest();
    }
}
