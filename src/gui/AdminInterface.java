package gui;

import gui.component.ShapeDeepenJPanel;
import gui.frame.ResizeFrame;

import javax.swing.*;
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
        frame.setLayout(null);
        frame.setSize(initialWidth, initialHeight);
        frame.setLocationRelativeTo(null);
        frame.setTitle("注册");
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        //鼠标拖动窗口
        var bottomPanel=new JPanel();
        bottomPanel.setBounds(0,0,1000,700);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(212, 239, 223));
        frame.add(bottomPanel);
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
