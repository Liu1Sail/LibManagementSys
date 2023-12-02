package com.qdu.niit.library.gui;

import com.qdu.niit.library.gui.animation.TextEmergeLabel;
import com.qdu.niit.library.gui.animation.Translation;
import com.qdu.niit.library.gui.component.ShapeDeepenPanel;
import com.qdu.niit.library.gui.input.InputInnerPasswordField;
import com.qdu.niit.library.gui.input.InputInnerTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
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
    private int genderNumber = 1;
    private final HashMap<Integer, Translation> componentMap = new HashMap<>();

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
        bodyPanel.setBounds(0, 40, 400, 560);
        bodyPanel.setOpaque(false);
        bodyPanel.setLayout(null);
        var inputBorderColor = new Color(84, 157, 248);
        var inputInnerTextColor = new Color(153, 153, 153);
        var inputBackgroundColor = new Color(229, 244, 251);
        var inputInnerTextDefaultFont = new Font("宋体", Font.PLAIN, 14);
        var titleText = new JLabel("欢迎注册图书馆账号");
        titleText.setFont(new Font("宋体", Font.PLAIN, 35));
        titleText.setBounds(30, 0, 350, 50);
        titleText.setFocusable(true);
        var nameInput = new InputInnerTextField("用户名", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (textField.getText().isEmpty() && !isInitial) {
                    for (int i = 3; i <= 4; i++) {
                        Translation tmpSave = componentMap.get(i);
                        if (tmpSave.isStart()) {
                            tmpSave.stop();
                        }
                        tmpSave.setDirection(Translation.TO_UPPER);
                        tmpSave.start();
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    if (tip.getTimer().isRunning()) {
                        tip.stop();
                    }
                    tip.setStartOpacity(255);
                    tip.setEndOpacity(0);
                    tip.start();
                }
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("用户名")) {
                    isInitial = false;
                    for (int i = 3; i <= 4; i++) {
                        Translation tmpSave = componentMap.get(i);
                        if (tmpSave.isStart()) {
                            tmpSave.stop();
                        }
                        tmpSave.setDirection(Translation.TO_BELOW);
                        tmpSave.start();
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    if (tip.getTimer().isRunning()) {
                        tip.stop();
                    }
                    tip.setStartOpacity(0);
                    tip.setEndOpacity(255);
                    tip.start();
                }
            }
        };
        var namePanel = new Translation(nameInput, 1, 3, 10, Translation.TO_BELOW);
        var nameNotEmptyTip = new TextEmergeLabel("用户名不能为空", 0.05, 0, 255, Color.RED);
        var nameNotEmptyTipPanel = new Translation(nameNotEmptyTip, 1, 3, 10, Translation.TO_BELOW);

        var passwordInput = new InputInnerPasswordField("密码", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerPasswordField passwordField) {
                if (passwordField.getText().isEmpty() && !isInitial) {
                    for (int i = 5; i <= 3; i++) {
                        Translation tmpSave = componentMap.get(i);
                        if (tmpSave.isStart()) {
                            tmpSave.stop();
                        }
                        tmpSave.setDirection(Translation.TO_UPPER);
                        tmpSave.start();
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                    if (tip.getTimer().isRunning()) {
                        tip.stop();
                    }
                    tip.setStartOpacity(255);
                    tip.setEndOpacity(0);
                    tip.start();
                }
            }

            @Override
            public void lostFocusMovement(InputInnerPasswordField passwordField) {
                if (passwordField.getText().equals("密码")) {
                    isInitial = false;
                    for (int i = 5; i <= 3; i++) {
                        Translation tmpSave = componentMap.get(i);
                        if (tmpSave.isStart()) {
                            tmpSave.stop();
                        }
                        tmpSave.setDirection(Translation.TO_BELOW);
                        tmpSave.start();
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();

                    if (tip.getTimer().isRunning()) {
                        tip.stop();
                    }
                    tip.setStartOpacity(0);
                    tip.setEndOpacity(255);
                    tip.start();
                }
            }
        };
        var passwordPanel = new Translation(passwordInput, 1, 3, 10, Translation.TO_BELOW);
        var passNotEmptyTip = new TextEmergeLabel("密码不能为空", 0.05, 0, 255, Color.RED);
        var passNotEmptyTipPanel = new Translation(passNotEmptyTip, 1, 3, 10, Translation.TO_BELOW);

        namePanel.setBounds(70, 70, 250, 35);
        nameInput.setFont(inputInnerTextDefaultFont);
        nameNotEmptyTipPanel.setBounds(70, 100, 250, 35);
        passwordPanel.setBounds(70, 120, 250, 35);
        passwordInput.setFont(inputInnerTextDefaultFont);
        passNotEmptyTipPanel.setBounds(70, 150, 250, 35);

        componentMap.put(1, namePanel);
        componentMap.put(2, nameNotEmptyTipPanel);
        componentMap.put(3, passwordPanel);
        componentMap.put(4, passNotEmptyTipPanel);


        bodyPanel.add(titleText);
        bodyPanel.add(namePanel);
        bodyPanel.add(nameNotEmptyTipPanel);
        bodyPanel.add(passwordPanel);
        bodyPanel.add(passNotEmptyTipPanel);


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
        private final int RIGHT = 0;
        private final int WRONG_NAME_SHORT = 1;
        private final int WRONG_EMPTY_PASSWORD=2;
        private final int WRONG_PASSWORD_NOT_SAME = 3;
        private final int WRONG_PASSWORD = 4;
        private final int WRONG_GENDER = 5;
        private final int WRONG_PHONE = 6;
        private final int WRONG_EMAIL = 7;

        public InputTextHandle(String name, String password, String passwordAgain, int gender, String phoneNumber, String emailAddress) {
            this.name = name;
            this.password = password;
            this.passwordAgain = passwordAgain;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        public int checkInputText() {
            if (!isNameEnoughLength()) return WRONG_NAME_SHORT;
            if (!isEmptyPassword()) return WRONG_EMPTY_PASSWORD;
            if (!password.equals(passwordAgain)) return WRONG_PASSWORD_NOT_SAME;
            if (!Pattern.matches("\\S{8,20}", password)) return WRONG_PASSWORD;
            if (!Pattern.matches("\\S*[A-Z]+\\S*", password)) return WRONG_PASSWORD;
            if (!Pattern.matches("\\S*[~`\\-_=+{\\[}\\]\\\\|;:'\",<.>/?!@#$%^&*()]\\S*", password))
                return WRONG_PASSWORD;
            if (gender != 1 && gender != 2) return WRONG_GENDER;
            if (!Pattern.matches("^(13[0-9]|14[57]|15[0-35-9]|18[0-35-9])\\d{8}$", phoneNumber))
                return WRONG_PHONE;
            if (!Pattern.matches("^[a-zA-z0-9_]+@[a-zA-z0-9_]+.[a-zA-z0-9]+$", emailAddress)) return WRONG_EMAIL;
            return RIGHT;
        }
        public boolean isNameEnoughLength(){
            if (!Pattern.matches("\\S{3,}", name)) return false;
            return true;
        }
        public boolean isEmptyPassword(){
            if (!password.isEmpty()) return false;
            return true;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordAgain() {
            return passwordAgain;
        }

        public void setPasswordAgain(String passwordAgain) {
            this.passwordAgain = passwordAgain;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }
    }
}