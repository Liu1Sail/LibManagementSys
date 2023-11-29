package com.qdu.niit.library.gui.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书删除面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */

public class AdminCenterBookDeletePanel extends centerPanelModel {
    private final AdminCenterBookDeletePanel frame = this;
    private final InputTextPanel bookId;

    public AdminCenterBookDeletePanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("删除图书信息");
        bookId=new InputTextPanel("待删除图书编号：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认删除图书");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        bookId.setLocation(100,50);
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
        inputBottomPanel.add(bookId);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);
        Object[][] rowData=new Object[][][][]{};
        String[] columnName=new String[]{"图书编号","书名","ISBN","作者","出版社","出版时间"};
        var resultBottomPanel=new ResultDisplayArea(rowData,columnName);
        DefaultTableModel resultTableModel=resultBottomPanel.getTextTableModel();
        this.add(resultBottomPanel);
        //测试代码区域

        resultTableModel.addRow(new Object[]{"1"});
        //
    }
    public void resetInputContent(){
        bookId.setInputText("");
    }
}
