import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.awt.*;
import java.awt.event.*;
public class TrayTest {
    public static void main(String[] args) {
        new ToTrayIcon();
    }
}

 class ToTrayIcon extends JFrame implements ActionListener,
        WindowListener {

    private static final long serialVersionUID = 1L;
    // Variables declaration - do not modify
    private javax.swing.JLabel L_img;
    private javax.swing.JLabel L_img2;
    private PopupMenu pop;
    private MenuItem open, close;
    private TrayIcon trayicon;

    // End of variables declaration

    /** Creates new form MainFrame */
    public ToTrayIcon() {
        setTitle("Java实现系统托盘示例");
        setLocation(300, 300);
        initComponents();
        addWindowListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        // L_img = new javax.swing.JLabel(new
        // ImageIcon((MainFrame.class).getResource("com/topking/tray/images/netbean1.png")));
        // L_img2 = new javax.swing.JLabel(new
        // ImageIcon((MainFrame.class).getResource("com/topking/tray/images/netbean2.png")));
        L_img = new javax.swing.JLabel();
        L_img2 = new javax.swing.JLabel();

        pop = new PopupMenu();
        open = new MenuItem("打开");
        open.addActionListener(this);

        close = new MenuItem("关闭");
        close.addActionListener(this);

        pop.add(open);
        pop.add(close);

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image icon = getToolkit().getImage(
                    getClass().getResource("/img/login/LeftImage.jpg"));
            trayicon = new TrayIcon(icon, "系统托盘示例(java)", pop);
            trayicon.addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        if (getExtendedState() == JFrame.ICONIFIED) {
                            openFrame();// 还原窗口
                        } else {
                            // 设置窗口状态(最小化至托盘)
                            setExtendedState(JFrame.ICONIFIED);
                        }
                    }
                }

                public void mouseEntered(MouseEvent e) {

                }

                public void mouseExited(MouseEvent e) {

                }

                public void mousePressed(MouseEvent e) {

                }

                public void mouseReleased(MouseEvent e) {

                }

            });

            try {
                tray.add(trayicon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        /** *设置界面布局,可以不用理睬它 */
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        ParallelGroup parg = layout
                .createParallelGroup(GroupLayout.Alignment.LEADING);
        SequentialGroup seqg = layout.createSequentialGroup();
        ParallelGroup parg2 = layout
                .createParallelGroup(GroupLayout.Alignment.TRAILING);

        ParallelGroup parg3 = parg2.addComponent(L_img2,
                GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380,
                Short.MAX_VALUE);
        ParallelGroup parg4 = parg3.addComponent(L_img,
                GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 380,
                Short.MAX_VALUE);
        SequentialGroup seqg2 = seqg.addContainerGap();
        SequentialGroup seqg3 = seqg2.addGroup(parg4);
        SequentialGroup seqg4 = seqg3.addContainerGap();
        ParallelGroup parg5 = parg.addGroup(GroupLayout.Alignment.TRAILING,
                seqg4);
        layout.setHorizontalGroup(parg5);
        parg = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        layout.setVerticalGroup(parg.addGroup(layout.createSequentialGroup()
                .addContainerGap().addComponent(L_img).addGap(29, 29, 29)
                .addComponent(L_img2, GroupLayout.PREFERRED_SIZE, 222,
                        GroupLayout.PREFERRED_SIZE).addContainerGap(39,
                        Short.MAX_VALUE)));

        pack();
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToTrayIcon().setVisible(true);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            openFrame();
        }
        if (e.getSource() == close) {
            System.exit(-1);
        }
    }

    public void openFrame() {
        setVisible(true);// 设置为可见
        setAlwaysOnTop(true);// 设置置顶
        // 设置窗口状态(在最小化状态弹出显示)
        setExtendedState(JFrame.NORMAL);
    }

    public void windowActivated(WindowEvent arg0) {

    }

    public void windowClosed(WindowEvent arg0) {
        dispose();
    }

    public void windowClosing(WindowEvent arg0) {

    }

    public void windowDeactivated(WindowEvent arg0) {

    }

    public void windowDeiconified(WindowEvent arg0) {

    }

    // 窗口最小化
    public void windowIconified(WindowEvent arg0) {
        setVisible(false);// 设置为不可见
    }

    public void windowOpened(WindowEvent arg0) {

    }

}