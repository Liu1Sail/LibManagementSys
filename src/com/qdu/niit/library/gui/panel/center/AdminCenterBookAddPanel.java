package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.panel.ResultDisplayArea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书添加面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class AdminCenterBookAddPanel extends centerPanelModel {
    private final InputTextPanel name;
    private final InputTextPanel isbn;
    private final InputTextPanel author;
    private final InputTextPanel publisher;
    private final InputTextPanel pubTime;
    private final InputTextPanel genre;

    public AdminCenterBookAddPanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("新增图书信息");
        name = new InputTextPanel("书名：");
        isbn = new InputTextPanel("ISBN：");
        author = new InputTextPanel("作者：");
        publisher = new InputTextPanel("出版社：");
        pubTime = new InputTextPanel("出版时间：");
        genre = new InputTextPanel("类型：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认添加图书");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        name.setLocation(15, 35);
        isbn.setLocation(220, 35);
        author.setLocation(440, 35);
        publisher.setLocation(15, 90);
        pubTime.setLocation(235, 90);
        genre.setLocation(465, 90);
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
        inputBottomPanel.add(name);
        inputBottomPanel.add(isbn);
        inputBottomPanel.add(author);
        inputBottomPanel.add(publisher);
        inputBottomPanel.add(pubTime);
        inputBottomPanel.add(genre);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);
        String[][] rowData=new String[][]{};
        String[] columnName=new String[]{"图书编号","书名","ISBN","作者","出版社","出版时间","类型"};
        var resultBottomPanel=new ResultDisplayArea(rowData,columnName);
        DefaultTableModel resultTableModel=resultBottomPanel.getTextTableModel();
        this.add(resultBottomPanel);
    }
    public void resetInputContent(){
        name.setInputText("");
        isbn.setInputText("");
        author.setInputText("");
        publisher.setInputText("");
        pubTime.setInputText("");
        genre.setInputText("");
    }
}