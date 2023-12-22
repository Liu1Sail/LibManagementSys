package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.UserInfo;
import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.panel.ResultDisplayArea;
import com.qdu.niit.library.gui.table.NonResultTableModel;
import com.qdu.niit.library.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 账户搜索面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class AdminCenterAccountSearchPanel extends centerPanelModel {
    private final InputTextPanel id;
    private final InputTextPanel name;
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    private final JDialog popMessageDialog;
    private final JLabel resultTipLabel = new JLabel("搜索完成！");
    private final NonResultTableModel nonResultTableModel=new NonResultTableModel();

    public AdminCenterAccountSearchPanel(JFrame frame) {
        String[] columnName = new String[]{"账号", "用户名", "密码", "年龄", "性别", "手机号", "邮箱"};
        JTable resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("系统出现未知原因，请重试");
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
        var titleLabel = new JLabel("搜索账户");
//        var tipLabel=new JLabel("请仅使用账号和用户名中的一项");
        id = new InputTextPanel("账号：");
        name = new InputTextPanel("用户名：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("搜索账户");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
//        tipLabel.setBounds(10,35,300,30);
//        tipLabel.setFont(new Font("宋体",Font.PLAIN,18));
//        id.setLocation(15, 70);
        id.setInputText("");
        name.setLocation(15, 30);

        resetButton.setBounds(410,145,130,35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570,145,130,35);
        defineButton.addActionListener(e -> {
            //获取信息
            String nameInput=name.getInputText();
            String idInput=id.getInputText();
            if(nameInput.isEmpty()){
                if(idInput.isEmpty()){
                    popMessageLabel.setText("请输入信息！");
                    popMessageDialog.setVisible(true);
                }
                else{
                    //id搜索
                }
            }
            else{
                if(idInput.isEmpty()){
                    //用户名搜索
                    UserInfo userInfo=userServiceImpl.getUserInfo(nameInput);
                    if(userInfo!=null){
                        String genderString="男";
                        if(userInfo.getGender()== UserInfo.Gender.FEMALE){
                            genderString="女";
                        }
                        String[][] rowData=new String[][]{{String.valueOf(userInfo.getID()),userInfo.getName(), String.valueOf(userInfo.getBirthday()),
                                genderString,userInfo.getPhone(),userInfo.getEmail()}};
                        resultTable.setModel(new DefaultTableModel(rowData,columnName));
                    }
                    else{
                        resultTable.setModel(nonResultTableModel);
                    }

                }
                else{
                    //优先使用id
                    popMessageLabel.setText("您同时输入了账号和用户名，将使用账号进行搜索");
                    popMessageDialog.setVisible(true);
                }
            }
            resetInputContent();
        });
        inputBottomPanel.add(titleLabel);
//        inputBottomPanel.add(tipLabel);
//        inputBottomPanel.add(id);
        inputBottomPanel.add(name);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

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
    public void resetInputContent(){
        id.setInputText("");
        name.setInputText("");
    }
}
