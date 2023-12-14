package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;
import com.qdu.niit.library.gui.animation.TextEmergeLabel;
import com.qdu.niit.library.gui.animation.Translation;
import com.qdu.niit.library.gui.input.InputInnerPasswordField;
import com.qdu.niit.library.gui.input.InputInnerTextField;
import com.qdu.niit.library.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;
/**
 * 目标修改，初始时显示当前个人信息
 */

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 修改个人信息面板，仅用于用户界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterAccountChangeInfoPanel extends centerPanelModel {
    private String id;
    private String name;
    private String password;
    private String age;
    private String gender;
    private String phone;
    private String email;
    private final JFrame frame;
    private int genderNumber = 1;
    private final UserServiceImpl userServiceImpl=UserServiceImpl.getInstance();
    private final User user;

    public UserCenterAccountChangeInfoPanel(JFrame frame,User user) {
        this.frame = frame;
        this.user=user;
        this.setBounds(0, 0, 800, 640);
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 560);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        var titleLabel = new JLabel("修改个人信息");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        inputBottomPanel.add(titleLabel);
        var tipText = new JLabel("请输入新的个人信息，不需要修改的信息请跳过");
        tipText.setBounds(10, 50, 400, 30);
        tipText.setFont(new Font("宋体", Font.PLAIN, 18));
        inputBottomPanel.add(tipText);
        var bodyPanel = getBodyPanel();
        inputBottomPanel.add(bodyPanel);
        this.add(inputBottomPanel);
    }

    private JPanel getBodyPanel() {
        var inputTextHandle = new InputTextHandle();
        HashMap<Integer, Translation> componentMap = new HashMap<>();
        var bodyPanel = new JPanel();
        bodyPanel.setBounds(0, 20, 400, 560);
        bodyPanel.setOpaque(false);
        bodyPanel.setLayout(null);
        bodyPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                bodyPanel.requestFocusInWindow();
            }
        });

        var popMessage = new JDialog(frame, true);
        popMessage.setLocationRelativeTo(null);
        popMessage.setSize(200, 100);
        popMessage.setTitle("错误提示");
        popMessage.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessage.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("输入信息存在错误,请修改信息");
        popMessage.add(popMessageLabel, BorderLayout.CENTER);

        var inputBorderColor = new Color(84, 157, 248);
        var inputInnerTextColor = new Color(153, 153, 153);
        var inputBackgroundColor = new Color(229, 244, 251);
        var buttonBackColor = new Color(0, 133, 255);
        var rightInputColor = new Color(4, 115, 18);
        var inputInnerTextDefaultFont = new Font("宋体", Font.PLAIN, 14);
        var nameInput = new InputInnerTextField("用户名", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (!isInitial) {
                    for (int i = 3; i <= 15; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                }
                TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                tip.setForeground(inputInnerTextColor);
                tip.setText("用户名限制3-20个字符");
                for (int i = 3; i <= 15; i++) {
                    var tmp = componentMap.get(i);
                    tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                }
                tip.setVisible(true);
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("用户名")) {
                    isInitial = false;
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    tip.setForeground(Color.RED);
                    tip.setText("用户名不能为空");
                    tip.setVisible(true);
                } else {
                    isInitial = false;
                    inputTextHandle.setName(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(2).getTargetComponent();
                    if (!inputTextHandle.isNameNotShorter()) {
                        tip.setForeground(Color.RED);
                        tip.setText("用户名过短");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isNameNotLonger()) {
                        tip.setForeground(Color.RED);
                        tip.setText("用户名过长");
                        tip.setVisible(true);
                    } else {
                        tip.setForeground(rightInputColor);
                        tip.setText("用户名正确！");
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
                    for (int i = 5; i <= 15; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                }
                TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                tip.setForeground(inputInnerTextColor);
                tip.setText("密码限制8-20个字符，至少包含一个大写字母和一个特殊字符");
                for (int i = 5; i <= 15; i++) {
                    var tmp = componentMap.get(i);
                    tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                }
                tip.setVisible(true);
            }

            @Override
            public void lostFocusMovement(InputInnerPasswordField textField) {
                if (new String(textField.getPassword()).equals("密码")) {
                    isInitial = false;
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                    tip.setForeground(Color.RED);
                    tip.setText("密码不能为空");
                    tip.setVisible(true);
                } else {
                    isInitial = false;
                    inputTextHandle.setPassword(new String(textField.getPassword()));
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(4).getTargetComponent();
                    if (!inputTextHandle.isTrueLengthPassword()) {
                        tip.setForeground(Color.RED);
                        tip.setText("密码过长或过短");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isHaveSpecialCharacterPassword()) {
                        tip.setForeground(Color.RED);
                        tip.setText("密码缺少特殊字符");
                        tip.setVisible(true);
                    } else if (!inputTextHandle.isHaveUpperCharacterPassword()) {
                        tip.setForeground(Color.RED);
                        tip.setText("密码缺少大写字母");
                        tip.setVisible(true);
                    } else {
                        tip.setForeground(rightInputColor);
                        tip.setText("密码正确！");
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
                    for (int i = 7; i <= 15; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                }
                TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(6).getTargetComponent();
                for (int i = 7; i <= 15; i++) {
                    var tmp = componentMap.get(i);
                    tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                }
                tip.setForeground(inputInnerTextColor);
                tip.setText("请再次输入密码");
                tip.setVisible(true);
            }

            @Override
            public void lostFocusMovement(InputInnerPasswordField textField) {
                if (new String(textField.getPassword()).equals("密码")) {
                    isInitial = false;
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(6).getTargetComponent();
                    tip.setText("两次输入的密码不一致");
                    tip.setVisible(true);
                } else {
                    isInitial = false;
                    inputTextHandle.setPasswordAgain(new String(textField.getPassword()));
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(6).getTargetComponent();
                    if (!inputTextHandle.isSamePassWord()) {
                        tip.setForeground(Color.RED);
                        tip.setText("两次输入的密码不一致");
                        tip.setVisible(true);
                    } else {
                        tip.setForeground(rightInputColor);
                        tip.setText("密码相同！");
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
        if(userServiceImpl.getUserInfo(user.getUName()).getGender()== UserInfo.Gender.FEMALE){
            genderNumber=2;
            maleButton.setSelected(false);
            femaleButton.setSelected(true);
        }
        var phoneInput = new InputInnerTextField("手机号", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (!isInitial) {
                    for (int i = 11; i <= 15; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                }
                for (int i = 11; i <= 15; i++) {
                    var tmp = componentMap.get(i);
                    tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                }
                TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(10).getTargetComponent();
                tip.setForeground(Color.RED);
                tip.setText("请输入正确的手机号");
                tip.setVisible(false);
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("手机号")) {
                    isInitial = false;
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(10).getTargetComponent();
                    tip.setText("手机号不能为空");
                    tip.setVisible(true);
                } else {
                    isInitial = false;
                    inputTextHandle.setPhoneNumber(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(10).getTargetComponent();
                    if (!inputTextHandle.isRightPhoneNumber()) {
                        tip.setForeground(Color.RED);
                        tip.setText("手机号错误");
                        tip.setVisible(true);
                    } else {
                        tip.setForeground(rightInputColor);
                        tip.setText("手机号正确");
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
                    for (int i = 13; i <= 15; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                }
                TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(12).getTargetComponent();
                for (int i = 13; i <= 15; i++) {
                    var tmp = componentMap.get(i);
                    tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                }
                tip.setForeground(inputInnerTextColor);
                tip.setText("邮箱的格式为*@*.*");
                tip.setVisible(true);
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("邮箱")) {
                    isInitial = false;
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(12).getTargetComponent();
                    tip.setTextColor(Color.RED);
                    tip.setText("邮箱不能为空");
                    tip.setVisible(true);
                } else {
                    isInitial = false;
                    inputTextHandle.setEmailAddress(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(12).getTargetComponent();
                    if (!inputTextHandle.isRightEmail()) {
                        tip.setForeground(Color.RED);
                        tip.setText("邮箱错误");
                        tip.setVisible(true);
                    } else {
                        tip.setForeground(rightInputColor);
                        tip.setText("邮箱正确");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var emailPanel = new Translation(emailInput, 1, 6, 10, Translation.TO_BELOW);
        var emailTip = new TextEmergeLabel("邮箱不能为空", 0.01, 255, 255, Color.RED);
        var emailTipPanel = new Translation(emailTip, 1, 6, 10, Translation.TO_BELOW);

        var birthdayInput = new InputInnerTextField("生日", inputBorderColor, inputBackgroundColor, inputInnerTextColor, Color.BLACK, 5, 5) {
            private boolean isInitial = true;

            @Override
            public void gainFocusMovement(InputInnerTextField textField) {
                if (!isInitial) {
                    for (int i = 15; i <= 15; i++) {
                        var tmp = componentMap.get(i);
                        tmp.setLocation(tmp.getX(), tmp.getY() - 10);
                    }
                }
                TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(14).getTargetComponent();
                for (int i = 15; i <= 15; i++) {
                    var tmp = componentMap.get(i);
                    tmp.setLocation(tmp.getX(), tmp.getY() + 10);
                }
                tip.setForeground(inputInnerTextColor);
                tip.setText("生日的格式例如2001-01-01或2001-11-11");
                tip.setVisible(true);
            }

            @Override
            public void lostFocusMovement(InputInnerTextField textField) {
                if (textField.getText().equals("生日")) {
                    isInitial = false;
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(14).getTargetComponent();
                    tip.setTextColor(Color.RED);
                    tip.setText("生日不能为空");
                    tip.setVisible(true);
                } else {
                    isInitial = false;
                    inputTextHandle.setBirthday(textField.getText());
                    TextEmergeLabel tip = (TextEmergeLabel) componentMap.get(14).getTargetComponent();
                    if (!inputTextHandle.isRightBirthday()) {
                        tip.setForeground(Color.RED);
                        tip.setText("生日错误");
                        tip.setVisible(true);
                    } else {
                        tip.setForeground(rightInputColor);
                        tip.setText("生日正确");
                        tip.setVisible(true);
                    }
                }
            }
        };
        var birthdayPanel = new Translation(birthdayInput, 1, 6, 10, Translation.TO_BELOW);
        var birthdayTip = new TextEmergeLabel("生日不能为空", 0.01, 255, 255, Color.RED);
        var birthdayTipPanel = new Translation(birthdayTip, 1, 6, 10, Translation.TO_BELOW);


        var defineButton = new JButton();
        var defineButtonPanel = new Translation(defineButton, 1, 6, 10, Translation.TO_BELOW);

        namePanel.setBounds(70, 70, 250, 35);
        nameInput.setFont(inputInnerTextDefaultFont);
        nameTipPanel.setBounds(70, 100, 250, 35);
        nameTip.setVisible(false);
        passPanel.setBounds(70, 120, 250, 35);
        passInput.setFont(inputInnerTextDefaultFont);
        passTipPanel.setBounds(70, 150, 360, 35);
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
        maleButton.addActionListener(e -> genderNumber = 1);
        femaleButton.addActionListener(e -> genderNumber = 2);
        phonePanel.setBounds(70, 250, 250, 35);
        phoneInput.setFont(inputInnerTextDefaultFont);
        phoneTipPanel.setBounds(70, 280, 250, 35);
        phoneTip.setVisible(false);
        emailPanel.setBounds(70, 300, 250, 35);
        emailInput.setFont(inputInnerTextDefaultFont);
        emailTipPanel.setBounds(70, 330, 250, 35);
        emailTip.setVisible(false);

        birthdayPanel.setBounds(70, 360, 250, 35);
        birthdayInput.setFont(inputInnerTextDefaultFont);
        birthdayTipPanel.setBounds(70, 390, 250, 35);
        birthdayTip.setVisible(false);

        defineButtonPanel.setBounds(100, 420, 200, 50);
        defineButton.setForeground(Color.WHITE);
        defineButton.setFont(new Font("宋体", Font.BOLD, 20));
        defineButton.setText("立即修改");
        defineButton.setHorizontalAlignment(SwingConstants.CENTER);
        defineButton.setVerticalAlignment(SwingConstants.CENTER);
        defineButton.setBackground(buttonBackColor);
        defineButton.setBorder(null);
        defineButton.setFocusPainted(false);
        defineButton.addActionListener(e -> {
            inputTextHandle.setName(null);
            inputTextHandle.setPassword(null);
            inputTextHandle.setPasswordAgain(null);
            inputTextHandle.setGender(0);
            inputTextHandle.setPhoneNumber(null);
            inputTextHandle.setEmailAddress(null);
            inputTextHandle.setBirthday(null);
            String name=nameInput.getText();
            String password=new String(passInput.getPassword());
            String passRepeat=new String(passRepeatInput.getPassword());
            int gender=genderNumber;
            String phone=phoneInput.getText();
            String email=emailInput.getText();
            String birthday=birthdayInput.getText();
            if(!Objects.equals(name, "用户名")){
                inputTextHandle.setName(name);
            }
            if(!Objects.equals(password, "密码")){
                inputTextHandle.setPassword(password);
            }
            if(!Objects.equals(passRepeat, "重复密码")){
                inputTextHandle.setPasswordAgain(passRepeat);
            }
            if(gender!=0){
                inputTextHandle.setGender(gender);
            }
            if(!Objects.equals(phone, "手机号")){
                inputTextHandle.setPhoneNumber(phone);
            }
            if(!Objects.equals(email, "邮箱")){
                inputTextHandle.setEmailAddress(email);
            }
            if(!Objects.equals(birthday, "生日")){
                inputTextHandle.setBirthday(birthday);
            }
            if (inputTextHandle.checkInputText() == InputTextHandle.RIGHT) {
                if(inputTextHandle.password!=null){
                    userServiceImpl.modifyLocalUserPassword(inputTextHandle.password);
                }
                UserInfo userInfo=userServiceImpl.getUserInfo(user.getUName());
                UserInfo.Gender genderPacked;
                Date birthdayPacked;
                if(userInfo!=null){
                    if(inputTextHandle.getName()==null){
                        name=userInfo.getName();
                    }
                    if(inputTextHandle.getGender()==0){
                        genderPacked=userInfo.getGender();
                    }
                    else{
                        if(inputTextHandle.getGender()==1){
                            genderPacked= UserInfo.Gender.MALE;
                        }
                        else{
                            genderPacked=UserInfo.Gender.FEMALE;
                        }
                    }
                    if(inputTextHandle.getPhoneNumber()==null){
                        phone=userInfo.getPhone();
                    }
                    if(inputTextHandle.getEmailAddress()==null){
                        email=userInfo.getEmail();
                    }
                    if(inputTextHandle.getBirthday()==null){
                        birthdayPacked=userInfo.getBirthday();
                    }
                    else{
                        String[] result = inputTextHandle.getBirthday().split("-");
                        int year=Integer.parseInt(result[0])-1900;
                        int month=Integer.parseInt(result[1])-1;
                        int day=Integer.parseInt(result[2]);
                        birthdayPacked=new Date(Integer.parseInt(result[0])-1900,Integer.parseInt(result[1])-1,Integer.parseInt(result[2]));
                    }
                    userServiceImpl.modifyLocalUserInfo(name,birthdayPacked,genderPacked,phone,email);
                }
                else{
                    popMessage.setTitle("修改失败");
                    popMessageLabel.setText("修改因系统问题失败，请稍后重试");
                    popMessage.setVisible(true);
                    frame.dispose();
                }
            } else {
                popMessage.setTitle("修改失败");
                popMessageLabel.setText("输入信息存在错误,请修改信息");
                popMessage.setVisible(true);
            }
        });

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
        componentMap.put(13, birthdayPanel);
        componentMap.put(14, birthdayTipPanel);
        componentMap.put(15, defineButtonPanel);

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
        bodyPanel.add(birthdayPanel);
        bodyPanel.add(birthdayTipPanel);
        bodyPanel.add(defineButtonPanel);
        return bodyPanel;
    }

    private static class InputTextHandle {
        private String name;
        private String password;
        private String passwordAgain;
        private int gender = 0;
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
            if (name != null) {
                if (!isNameNotShorter()) return WRONG_NAME_SHORT;
                if (!isNameNotLonger()) return WRONG_NAME_LONG;
            }
            if (password != null) {
                if (isEmptyPassword()) return WRONG_EMPTY_PASSWORD;
                if (!isSamePassWord()) return WRONG_PASSWORD_NOT_SAME;
                if (!isTrueLengthPassword()) return WRONG_PASSWORD_LENGTH;
                if (!isHaveUpperCharacterPassword()) return WRONG_PASSWORD_UPPER;
                if (!isHaveSpecialCharacterPassword()) return WRONG_PASSWORD_SPECIAL;
            }
            if (gender != 0 && !isRightGender()) return WRONG_GENDER;
            if (phoneNumber != null && !isRightPhoneNumber()) return WRONG_PHONE;
            if (emailAddress != null && !isRightEmail()) return WRONG_EMAIL;
            if (birthday != null && !isRightBirthday()) return WRONG_BIRTHDAY;
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

        @Override
        public String toString() {
            return "InputTextHandle{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", passwordAgain='" + passwordAgain + '\'' +
                    ", gender=" + gender +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", emailAddress='" + emailAddress + '\'' +
                    ", birthday='" + birthday + '\'' +
                    '}';
        }
    }
}
