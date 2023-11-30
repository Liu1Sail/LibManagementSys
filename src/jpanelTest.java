import com.qdu.niit.library.entity.ReadingRoom;
import com.qdu.niit.library.service.ReadingRoomService;
import com.qdu.niit.library.service.impl.ReadingRoomServiceImpl;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;

 class RoundButton extends JButton {
     private int hours;
    private int colorFlag;
    private int bid;
    private String use;
     public RoundButton(String text) {
         use = text;
         setOpaque(false);
         setContentAreaFilled(false);
         setBorderPainted(false);
     }

     public void setColorFlag(int colorFlag) {
        this.colorFlag = colorFlag;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }

    public int getColorFlag() {
        return colorFlag;
    }

    public RoundButton() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {

        } else {
            switch ((colorFlag))
            {
                case -1:g.setColor(Color.gray);break;
                case 0:g.setColor(Color.blue);break;
                case 1:g.setColor(Color.green);break;
            }
        }
        g.drawString(String.valueOf(bid),5,40);
        g.fillOval(0, 0, 20, 20);
        super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g) {

        g.setColor(getForeground());
        g.drawOval(0, 0, 20, 20);
    }

    @Override
    public boolean contains(int x, int y) {
        return new Ellipse2D.Float(0,0,20,20).contains(x,y);
    }
}
public class jpanelTest {
    public void deleteGetNeed()
    {
        if(getNeed != null)
        {
            getNeed.dispose();
        }
    }
    private static int uid;
    private LocalDateTime end;
    public static void setUid(int in)
    {
        uid = in;
    }
    private static final int hang = 5;
    private static final int lie = 10;
    private RoundButton[] save = new RoundButton[hang*lie];
    //0表示蓝色，即已经被选择，1表示绿色，表示可以选择
    //-1表示灰色，即不能被选择
    private static JPanel only;
    private static JComboBox<Integer>chooseUse;
    private static JButton back;
    private jpanelTest() throws SQLException {
        only = new JPanel();
        run();
        create();
    }
    private static JTextField textField;
    public static JPanel getinstance() throws SQLException {
        if(only == null)
        {
            new jpanelTest();
        }
        return only;
    }
    private static JFrame getNeed;
    private void run()
    {
        getNeed = new JFrame();
        getNeed.setLayout(null);
        getNeed.setResizable(false);
        getNeed.setBounds(400,400,300,300);
        chooseUse = new JComboBox<>();
        chooseUse.setBounds(0,0,100,100);
        for(int i = 1;i<8;i++)
        {
            chooseUse.addItem(i);
        }
        textField = new JTextField("请选择小时");
        textField.setBounds(100,0,100,100);
        back = new JButton("确定");
        back.setBounds(200,0,100,100);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getNeed.setVisible(false);
            }
        });
        getNeed.add(chooseUse);
        getNeed.add(textField);
        getNeed.add(back);
    }
    //用来初始化界面
    private void create() throws SQLException {
        //
        only.setSize(800,640);
        only.setLayout(new GridLayout(hang,lie));
        only.setBackground(Color.gray);
        for(int i = 0;i<hang;i++)
        {
            for(int j = 0;j<lie;j++)
            {
                save[i*10+j] = new RoundButton(String.valueOf(i*10+j));
                save[i*10+j].setBackground(Color.green);
                save[i*10+j].setSize(10,10);
                save[i*10+j].setColorFlag(1);
                save[i*10+j].setBid(i*10+j);
                addListen((save[i*10+j]));
                only.add(save[i*10+j]);
            }
        }
        ReadingRoomService use = new ReadingRoomServiceImpl();
        ReadingRoom[]receive = use.findAll();
        if(receive == null)
        {
            return;
        }
        for(int i = 0;i<receive.length;i++)
        {
            save[receive[i].getBid()-1].setColorFlag(0);
            save[receive[i].getBid()-1].repaint();
        }
    }

    private void addListen(RoundButton in)throws SQLException
    {
        in.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(in.getColorFlag() == 1)
                {
                    ReadingRoomService use = new ReadingRoomServiceImpl();
                    try {
                        if(use.ifHaveByUid(uid))
                        {
                            JOptionPane.showMessageDialog(only,"你已经预定一个座位了");
                        }
                        else {
                            getNeed.setVisible(true);
                            int hours = (int) chooseUse.getSelectedItem();
                            ReadingRoom push = new ReadingRoom(uid,in.getBid(), LocalDateTime.now(),LocalDateTime.now().minusHours(hours));
                            use.insert(push);
                            save[in.getBid()].setColorFlag(0);
                            save[in.getBid()].repaint();
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
