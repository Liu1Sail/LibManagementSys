package com.qdu.niit.library.gui;

import com.qdu.niit.library.gui.component.ShapeDeepenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private InputText inputText;
    private int genderNumber=1;

    //完成阴影边框，并应用到注册和登录窗口
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
        var titleText = new JLabel();
        titleText.setBounds(100, 10, 200, 50);
        titleText.setFont(new Font("宋体", Font.PLAIN, 40));
        titleText.setText("注册");
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setVerticalAlignment(SwingConstants.TOP);
        bodyPanel.add(titleText);
        var nameLabel = new JLabel();
        var nameInput = new JTextField();
        var passwordLabel = new JLabel();
        var passwordInput = new JPasswordField();
        var passwordAgainLabel = new JLabel();
        var passwordAgainInput = new JPasswordField();
        var genderLabel = new JLabel();
        var genderPanel = new JPanel();
        var genderGroup = new ButtonGroup();
        var maleButton = new JRadioButton("男", true);
        var femaleButton = new JRadioButton("女", false);
        maleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genderNumber = 1;
            }
        });
        femaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genderNumber = 2;
            }
        });
        var phoneLabel = new JLabel();
        var phoneInput = new JTextField();
        var emailLabel = new JLabel();
        var emailInput = new JTextField();
        var font = new Font("宋体", Font.PLAIN, 18);
        nameLabel.setBounds(30, 60, 80, 50);
        nameLabel.setFont(font);
        nameLabel.setText("用户名：");
        nameLabel.setOpaque(false);
        nameInput.setBounds(120, 70, 200, 30);
        passwordLabel.setBounds(30, 110, 80, 50);
        passwordLabel.setFont(font);
        passwordLabel.setText("密码：");
        passwordLabel.setOpaque(false);
        passwordInput.setBounds(120, 120, 200, 30);
        passwordAgainLabel.setBounds(30, 160, 95, 50);
        passwordAgainLabel.setFont(font);
        passwordAgainLabel.setText("重复密码：");
        passwordAgainLabel.setOpaque(false);
        passwordAgainInput.setBounds(120, 170, 200, 30);
        genderLabel.setBounds(30, 210, 80, 50);
        genderLabel.setFont(font);
        genderLabel.setText("性别：");
        genderLabel.setOpaque(false);
        genderPanel.setBounds(65, 220, 200, 30);
        genderPanel.setOpaque(false);
        maleButton.setOpaque(false);
        maleButton.setFocusable(false);
        maleButton.setFont(font);
        maleButton.setFont(font);
        femaleButton.setOpaque(false);
        femaleButton.setFocusable(false);
        femaleButton.setFont(font);
        femaleButton.setFont(font);
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        phoneLabel.setBounds(30, 260, 80, 50);
        phoneLabel.setFont(font);
        phoneLabel.setText("手机号：");
        phoneLabel.setOpaque(false);
        phoneInput.setBounds(120, 270, 200, 30);
        emailLabel.setBounds(30, 310, 80, 50);
        emailLabel.setFont(font);
        emailLabel.setText("邮箱：");
        emailLabel.setOpaque(false);
        emailInput.setBounds(120, 320, 200, 30);
        bodyPanel.add(nameLabel);
        bodyPanel.add(nameInput);
        bodyPanel.add(passwordLabel);
        bodyPanel.add(passwordInput);
        bodyPanel.add(passwordAgainLabel);
        bodyPanel.add(passwordAgainInput);
        bodyPanel.add(genderLabel);
        bodyPanel.add(genderPanel);
        bodyPanel.add(phoneLabel);
        bodyPanel.add(phoneInput);
        bodyPanel.add(emailLabel);
        bodyPanel.add(emailInput);
        var cancelButton = new JButton();
        var defineButton = new JButton();
        cancelButton.setBounds(110, 370, 80, 30);
        cancelButton.setText("取消");
        defineButton.setBounds(210, 370, 80, 30);
        defineButton.setText("确认");
        defineButton.addActionListener(e -> {
            inputText = new InputText(nameInput.getText(), new String(passwordInput.getPassword()),
                    new String(passwordAgainInput.getPassword()), genderNumber,
                    phoneInput.getText(), emailInput.getText());
            //获取输入信息，向数据库提交信息
            System.out.println(inputText.checkInputText());
            frame.dispose();
        });
        cancelButton.addActionListener(e -> frame.dispose());
        bodyPanel.add(cancelButton);
        bodyPanel.add(defineButton);
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

    private class InputText {
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

        public InputText(String name, String password, String passwordAgain, int gender, String phoneNumber, String emailAddress) {
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