package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.panel.ResultDisplayArea;
import com.qdu.niit.library.gui.tool.ButtonGroupWithNum;
import com.qdu.niit.library.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 账户删除面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class AdminCenterAccountDeletePanel extends centerPanelModel {
    private final InputTextPanel id;
    private final ButtonGroupWithNum adminOrUser;
    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    private final JDialog popMessageDialog;

    public AdminCenterAccountDeletePanel(JFrame frame) {
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
        var titleLabel = new JLabel("删除账户");
        id = new InputTextPanel("账号：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认删除账户");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        id.setLocation(15, 35);
        var adminOrUserFont = new Font("宋体", Font.PLAIN, 16);
        var adminOrUserPanel = new JPanel();
        adminOrUser = new ButtonGroupWithNum();
        adminOrUser.setSelectedButtonNum(2);
        adminOrUserPanel.setBounds(15, 80, 200, 30);
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
        inputBottomPanel.add(adminOrUserPanel);
        resetButton.setBounds(410,145,130,35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570,145,130,35);
        defineButton.addActionListener(e -> {
            //获取信息
            resetInputContent();
            if (adminOrUser.getSelectedButtonNum() == 1) {
//                userServiceImpl.
            }
            else{
                //用户
            }
            //SwingWorker调用中间层
            //得到结果后显示在结果显示区域，并将相同图书信息也显示在结果显示区域
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(id);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);
    }
    public void resetInputContent(){
        id.setInputText("");
    }
}
