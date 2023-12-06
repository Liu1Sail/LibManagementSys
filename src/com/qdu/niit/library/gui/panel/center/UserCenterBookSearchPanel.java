package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.panel.ResultDisplayArea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书搜索面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterBookSearchPanel extends centerPanelModel {
    private final InputTextPanel bookIdInput;
    private final InputTextPanel bookKeyWordInput;
    private final InputTextPanel bookStorageTimeInput;
    private int nowTabNum=1;

    public UserCenterBookSearchPanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);

        var titleLabel = new JLabel("搜索图书信息");
        bookIdInput=new InputTextPanel("作者：");
        bookIdInput.setLocation(100,50);
        bookKeyWordInput=new InputTextPanel("书名：");
        bookKeyWordInput.setLocation(100,50);
        bookStorageTimeInput=new InputTextPanel("作者和书名：");
        bookStorageTimeInput.setLocation(100,50);
        inputBottomPanel.add(bookIdInput);

        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("搜索");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
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
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

        //结果显示区域
        String[][] rowData=new String[][]{};
        String[] columnName=new String[]{"图书编号","书名","ISBN","作者","出版社","出版时间","类型"};
        var resultBottomPanel=new ResultDisplayArea(rowData,columnName);
        DefaultTableModel resultTableModel=resultBottomPanel.getTextTableModel();
        this.add(resultBottomPanel);

        //搜索切换选项卡
        var tabNameFont=new Font("宋体",Font.PLAIN,15);

        var bookIdPanel=new JPanel();
        bookIdPanel.setBounds(150,5,80,25);
        bookIdPanel.setBackground(new Color(164, 232, 255));
        var bookIdPanelText=new JLabel("作者查询");
        bookIdPanelText.setBounds(0,0,80,25);
        bookIdPanelText.setFont(tabNameFont);
        bookIdPanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookIdPanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookIdPanel.add(bookIdPanelText);
        inputBottomPanel.add(bookIdPanel);
        bookIdPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookIdInput);
                inputBottomPanel.repaint();
                nowTabNum=1;
            }
        });

        var bookKeyWordPanel=new JPanel();
        bookKeyWordPanel.setBounds(230,5,80,25);
        bookKeyWordPanel.setBackground(new Color(164, 232, 255));
        var bookKeyWordPanelText=new JLabel("书名查询");
        bookKeyWordPanelText.setBounds(0,0,80,25);
        bookKeyWordPanelText.setFont(tabNameFont);
        bookKeyWordPanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookKeyWordPanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookKeyWordPanel.add(bookKeyWordPanelText);
        inputBottomPanel.add(bookKeyWordPanel);
        bookKeyWordPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookKeyWordInput);
                inputBottomPanel.repaint();
                nowTabNum=2;
            }
        });

        var bookStorageTimePanel=new JPanel();
        bookStorageTimePanel.setBounds(310,5,80,25);
        bookStorageTimePanel.setBackground(new Color(164, 232, 255));
        var bookStorageTimePanelText=new JLabel("作者和书名");
        bookStorageTimePanelText.setBounds(0,0,80,25);
        bookStorageTimePanelText.setFont(tabNameFont);
        bookStorageTimePanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookStorageTimePanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookStorageTimePanel.add(bookStorageTimePanelText);
        inputBottomPanel.add(bookStorageTimePanel);
        bookStorageTimePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookStorageTimeInput);
                inputBottomPanel.repaint();
                nowTabNum=3;
            }
        });
    }
    public void resetInputContent(){
        bookIdInput.setInputText("");
        bookKeyWordInput.setInputText("");
        bookStorageTimeInput.setInputText("");
    }
    public void removeNowTab(JPanel inputBottomPanel){
        switch (nowTabNum){
            case 1->inputBottomPanel.remove(bookIdInput);
            case 2->inputBottomPanel.remove(bookKeyWordInput);
            case 3->inputBottomPanel.remove(bookStorageTimeInput);
        }
    }
}
