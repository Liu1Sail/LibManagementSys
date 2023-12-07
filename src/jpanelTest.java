import com.qdu.niit.library.entity.ReadingRoom;
import com.qdu.niit.library.service.ReadingRoomService;
import com.qdu.niit.library.service.impl.ReadingRoomServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

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

    private jpanelTest() throws SQLException {
        only = new JPanel();
        run();
        create();
    }

    public static JPanel getinstance() throws SQLException {
        if(only == null)
        {
            new jpanelTest();
        }
        return only;
    }
    private static JTextField tableUse;
    private static JButton back;//用于归还作为
    private static JFrame  pushUse;
    private static JComboBox pushYear;
    private static JComboBox pushmonth;
    private static JComboBox pushday;
    private static final String[] day31 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private static  final String[] day30 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    private static  final String[] day29= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
    private static  final String[] day28= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
    private static  final String[] year = {"2023","2024","2025","2026","2027","2028","2029","2030"};
    private static  final String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private static int[] test = {1,2,3,4,5,6,7};
    private static JButton pushIn;
    private static JComboBox chooseHour;
    private static final JTextField chooseText = new JTextField("选择借阅时间");
    private static final JTextField yearText = new JTextField("选择年份");
    private static final JTextField monthText = new JTextField("选择月份");
    private static final JTextField dayText = new JTextField("选择日");
    private static  JButton backIn;
    private static  JButton surePush;
    private void tianChongPushday()
    {
        int month = (int) pushmonth.getSelectedIndex();
        month++;
        if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12)
        {
            pushday.setModel(new DefaultComboBoxModel<>(day31));
        }
        else if(month == 4||month == 6||month == 9||month == 11)
        {
            pushday.setModel(new DefaultComboBoxModel<>(day30));
        }
        else
        {
            //2月的特殊处理
            int a = pushYear.getSelectedIndex();
            a+=2023;
            if(a%4 == 0&&a%100!=0)
            {
                pushday.setModel(new DefaultComboBoxModel<>(day29));
            }
            else if(a%400 == 0)
            {
                pushday.setModel(new DefaultComboBoxModel<>(day29));
            }
            else
            {
                pushday.setModel(new DefaultComboBoxModel<>(day28));
            }
        }
    }
    private void run()
    {
        pushIn = new JButton("选择开始时间");
        pushIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushUse.setVisible(true);
            }
        });
        pushUse = new JFrame();
        pushUse.setLayout(new GridLayout(2,5));
        pushUse.setBounds(500,500,300,300);
        pushYear = new JComboBox<Integer>();
        pushmonth = new JComboBox<Integer>();
        backIn = new JButton("返回主界面");
        surePush = new JButton("确定");
        surePush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushUse.setVisible(false);
            }
        });
        backIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushUse.setVisible(false);
            }
        });
        pushmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                tianChongPushday();
            }
        });
        pushday = new JComboBox<Integer>();
        chooseHour = new JComboBox<Integer>();
        for(int i = 1;i<=12;i++)
        {
            chooseHour.addItem(i);
        }
        pushYear.setModel(new DefaultComboBoxModel(year));
        pushmonth.setModel(new DefaultComboBoxModel(month));
        pushYear.setSelectedIndex(0);
        pushmonth.setSelectedIndex(0);
        tianChongPushday();
        pushday.setSelectedIndex(0);
        pushUse.add(pushYear);
        pushUse.add(yearText);
        pushUse.add(pushmonth);
        pushUse.add(monthText);
        pushUse.add(pushday);
        pushUse.add(dayText);
        pushUse.add(backIn);
        pushUse.add(surePush);
        tableUse = new JTextField("阅览室借阅系统");
        back = new JButton("座位归还");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!use.ifHaveByUid(uid))
                    {
                        JOptionPane.showMessageDialog(only,"你没有预定一个座位");
                    }
                    else
                    {
                        int bid = use.findOneByUid(uid).getBid();
                        use.deleteByBid(bid);
                        save[bid].setColorFlag(1);
                        save[bid].repaint();
                        JOptionPane.showMessageDialog(only,bid+"号座位归还成功，可以再次预约");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private static ReadingRoomService use = new ReadingRoomServiceImpl();
    //用来初始化界面
    private void create() throws SQLException {
        //
        only.setSize(800,640);
        only.setLayout(new GridLayout(hang+1,lie));
        only.setBackground(Color.gray);
        only.add(tableUse);
        only.add(chooseHour);
        only.add(chooseText);
        only.add(pushIn);
        only.add(back);
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
        ReadingRoom[]receive = use.findAllBySmallEndTime(LocalDateTime.now());
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

                            LocalDateTime first = LocalDateTime.now();
                            first.withYear((Integer) pushYear.getSelectedIndex()+2023);
                            first.withMonth((Integer) pushmonth.getSelectedIndex()+1);
                            first.withDayOfMonth((Integer) pushday.getSelectedIndex()+1);
                            LocalDateTime second = first;
                            second.plusHours((Integer) chooseHour.getSelectedItem());
                            ReadingRoom push = new ReadingRoom(uid,in.getBid(), first,second);
                            use.insert(push);
                            save[in.getBid()].setColorFlag(0);
                            save[in.getBid()].repaint();
                            JOptionPane.showMessageDialog(only,"座位预定成功,预定时间为"+first+"————到————"+second);
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
