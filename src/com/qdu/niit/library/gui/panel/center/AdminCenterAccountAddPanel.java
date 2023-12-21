package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.gui.input.InputPasswordPanel;
import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.panel.ResultDisplayArea;
import com.qdu.niit.library.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
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
    private final InputTextPanel age;
    private final InputTextPanel gender;
    private final InputTextPanel phone;
    private final InputTextPanel email;
    private final JRadioButton isAdmin;
    private final JRadioButton isUser;
    private final ButtonGroupWithNum adminOrUser;
    private final UserServiceImpl userServiceImpl=UserServiceImpl.getInstance();
    public AdminCenterAccountAddPanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("添加账户");
        name = new InputTextPanel("用户名：");
        password = new InputPasswordPanel("密码：");
        age = new InputTextPanel("年龄：");
        gender = new InputTextPanel("性别：");
        phone = new InputTextPanel("手机号：");
        email = new InputTextPanel("邮箱：");
        var adminOrUserFont=new Font("宋体",Font.PLAIN,16);
        var adminOrUserPanel=new JPanel();
        adminOrUser =new ButtonGroupWithNum();
        adminOrUserPanel.setBounds(200,155,200,30);
        adminOrUserPanel.setOpaque(false);
        isAdmin=new JRadioButton("管理员");
        isAdmin.setBounds(0,0,80,30);
        isAdmin.setFont(adminOrUserFont);
        isAdmin.setOpaque(false);
        isAdmin.setSelected(true);
        isAdmin.addActionListener(e -> adminOrUser.setSelectedButtonNum(1));
        isUser=new JRadioButton("普通用户");
        isUser.setBounds(80,0,90,30);
        isUser.setFont(adminOrUserFont);
        isUser.setOpaque(false);
        isUser.setSelected(false);
        isUser.addActionListener(e -> adminOrUser.setSelectedButtonNum(2));
        adminOrUser.add(isAdmin);
        adminOrUser.add(isUser);
        adminOrUserPanel.add(isAdmin);
        adminOrUserPanel.add(isUser);
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认添加账户");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        name.setLocation(15, 35);
        password.setLocation(220, 35);
        age.setLocation(15, 90);
        gender.setLocation(235, 90);
        phone.setLocation(440, 90);
        email.setLocation(15,145);
        resetButton.setBounds(410,145,130,35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570,145,130,35);
        defineButton.addActionListener(e -> {
            //获取信息
            //管理员
            if(adminOrUser.getSelectedButtonNum()==1){

            }
            else{
                //用户
//                var inputTextHandle=new InputTextHandle(name.getInputText(),password.getInputText(),gender.getInputText(),
//                        );
//                userServiceImpl.register();
            }
            resetInputContent();
            //得到结果查找一次，显示在结果显示区域，并将相同图书信息也显示在结果显示区域
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(name);
        inputBottomPanel.add(password);
        inputBottomPanel.add(age);
        inputBottomPanel.add(gender);
        inputBottomPanel.add(phone);
        inputBottomPanel.add(email);
        inputBottomPanel.add(adminOrUserPanel);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);
        String[][] rowData=new String[][]{};
        String[] columnName=new String[]{"账号","用户名","密码","年龄","性别","手机号","邮箱"};
        var resultBottomPanel=new ResultDisplayArea(rowData,columnName);
        DefaultTableModel resultTableModel=resultBottomPanel.getTextTableModel();
        this.add(resultBottomPanel);
    }
    public void resetInputContent(){
        name.setInputText("");
        password.setInputText("");
        age.setInputText("");
        gender.setInputText("");
        phone.setInputText("");
        email.setInputText("");
    }
    private static class ButtonGroupWithNum extends ButtonGroup{
        private int selectedButtonNum=1;

        public int getSelectedButtonNum() {
            return selectedButtonNum;
        }

        public void setSelectedButtonNum(int selectedButtonNum) {
            this.selectedButtonNum = selectedButtonNum;
        }
    }
    @SuppressWarnings("unused")
    private static class InputTextHandle {
        private String name;
        private String password;
        private String passwordAgain;
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
            if (isEmptyPassword()) return WRONG_EMPTY_PASSWORD;
            if (!isTrueLengthPassword()) return WRONG_PASSWORD_LENGTH;
            if (!isHaveUpperCharacterPassword()) return WRONG_PASSWORD_UPPER;
            if (!isHaveSpecialCharacterPassword()) return WRONG_PASSWORD_SPECIAL;
            if (!isRightGender()) return WRONG_GENDER;
            if (!isRightPhoneNumber()) return WRONG_PHONE;
            if (!isRightEmail()) return WRONG_EMAIL;
            if(!isRightBirthday()) return WRONG_BIRTHDAY;
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
            if (password == null) {
                return false;
            }
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

        public boolean isRightBirthday() {
            if (!Pattern.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", birthday)) {
                return false;
            }
            String[] result = birthday.split("-");
            if (result.length != 3) {
                return false;
            }
            Calendar cal = Calendar.getInstance();
            int nowYear=cal.get(Calendar.YEAR);
            int nowMonth=cal.get(Calendar.MONTH);
            int nowDay=cal.get(Calendar.DATE);
            int year=Integer.parseInt(result[0]);
            int month=Integer.parseInt(result[1]);
            int day=Integer.parseInt(result[2]);
            //年月日不大于当前日期
            if(year>nowYear||(year==nowYear&&month>nowMonth)||(year==nowYear&&month==nowMonth&&day>nowDay)){
                return false;
            }
            //除2月以外日正确
            if(month<1||month>12||((month==4||month==6||month==9||month==11)&&(day<1||day>30))||
                    ((month==1||month==3||month==5||month==7||month==8||month==10||month==12)&&(day<1||day>31)))
            {
                return false;
            }
            //判断闰年
            if(year%400==0||(year%100!=0&&year%4==0)){
                return month != 2 || (day >= 1 && day <= 29);
            }
            else{
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}