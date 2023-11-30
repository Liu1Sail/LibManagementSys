package com.qdu.niit.library.gui.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 修改个人信息面板，仅用于用户界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterAccountChangeInfoPanel extends centerPanelModel {
    private final InputTextPanel id;
    private final InputTextPanel name;
    private final InputTextPanel password;
    private final InputTextPanel age;
    private final InputTextPanel gender;
    private final InputTextPanel phone;
    private final InputTextPanel email;

    public UserCenterAccountChangeInfoPanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("添加账户");
        id = new InputTextPanel("账号：");
        name = new InputTextPanel("用户名：");
        password = new InputTextPanel("密码：");
        age = new InputTextPanel("年龄：");
        gender = new InputTextPanel("性别：");
        phone = new InputTextPanel("手机号：");
        email = new InputTextPanel("邮箱：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认添加账户");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        id.setLocation(15, 35);
        name.setLocation(220, 35);
        password.setLocation(440, 35);
        age.setLocation(15, 90);
        gender.setLocation(235, 90);
        phone.setLocation(440, 90);
        email.setLocation(15,145);
        resetButton.setBounds(410,145,130,35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570,145,130,35);
        defineButton.addActionListener(e -> {
            //获取信息
            resetInputContent();
            //SwingWorker调用中间层
            //得到结果后显示在结果显示区域，并将相同图书信息也显示在结果显示区域
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(id);
        inputBottomPanel.add(name);
        inputBottomPanel.add(password);
        inputBottomPanel.add(age);
        inputBottomPanel.add(gender);
        inputBottomPanel.add(phone);
        inputBottomPanel.add(email);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);
        String[][] rowData=new String[][]{};
        String[] columnName=new String[]{"账号","用户名","密码","年龄","性别","手机号","邮箱"};
        var resultBottomPanel=new ResultDisplayArea(rowData,columnName);
        DefaultTableModel resultTableModel=resultBottomPanel.getTextTableModel();
        this.add(resultBottomPanel);
    }
    public void resetInputContent(){
        id.setInputText("");
        name.setInputText("");
        password.setInputText("");
        age.setInputText("");
        gender.setInputText("");
        phone.setInputText("");
    }
}
