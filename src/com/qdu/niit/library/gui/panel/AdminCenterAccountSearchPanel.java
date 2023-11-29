package com.qdu.niit.library.gui.panel;

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
    private final AdminCenterAccountSearchPanel frame = this;
    private final InputPanel id;
    private final InputPanel name;

    public AdminCenterAccountSearchPanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("搜索账户");
        id = new InputPanel("账号：");
        name = new InputPanel("用户名：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认添加账户");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        id.setLocation(15, 35);
        name.setLocation(220, 35);

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
    }
}
