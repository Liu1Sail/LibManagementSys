package gui;

import gui.tool.ShapeDeepenJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 登陆界面
 * @date 2023/11/12
 */

public class LoginInterface extends JFrame {
    JFrame frame=this;
    private Point offsetMouseToFrame = new Point();
    public LoginInterface(){
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(dimension.width/4,dimension.height/4,800,600);
        frame.setUndecorated(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offsetMouseToFrame.x = e.getXOnScreen() - frame.getLocationOnScreen().x;
                offsetMouseToFrame.y = e.getYOnScreen() - frame.getLocationOnScreen().y;
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            private Point mousePos;

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePos = e.getLocationOnScreen();
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                Point offset = new Point(e.getXOnScreen() - mousePos.x, e.getYOnScreen() - mousePos.y);
                int newX = e.getXOnScreen() - offsetMouseToFrame.x;
                int newY = e.getYOnScreen() - offsetMouseToFrame.y;
                frame.setLocation(newX, newY);
                mousePos = e.getLocationOnScreen();
            }
        });
        var leftPanel=new JPanel();
        leftPanel.setBounds(0,0,500,600);
//        leftPanel.setOpaque(false);
        var rightPanel=new JPanel();
        rightPanel.setBounds(500,0,300,600);
        rightPanel.setLayout(null);
        var rightTitleToolBar = getRightTitleToolBar(frame);
        rightPanel.add(rightTitleToolBar);
        //测试代码区
        leftPanel.setBackground(new Color(212, 239, 223));
        rightPanel.setBackground(new Color(218, 247, 166));
        //添加主面板
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setVisible(true);
    }
    private static JPanel getRightTitleToolBar(JFrame frame) {
        var rightTitleToolBar = new JPanel();
        rightTitleToolBar.setBounds(10, 10, 280, 35);
        rightTitleToolBar.setBackground(Color.WHITE);
        rightTitleToolBar.setLayout(null);
        rightTitleToolBar.setOpaque(false);
        //创建右上角关闭按钮
        var buttonClose = new ShapeDeepenJPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0f, 0f, 0f, this.getOpacity()));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, 0, 15, 15);
                g2d.drawLine(15, 0, 0, 15);
            }
        };
        buttonClose.setOpaque(false);
        //x位置为rightToolBar宽度-(15-10),-10是减去Bar右侧与边界的间隔，以保证图标距离上侧和右侧距离相同
        buttonClose.setBounds(255, 10, 16, 16);
        buttonClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonClose.getTimerEntered().restart();
                if (buttonClose.getTimerExited().isRunning()) {
                    buttonClose.getTimerExited().stop();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonClose.getTimerExited().restart();
                if (buttonClose.getTimerEntered().isRunning()) {
                    buttonClose.getTimerEntered().stop();
                }
            }
        });
        //创建右上角设置(六边形齿轮)按钮
        var settingButton = new ShapeDeepenJPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0f, 0f, 0f, this.getOpacity()));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setStroke(new BasicStroke(1));
                int[] xPoints = {10, 18, 18, 10, 2, 2};//从上面中间的点开始，顺时针
                int[] yPoints = {2, 6, 15, 19, 15, 6};//横向20px，纵向22px，纵向一条边10px，斜着的边在纵向投影6px，在横向投影5px
                //drawPolygon若最后一个点不同，自动闭合，drawPolyline不自动闭合
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                g.drawOval(5, 6, 10, 9);
            }
        };
        settingButton.setOpaque(false);
        settingButton.setBounds(225, 7, 20, 20);//坐标从0开始算，那宽度21时，坐标为0-20
        //未添加设置面板跳转
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingButton.getTimerEntered().restart();
                if (settingButton.getTimerExited().isRunning()) {
                    settingButton.getTimerExited().stop();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.getTimerExited().restart();
                if (settingButton.getTimerEntered().isRunning()) {
                    settingButton.getTimerEntered().stop();
                }
            }
        });
        rightTitleToolBar.add(settingButton);
        rightTitleToolBar.add(buttonClose);
        return rightTitleToolBar;
    }
}
