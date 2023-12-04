import com.qdu.niit.library.utils.SqlConfig;

import javax.swing.*;
import java.sql.SQLException;

public class TestJframe {
    public static void main(String[] args) throws SQLException {
        SqlConfig temper = SqlConfig.getInstance();
        temper.setUser("root");
        temper.setPassword("root");
        temper.setUrl("jdbc:mysql://localhost:3306/mybd");
        jpanelTest.setUid(4001);
        JPanel receive = jpanelTest.getinstance();
        JFrame test = new JFrame();
        test.setLayout(null);
        test.setSize(800,640);
        test.add(receive);
        test.setVisible(true);
        int a = 0;
    }
}
