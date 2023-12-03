package com.qdu.niit.library.gui;

import com.qdu.niit.library.gui.animation.TextEmergeLabel;
import com.qdu.niit.library.gui.animation.Translation;
import com.qdu.niit.library.gui.component.ShapeDeepenPanel;
import com.qdu.niit.library.gui.input.InputInnerPasswordField;
import com.qdu.niit.library.gui.input.InputInnerTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private InputTextHandle inputTextHandle = new InputTextHandle();
    private int genderNumber = 1;
    private final HashMap<Integer, Translation> componentMap = new HashMap<>();

    public RegisterInterface() {
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 239, 223));
        frame.setSize(400, 700);
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
        var buttonBackColor = new Color(0, 133, 255);
        var buttonPressedBackColor = new Color(0, 124, 237);
        var inputInnerTextDefaultFont = new Font("宋体", Font.PLAIN, 14);
        var titleText = new JLabel("欢迎注册图书馆账号");
        titleText.setFont(new Font("宋体", Font.PLAIN, 35));
        titleText.setBounds(30, 0, 350, 50);
        titleText.setFocusable(true);
        var nameInput = new InputInnerTextField("用户名", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (!isInitial) {
                    for (int i = 3; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    tip.setVisible(false);
                }
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("用户名")) {
                    isInitial = false;
                    for (int i = 3; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    tip.setText("用户名不能为空");
                    tip.setVisible(true);
                } else {
                    inputTextHandle.setName(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    if (!inputTextHandle.isNameNotShorter()) {
                        isInitial = false;
                        for (int i = 3; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("用户名过短");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isNameNotLonger()) {
                        isInitial = false;
                        for (int i = 3; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("用户名过长");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var namePanel = new Translation(nameInput, 1, 6, 10, Translation.TO_BELOW);
        var nameTip = new TextEmergeLabel("用户名不能为空", 0.01, 255, 255, Color.RED);
        var nameTipPanel = new Translation(nameTip, 1, 6, 10, Translation.TO_BELOW);
        var passInput = new InputInnerPasswordField("密码", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerPasswordField textField) {
                if (!isInitial) {
                    for (int i = 5; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                    tip.setVisible(false);
                }
            }

            @Override
            public void lostFocusMovement(InputInnerPasswordField textField) {
                if (new String(textField.getPassword()).equals("密码")) {
                    isInitial = false;
                    for (int i = 5; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                    tip.setText("密码不能为空");
                    tip.setVisible(true);
                } else {
                    inputTextHandle.setPassword(new String(textField.getPassword()));
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                    if (!inputTextHandle.isSamePassWord()) {
                        isInitial = false;
                        for (int i = 5; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("密码过长或过短");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isHaveSpecialCharacterPassword()) {
                        isInitial = false;
                        for (int i = 5; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("密码缺少特殊字符");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isHaveUpperCharacterPassword()) {
                        isInitial = false;
                        for (int i = 5; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("密码缺少大写字母");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var passPanel = new Translation(passInput, 1, 6, 10, Translation.TO_BELOW);
        var passTip = new TextEmergeLabel("密码不能为空", 0.01, 255, 255, Color.RED);
        var passTipPanel = new Translation(passTip, 1, 6, 10, Translation.TO_BELOW);
        var passRepeatInput = new InputInnerPasswordField("重复密码", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerPasswordField textField) {
                if (!isInitial) {
                    for (int i = 7; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(6).getTargetComponent();
                    tip.setVisible(false);
                }
            }

            @Override
            public void lostFocusMovement(InputInnerPasswordField textField) {
                if (new String(textField.getPassword()).equals("密码")) {
                    isInitial = false;
                    for (int i = 7; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(6).getTargetComponent();
                    tip.setText("密码不能为空");
                    tip.setVisible(true);
                } else {
                    inputTextHandle.setPassword(new String(textField.getPassword()));
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(6).getTargetComponent();
                    if (!inputTextHandle.isSamePassWord()) {
                        isInitial = false;
                        for (int i = 7; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("密码过长或过短");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isHaveSpecialCharacterPassword()) {
                        isInitial = false;
                        for (int i = 7; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("密码缺少特殊字符");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isHaveUpperCharacterPassword()) {
                        isInitial = false;
                        for (int i = 7; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("密码缺少大写字母");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var passRepeatPanel = new Translation(passRepeatInput, 1, 6, 10, Translation.TO_BELOW);
        var passRepeatTip = new TextEmergeLabel("密码不能为空", 0.01, 255, 255, Color.RED);
        var passRepeatTipPanel = new Translation(passRepeatTip, 1, 6, 10, Translation.TO_BELOW);

        var genderLabel = new JLabel();
        var genderLabelPanel = new Translation(genderLabel, 1, 6, 10, Translation.TO_BELOW);
        var genderButtonPanel = new JPanel();
        var genderPanel = new Translation(genderButtonPanel, 1, 6, 10, Translation.TO_BELOW);
        var genderGroup = new ButtonGroup();
        var maleButton = new JRadioButton("男", true);
        var femaleButton = new JRadioButton("女", false);
        var phoneInput = new InputInnerTextField("手机号", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (!isInitial) {
                    for (int i = 11; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(10).getTargetComponent();
                    tip.setVisible(false);
                }
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("手机号")) {
                    isInitial = false;
                    for (int i = 11; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(10).getTargetComponent();
                    tip.setText("手机号不能为空");
                    tip.setVisible(true);
                } else {
                    inputTextHandle.setPhoneNumber(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(10).getTargetComponent();
                    if (!inputTextHandle.isRightPhoneNumber()) {
                        isInitial = false;
                        for (int i = 11; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("手机号错误");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var phonePanel = new Translation(phoneInput, 1, 6, 10, Translation.TO_BELOW);
        var phoneTip = new TextEmergeLabel("手机不能为空", 0.01, 255, 255, Color.RED);
        var phoneTipPanel = new Translation(phoneTip, 1, 6, 10, Translation.TO_BELOW);

        var emailInput = new InputInnerTextField("邮箱", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (!isInitial) {
                    for (int i = 13; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(12).getTargetComponent();
                    tip.setVisible(false);
                }
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("邮箱")) {
                    isInitial = false;
                    for (int i = 13; i <= 13; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                    }
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(12).getTargetComponent();
                    tip.setText("邮箱不能为空");
                    tip.setVisible(true);
                } else {
                    inputTextHandle.setEmailAddress(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(12).getTargetComponent();
                    if (!inputTextHandle.isRightEmail()) {
                        isInitial = false;
                        for (int i = 13; i <= 13; i++) {
                            var tmp = componentMap.get(i);
                            tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                        }
                        tip.setText("邮箱错误");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var emailPanel = new Translation(emailInput, 1, 6, 10, Translation.TO_BELOW);
        var emailTip = new TextEmergeLabel("邮箱不能为空", 0.01, 255, 255, Color.RED);
        var emailTipPanel = new Translation(emailTip, 1, 6, 10, Translation.TO_BELOW);

        var defineButton = new JButton();
        var defineButtonPanel = new Translation(defineButton, 1, 6, 10, Translation.TO_BELOW);

        namePanel.setBounds(70, 70, 250, 35);
        nameInput.setFont(inputInnerTextDefaultFont);
        nameTipPanel.setBounds(70, 100, 250, 35);
        nameTip.setVisible(false);
        passPanel.setBounds(70, 120, 250, 35);
        passInput.setFont(inputInnerTextDefaultFont);
        passTipPanel.setBounds(70, 150, 250, 35);
        passTip.setVisible(false);
        passRepeatPanel.setBounds(70, 170, 250, 35);
        passRepeatInput.setFont(inputInnerTextDefaultFont);
        passRepeatTipPanel.setBounds(70, 200, 250, 35);
        passRepeatTip.setVisible(false);
        genderLabelPanel.setBounds(70, 210, 80, 50);
        genderLabel.setFont(inputInnerTextDefaultFont);
        genderLabel.setText("性别：");
        genderLabel.setOpaque(false);
        genderPanel.setBounds(70, 215, 200, 30);
        genderButtonPanel.setOpaque(false);
        maleButton.setOpaque(false);
        maleButton.setFocusable(false);
        maleButton.setFont(inputInnerTextDefaultFont);
        femaleButton.setOpaque(false);
        femaleButton.setFocusable(false);
        femaleButton.setFont(inputInnerTextDefaultFont);
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderButtonPanel.add(maleButton);
        genderButtonPanel.add(femaleButton);
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
        phonePanel.setBounds(70, 250, 250, 35);
        phoneInput.setFont(inputInnerTextDefaultFont);
        phoneTipPanel.setBounds(70, 280, 250, 35);
        phoneTip.setVisible(false);
        emailPanel.setBounds(70, 300, 250, 35);
        emailInput.setFont(inputInnerTextDefaultFont);
        emailTipPanel.setBounds(70, 330, 250, 35);
        emailTip.setVisible(false);
        defineButtonPanel.setBounds(100, 360, 200, 50);
        defineButton.setForeground(Color.WHITE);
        defineButton.setFont(new Font("宋体", Font.BOLD, 20));
        defineButton.setText("立即注册");
        defineButton.setHorizontalAlignment(SwingConstants.CENTER);
        defineButton.setVerticalAlignment(SwingConstants.CENTER);
        defineButton.setBackground(buttonBackColor);
        defineButton.setBorder(null);
        defineButton.setFocusPainted(false);

        componentMap.put(1, namePanel);
        componentMap.put(2, nameTipPanel);
        componentMap.put(3, passPanel);
        componentMap.put(4, passTipPanel);
        componentMap.put(5, passRepeatPanel);
        componentMap.put(6, passRepeatTipPanel);
        componentMap.put(7, genderLabelPanel);
        componentMap.put(8, genderPanel);
        componentMap.put(9, phonePanel);
        componentMap.put(10, phoneTipPanel);
        componentMap.put(11, emailPanel);
        componentMap.put(12, emailTipPanel);
        componentMap.put(13, defineButtonPanel);

        bodyPanel.add(titleText);
        bodyPanel.add(namePanel);
        bodyPanel.add(nameTipPanel);
        bodyPanel.add(passPanel);
        bodyPanel.add(passTipPanel);
        bodyPanel.add(passRepeatPanel);
        bodyPanel.add(passRepeatTipPanel);
        bodyPanel.add(genderLabelPanel);
        bodyPanel.add(genderPanel);
        bodyPanel.add(phonePanel);
        bodyPanel.add(phoneTipPanel);
        bodyPanel.add(emailPanel);
        bodyPanel.add(emailTipPanel);
        bodyPanel.add(defineButtonPanel);

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

    @SuppressWarnings("unused")
    private static class InputTextHandle {
        private String name;
        private String password;
        private String passwordAgain;
        private int gender;
        private String phoneNumber;
        private String emailAddress;
        private final int RIGHT = 0;
        private final int WRONG_NAME_SHORT = 1;
        private final int WRONG_NAME_LONG = 2;
        private final int WRONG_EMPTY_PASSWORD = 3;
        private final int WRONG_PASSWORD_NOT_SAME = 4;
        private final int WRONG_PASSWORD_LENGTH = 5;
        private final int WRONG_PASSWORD_UPPER = 6;
        private final int WRONG_PASSWORD_SPECIAL = 7;
        private final int WRONG_GENDER = 8;
        private final int WRONG_PHONE = 9;
        private final int WRONG_EMAIL = 10;

        public InputTextHandle() {
        }

        public InputTextHandle(String name, String password, String passwordAgain, int gender, String phoneNumber, String emailAddress) {
            this.name = name;
            this.password = password;
            this.passwordAgain = passwordAgain;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        public int checkInputText() {
            if (!isNameNotShorter()) return WRONG_NAME_SHORT;
            if (!isNameNotLonger()) return WRONG_NAME_LONG;
            if (!isEmptyPassword()) return WRONG_EMPTY_PASSWORD;
            if (!isSamePassWord()) return WRONG_PASSWORD_NOT_SAME;
            if (!isTrueLengthPassword()) return WRONG_PASSWORD_LENGTH;
            if (!isHaveUpperCharacterPassword()) return WRONG_PASSWORD_UPPER;
            if (!isHaveSpecialCharacterPassword()) return WRONG_PASSWORD_SPECIAL;
            if (!isRightGender()) return WRONG_GENDER;
            if (!isRightPhoneNumber()) return WRONG_PHONE;
            if (!isRightEmail()) return WRONG_EMAIL;
            return RIGHT;
        }

        public boolean isNameNotShorter() {
            return name.length() > 2;
        }

        public boolean isNameNotLonger() {
            return name.length() <= 20;
        }

        public boolean isEmptyPassword() {
            return password.isEmpty();
        }

        public boolean isSamePassWord() {
            return password.equals(passwordAgain);
        }

        public boolean isTrueLengthPassword() {
            return Pattern.matches("\\S{8,20}", password);
        }

        public boolean isHaveUpperCharacterPassword() {
            return Pattern.matches("\\S*[A-Z]+\\S*", password);
        }

        public boolean isHaveSpecialCharacterPassword() {
            return Pattern.matches("\\S*[~`\\-_=+{\\[}\\]\\\\|;:'\",<.>/?!@#$%^&*()]\\S*", password);
        }

        public boolean isRightGender() {
            return gender == 1 || gender == 2;
        }

        public boolean isRightPhoneNumber() {
            return Pattern.matches("^(13[0-9]|14[57]|15[0-35-9]|18[0-35-9])\\d{8}$", phoneNumber);
        }

        public boolean isRightEmail() {
            return Pattern.matches("^[a-zA-z0-9_]+@[a-zA-z0-9_]+.[a-zA-z0-9]+$", emailAddress);
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