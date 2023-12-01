package com.qdu.niit.library.gui;

import com.qdu.niit.library.gui.component.ShapeDeepenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description
 * @date 2023/11/14
 */

public class RegisterInterface extends JFrame {
    private final JFrame frame = this;
    private Point offsetMouseToFrame = new Point();
    private InputTextHandle inputTextHandle;
    private int genderNumber=1;

    public RegisterInterface() {
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 239, 223));
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setTitle("注册");
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        //鼠标拖动窗口
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offsetMouseToFrame.x = e.getXOnScreen() - frame.getLocationOnScreen().x;
                offsetMouseToFrame.y = e.getYOnScreen() - frame.getLocationOnScreen().y;
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - offsetMouseToFrame.x;
                int newY = e.getYOnScreen() - offsetMouseToFrame.y;
                frame.setLocation(newX, newY);
            }
        });
        var buttonClose = getButtonClose(frame);
        //添加注册信息输入JPanel
        var bodyPanel = getBodyPanel();
        frame.add(bodyPanel);
        frame.add(buttonClose);
        frame.setVisible(true);
    }

    private JPanel getBodyPanel() {
        var bodyPanel = new JPanel();
        bodyPanel.setBounds(0, 40, 400, 400);
        bodyPanel.setOpaque(false);
        bodyPanel.setLayout(null);
        return bodyPanel;
    }

    private static ShapeDeepenPanel getButtonClose(JFrame frame) {
        var buttonClose = new ShapeDeepenPanel() {
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
        buttonClose.setBounds(370, 14, 16, 16);
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

    private class InputTextHandle {
        private String name;
        private String password;
        private String passwordAgain;
        private int gender;
        private String phoneNumber;
        private String emailAddress;
        private final int RIGHT=0;
        private final int WRONG_NAME=1;
        private final int WRONG_PASSWORD_NOT_SAME =2;
        private final int WRONG_PASSWORD=3;
        private final int WRONG_GENDER=4;
        private final int WRONG_PHONE=5;
        private final int WRONG_EMAIL=6;

        public InputTextHandle(String name, String password, String passwordAgain, int gender, String phoneNumber, String emailAddress) {
            this.name = name;
            this.password = password;
            this.passwordAgain=passwordAgain;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        public int checkInputText() {
            if (!Pattern.matches("\\S{3,}", name)) return WRONG_NAME;
            if (!password.equals(passwordAgain)) return WRONG_PASSWORD_NOT_SAME;
            if (!Pattern.matches("\\S{8,20}", password)) return WRONG_PASSWORD;
            if (!Pattern.matches("\\S*[A-Z]+\\S*", password)) return WRONG_PASSWORD;
            if (!Pattern.matches("\\S*[~`\\-_=+{\\[}\\]\\\\|;:'\",<.>/?!@#$%^&*()]\\S*", password)) return WRONG_PASSWORD;
            if (gender != 1 && gender != 2) return WRONG_GENDER;
            if (!Pattern.matches("^(13[0-9]|14[57]|15[0-35-9]|18[0-35-9])\\d{8}$", phoneNumber))
                return WRONG_PHONE;
            if (!Pattern.matches("^[a-zA-z0-9_]+@[a-zA-z0-9_]+.[a-zA-z0-9]+$", emailAddress)) return WRONG_EMAIL;
            return RIGHT;
        }
    }
}