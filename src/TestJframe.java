import com.qdu.niit.library.service.BorrowingService;
import com.qdu.niit.library.service.impl.BorrowingServiceImpl;
import com.qdu.niit.library.utils.SqlConfig;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class TestJframe {
    public static void main(String[] args) throws SQLException {
        SqlConfig temper = SqlConfig.getInstance();
        temper.setUser("root");
        temper.setPassword("root");
        temper.setUrl("jdbc:mysql://localhost:3306/mybd");
        jpanelTest.setUid(23);
        JPanel receive = jpanelTest.getinstance();
        JFrame test = new JFrame();
        test.setLayout(null);
        test.setSize(800,640);
        test.add(receive);
        test.setVisible(true);
        int a = 0;
    }
}
