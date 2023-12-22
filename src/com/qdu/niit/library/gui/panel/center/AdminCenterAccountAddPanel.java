package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.UserInfo;
import com.qdu.niit.library.gui.input.InputPasswordPanel;
import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.tool.ButtonGroupWithNum;
import com.qdu.niit.library.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 账户添加面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class AdminCenterAccountAddPanel extends centerPanelModel {
    private final InputTextPanel name;
    private final InputPasswordPanel password;
    private final InputTextPanel birthday;
    private final ButtonGroupWithNum genderButtonGroup;
    private final InputTextPanel phone;
    private final InputTextPanel email;
    private final ButtonGroupWithNum adminOrUser;
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    private final JDialog popMessageDialog;
    private final JLabel resultTipLabel = new JLabel("注册成功！");

    public AdminCenterAccountAddPanel(JFrame frame) {
        JTable resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("填写信息存在错误，请按照规定格式填写");
        popMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
        popMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        popMessageLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        popMessageDialog.add(popMessageLabel, BorderLayout.CENTER);
        var popMessageButtonPanel = new JPanel();
        var popMessageButtonExit = new JButton("修改信息");
        popMessageButtonExit.setPreferredSize(new Dimension(90, 30));
        popMessageButtonExit.setFocusPainted(false);
        popMessageButtonPanel.add(popMessageButtonExit);
        popMessageButtonExit.addActionListener(e -> popMessageDialog.setVisible(false));
        popMessageDialog.add(popMessageButtonPanel, BorderLayout.SOUTH);
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("添加账户");
        name = new InputTextPanel("用户名：");
        password = new InputPasswordPanel("密码：");
        birthday = new InputTextPanel("出生日期：");

        phone = new InputTextPanel("手机号：");
        email = new InputTextPanel("邮箱：");

        var adminOrUserFont = new Font("宋体", Font.PLAIN, 16);
        var adminOrUserPanel = new JPanel();
        adminOrUser = new ButtonGroupWithNum();
        adminOrUser.setSelectedButtonNum(2);
        adminOrUserPanel.setBounds(15, 155, 200, 30);
        adminOrUserPanel.setOpaque(false);
        JRadioButton isAdmin = new JRadioButton("管理员");
        isAdmin.setBounds(0, 0, 80, 30);
        isAdmin.setFont(adminOrUserFont);
        isAdmin.setOpaque(false);
        isAdmin.setSelected(false);
        isAdmin.setFocusPainted(false);
        isAdmin.addActionListener(e -> adminOrUser.setSelectedButtonNum(1));
        JRadioButton isUser = new JRadioButton("普通用户");
        isUser.setBounds(80, 0, 90, 30);
        isUser.setFont(adminOrUserFont);
        isUser.setOpaque(false);
        isUser.setSelected(true);
        isUser.setFocusPainted(false);
        isUser.addActionListener(e -> adminOrUser.setSelectedButtonNum(2));
        adminOrUser.add(isAdmin);
        adminOrUser.add(isUser);
        adminOrUserPanel.add(isAdmin);
        adminOrUserPanel.add(isUser);
        var resetButton = new JButton("清空已填信息");
        var defineButton = new JButton("确认添加账户");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        name.setLocation(15, 35);
        password.setLocation(220, 35);
        birthday.setLocation(440, 35);
        var genderPanel = new JPanel();
        var genderTipLabel = new JLabel("性别：");
        genderTipLabel.setBounds(0, 0, 60, 30);
        genderTipLabel.setFont(InputTextPanel.defaultFont);
        genderPanel.add(genderTipLabel);
        genderButtonGroup = new ButtonGroupWithNum();
        genderButtonGroup.setSelectedButtonNum(1);
        genderPanel.setBounds(15, 100, 200, 30);
        genderPanel.setOpaque(false);
        JRadioButton isMale = new JRadioButton("男");
        isMale.setBounds(60, 0, 45, 30);
        isMale.setFont(adminOrUserFont);
        isMale.setOpaque(false);
        isMale.setSelected(true);
        isMale.setFocusPainted(false);
        isMale.addActionListener(e -> genderButtonGroup.setSelectedButtonNum(1));
        JRadioButton isFemale = new JRadioButton("女");
        isFemale.setBounds(105, 0, 45, 30);
        isFemale.setFont(adminOrUserFont);
        isFemale.setOpaque(false);
        isFemale.setSelected(false);
        isFemale.setFocusPainted(false);
        isFemale.addActionListener(e -> genderButtonGroup.setSelectedButtonNum(2));
        genderButtonGroup.add(isMale);
        genderButtonGroup.add(isFemale);
        genderPanel.add(isMale);
        genderPanel.add(isFemale);
        phone.setLocation(220, 90);
        email.setLocation(440, 90);
        resetButton.setBounds(410, 145, 130, 35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570, 145, 130, 35);
        defineButton.addActionListener(e -> {
            if (adminOrUser.getSelectedButtonNum() == 1) {
                //管理员
                var inputTextHandle = new InputTextHandle(name.getInputText(), password.getInputText());
                if (inputTextHandle.superUserCheckInputText() == 0) {
                    int adminAccountNum = userServiceImpl.superUserRegister(inputTextHandle.getName(), inputTextHandle.getPassword());
                    if (adminAccountNum != -1) {
                        String[] columnName = new String[]{"管理员账号", "用户名", "密码"};
                        String[][] rowData = new String[][]{{String.valueOf(adminAccountNum), inputTextHandle.getName(), inputTextHandle.getPassword()}};
                        //暂时直接从界面上显示注册内容
                        resultTipLabel.setVisible(true);
                        resultTable.setModel(new DefaultTableModel(rowData, columnName));
                    } else {
                        popMessageLabel.setText("用户名已被使用，请修改用户名");
                        popMessageDialog.setVisible(true);
                    }
                } else {
                    popMessageLabel.setText("填写信息存在错误，请按照规定格式填写");
                    popMessageDialog.setVisible(true);
                }
            } else {
                //用户
                var inputTextHandle = new InputTextHandle(name.getInputText(), password.getInputText(),
                        genderButtonGroup.getSelectedButtonNum(), phone.getInputText(),
                        email.getInputText(), birthday.getInputText());
                if (inputTextHandle.checkInputText() == 0) {
                    UserInfo.Gender tmpGender;
                    if (genderButtonGroup.getSelectedButtonNum() == 1) {
                        tmpGender = UserInfo.Gender.MALE;
                    } else {
                        tmpGender = UserInfo.Gender.FEMALE;
                    }
                    String[] resultBirthday = inputTextHandle.getBirthday().split("-");
                    Calendar cal = Calendar.getInstance();
                    int year = Integer.parseInt(resultBirthday[0]) - 1900;
                    int month = Integer.parseInt(resultBirthday[1]) - 1;
                    int day = Integer.parseInt(resultBirthday[2]);
                    Date birthday = new Date(year, month, day);
                    int userAccountNum = userServiceImpl.register(inputTextHandle.getName(), inputTextHandle.getPassword(),
                            inputTextHandle.getName(), birthday, tmpGender, inputTextHandle.getPhoneNumber(),
                            inputTextHandle.getEmailAddress());
                    if (userAccountNum != -1) {
                        String genderString = "男";
                        if (tmpGender == UserInfo.Gender.FEMALE) {
                            genderString = "女";
                        }
                        String[] columnName = new String[]{"账号", "用户名", "密码", "出生日期", "性别", "手机号", "邮箱"};
                        String[][] rowData = new String[][]{{String.valueOf(userAccountNum), inputTextHandle.getName(), inputTextHandle.getPassword(),
                                year + 1900 + "-" + month + 1 + "-" + day, genderString, inputTextHandle.phoneNumber, inputTextHandle.emailAddress}};
                        //暂时直接从界面上显示注册内容
                        resultTipLabel.setVisible(true);
                        resultTable.setModel(new DefaultTableModel(rowData, columnName));
                    } else {
                        popMessageLabel.setText("用户名已被使用，请修改用户名");
                        popMessageDialog.setVisible(true);
                    }
                } else {
                    popMessageDialog.setVisible(true);
                }
            }
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(name);
        inputBottomPanel.add(password);
        inputBottomPanel.add(birthday);
        inputBottomPanel.add(genderPanel);
        inputBottomPanel.add(phone);
        inputBottomPanel.add(email);
        inputBottomPanel.add(adminOrUserPanel);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

        String[][] rowData = new String[][]{};
        String[] columnName = new String[]{"账号", "用户名", "密码", "年龄", "性别", "手机号", "邮箱"};
        var resultDisplayPanel = new JPanel();
        resultDisplayPanel.setBounds(40, 280, 720, 300);
        resultDisplayPanel.setBackground(Color.WHITE);
        resultDisplayPanel.setLayout(null);
        this.add(resultDisplayPanel);
        var resultTipPanel = new JPanel();
        resultTipPanel.setBounds(0, 0, 720, 50);
        resultTipPanel.setBackground(Color.WHITE);
        resultDisplayPanel.add(resultTipPanel);
        resultTipLabel.setVisible(false);
        resultTipLabel.setFont(new Font("宋体",Font.PLAIN,20));
        resultTipPanel.add(resultTipLabel);
        var resultScrollPane = new JScrollPane(resultTable);
        resultScrollPane.setBounds(0, 50, 720, 250);
        resultScrollPane.getViewport().setBackground(Color.WHITE);
        resultDisplayPanel.add(resultScrollPane);
    }

    public void resetInputContent() {
        name.setInputText("");
        password.setInputText("");
        birthday.setInputText("");
        phone.setInputText("");
        email.setInputText("");
        resultTipLabel.setVisible(false);
    }
    @SuppressWarnings("unused")
    private static class InputTextHandle {
        private String name;
        private String password;
        //        private String passwordAgain;
        private int gender;
        private String phoneNumber;
        private String emailAddress;
        private String birthday;
        private final static int RIGHT = 0;
        private final static int WRONG_NAME_SHORT = 1;
        private final static int WRONG_NAME_LONG = 2;
        private final static int WRONG_EMPTY_PASSWORD = 3;
        private final static int WRONG_PASSWORD_NOT_SAME = 4;
        private final static int WRONG_PASSWORD_LENGTH = 5;
        private final static int WRONG_PASSWORD_UPPER = 6;
        private final static int WRONG_PASSWORD_SPECIAL = 7;
        private final static int WRONG_GENDER = 8;
        private final static int WRONG_PHONE = 9;
        private final static int WRONG_EMAIL = 10;
        private final static int WRONG_BIRTHDAY = 11;

        public InputTextHandle() {
        }

        public InputTextHandle(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public InputTextHandle(String name, String password, int gender, String phoneNumber, String emailAddress, String birthday) {
            this.name = name;
            this.password = password;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
            this.birthday = birthday;
        }

        public int checkInputText() {
            if (!isNameNotShorter()) return WRONG_NAME_SHORT;
            if (!isNameNotLonger()) return WRONG_NAME_LONG;
            if (isEmptyPassword()) return WRONG_EMPTY_PASSWORD;
            if (!isTrueLengthPassword()) return WRONG_PASSWORD_LENGTH;
            if (!isHaveUpperCharacterPassword()) return WRONG_PASSWORD_UPPER;
            if (!isHaveSpecialCharacterPassword()) return WRONG_PASSWORD_SPECIAL;
            if (!isRightGender()) return WRONG_GENDER;
            if (!isRightPhoneNumber()) return WRONG_PHONE;
            if (!isRightEmail()) return WRONG_EMAIL;
            if (!isRightBirthday()) return WRONG_BIRTHDAY;
            return RIGHT;
        }

        public int superUserCheckInputText() {
            if (!isNameNotShorter()) return WRONG_NAME_SHORT;
            if (!isNameNotLonger()) return WRONG_NAME_LONG;
            if (isEmptyPassword()) return WRONG_EMPTY_PASSWORD;
            if (!isTrueLengthPassword()) return WRONG_PASSWORD_LENGTH;
            if (!isHaveUpperCharacterPassword()) return WRONG_PASSWORD_UPPER;
            if (!isHaveSpecialCharacterPassword()) return WRONG_PASSWORD_SPECIAL;
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

//        public boolean isSamePassWord() {
//            if (password == null) {
//                return false;
//            }
//            return password.equals(passwordAgain);
//        }

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

        public boolean isRightBirthday() {
            if (!Pattern.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", birthday)) {
                return false;
            }
            String[] result = birthday.split("-");
            if (result.length != 3) {
                return false;
            }
            Calendar cal = Calendar.getInstance();
            int nowYear = cal.get(Calendar.YEAR);
            int nowMonth = cal.get(Calendar.MONTH);
            int nowDay = cal.get(Calendar.DATE);
            int year = Integer.parseInt(result[0]);
            int month = Integer.parseInt(result[1]);
            int day = Integer.parseInt(result[2]);
            //年月日不大于当前日期
            if (year > nowYear || (year == nowYear && month > nowMonth) || (year == nowYear && month == nowMonth && day > nowDay)) {
                return false;
            }
            //除2月以外日正确
            if (month < 1 || month > 12 || ((month == 4 || month == 6 || month == 9 || month == 11) && (day < 1 || day > 30)) ||
                    ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (day < 1 || day > 31))) {
                return false;
            }
            //判断闰年
            if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
                return month != 2 || (day >= 1 && day <= 29);
            } else {
                return month != 2 || (day >= 1 && day <= 28);
            }
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

//        public String getPasswordAgain() {
//            return passwordAgain;
//        }
//
//        public void setPasswordAgain(String passwordAgain) {
//            this.passwordAgain = passwordAgain;
//        }

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}