import com.qdu.niit.library.entity.ReadingRoom;
import com.qdu.niit.library.service.ReadingRoomService;
import com.qdu.niit.library.service.impl.ReadingRoomServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class jpanelTest {
    private int uid;
    private LocalDateTime end;
    public void setUid(int uid)
    {
        this.uid = uid;
    }
    public class RoundButton extends JButton {

        public int colorFlag;
        public int bid;
        public RoundButton() {
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawOval(0, 0, getSize().width-1, getSize().height-1);
        }
        Shape getShape()
        {
            return new Ellipse2D.Float(0,0,getWidth(),getHeight());
        }
        @Override
        public boolean contains(int x, int y) {
            return getShape().contains(x,y);
        }
    }
    private static final int hang = 5;
    private static final int lie = 10;
    private RoundButton[] save = new RoundButton[hang*lie];
    //0表示蓝色，即已经被选择，1表示绿色，表示可以选择
    //-1表示灰色，即不能被选择
    public JPanel only;
    private jpanelTest() throws SQLException {
        if(only == null)
        {
            only = new JPanel();
            create();
        }
    }
    public JPanel getJpanel()
    {
        return only;
    }
    //用来初始化界面
    private void create() throws SQLException {
        only.setSize(800,640);
        only.setBackground(Color.CYAN);
        only.setLayout(new GridLayout(hang,lie));

        for(int i = 0;i<hang;i++)
        {
            for(int j = 0;j<lie;j++)
            {
                save[i] = new RoundButton();
                save[i].setBackground(Color.green);
                save[i].colorFlag = 1;
                save[i].bid = i*10+j;
                only.add(save[i]);
            }
        }
        ReadingRoomService use = new ReadingRoomServiceImpl();
        ReadingRoom[]receive = use.findAllBySmallEndTime();
        if(receive == null)
        {
            return;
        }
        for(int i = 0;i<receive.length;i++)
        {
            save[receive[i].getUid()].setBackground(Color.red);
        }
    }
    private void addListen(RoundButton in)throws SQLException
    {
        in.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(in.colorFlag == 1)
                {
                    ReadingRoom push = new ReadingRoom(uid,in.bid, LocalDateTime.now(),end);
                    ReadingRoomService use = new ReadingRoomServiceImpl();
                    try {
                        if(use.ifHaveByUid(uid))
                        {
                            //这里要特殊处理
                        }
                        else {
                            use.insert(push);
                            save[in.bid].setBackground(Color.blue);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
