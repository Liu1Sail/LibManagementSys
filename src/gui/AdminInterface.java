package gui;

import gui.component.ShapeDeepenJPanel;
import gui.frame.ResizeFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 管理员管理界面
 * @date 2023/11/16
 */

public class AdminInterface extends ResizeFrame {
    private final ResizeFrame frame = this;
    private final int initialWidth=1000;
    private final int initialHeight=700;

    //完成阴影边框，并应用到注册和登录窗口
    public AdminInterface() {
        frame.setLayout(new BorderLayout());
        frame.setSize(initialWidth, initialHeight);
        frame.setLocationRelativeTo(null);
        frame.setTitle("注册");
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        //鼠标拖动窗口
        var bottomPanel=new JPanel();
        bottomPanel.setBounds(0,0,1000,700);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        var leftPanel=new JPanel();//左侧面板
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLocation(0,0);
        leftPanel.setPreferredSize(new Dimension(200,0));
        bottomPanel.add(leftPanel,BorderLayout.WEST);

        var topBottomPanel=new JPanel();//顶部根面板
        topBottomPanel.setBackground(Color.WHITE);
        topBottomPanel.setPreferredSize(new Dimension(0,100));
        topBottomPanel.setLayout(null);

        var leftTopPanel=new JPanel();//左上角图标面板
        leftTopPanel.setBounds(0,0,leftPanel.getPreferredSize().width,200);
        leftTopPanel.setBackground(Color.ORANGE);
        System.out.println(leftPanel.getPreferredSize().width);
        topBottomPanel.add(leftTopPanel);
        var topPanel=new JPanel();
        topPanel.setBounds(leftPanel.getPreferredSize().width,0,topBottomPanel.getPreferredSize().width,200);
        topBottomPanel.add(topPanel);
        bottomPanel.add(topBottomPanel,BorderLayout.NORTH);
        var centerPanel=new JPanel();//中心面板
        centerPanel.setBackground(new Color(236,238,245));
        bottomPanel.add(centerPanel,BorderLayout.CENTER);
        //测试代码区
        //
        frame.add(bottomPanel,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static ShapeDeepenJPanel getButtonClose(JFrame frame) {
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
        buttonClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
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
        return buttonClose;
    }
}
