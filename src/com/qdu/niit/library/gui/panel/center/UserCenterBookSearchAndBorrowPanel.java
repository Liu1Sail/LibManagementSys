package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.service.impl.BookServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书搜索和借阅面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterBookSearchAndBorrowPanel extends centerPanelModel {
    private final InputTextPanel bookNameInput;
    private final InputTextPanel bookAuthorInput;
    private final InputTextPanel bookNameAndAuthorNameInput;
    private final InputTextPanel bookNameAndAuthorAuthorInput;
    private int nowTabNum = 1;
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl();
    private final JDialog popMessageDialog;
    private final JTable resultTable=new JTable();
    private final DefaultTableModel resultTableModel=new DefaultTableModel();

    public UserCenterBookSearchAndBorrowPanel(Frame frame) {
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("查询图书信息失败，请检查数据库是否连接");
        popMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
        popMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        popMessageLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        popMessageDialog.add(popMessageLabel, BorderLayout.CENTER);
        var popMessageButtonPanel = new JPanel();
        var popMessageButtonExit = new JButton("退出程序");
        popMessageButtonExit.setPreferredSize(new Dimension(90, 30));
        popMessageButtonExit.setFocusPainted(false);
        popMessageButtonPanel.add(popMessageButtonExit);
        popMessageButtonExit.addActionListener(e -> frame.dispose());
        popMessageDialog.add(popMessageButtonPanel, BorderLayout.SOUTH);
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);

        var titleLabel = new JLabel("搜索借阅图书");
        bookNameInput = new InputTextPanel("书名：");
        bookNameInput.setLocation(100, 50);
        bookAuthorInput = new InputTextPanel("作者：");
        bookAuthorInput.setLocation(100, 50);
        bookNameAndAuthorNameInput = new InputTextPanel("书名：");
        bookNameAndAuthorNameInput.setLocation(50, 50);
        bookNameAndAuthorAuthorInput = new InputTextPanel("作者：");
        bookNameAndAuthorAuthorInput.setLocation(250, 50);
        inputBottomPanel.add(bookNameInput);

        var resetButton = new JButton("清空已填信息");
        var defineButton = new JButton("搜索");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        resetButton.setBounds(410, 145, 130, 35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570, 145, 130, 35);
        defineButton.addActionListener(e -> {
            try {
                switch (nowTabNum) {
                    case 1 -> {
                        var name = bookNameInput.getInputText();
                        bookServiceImpl.getBookInfoByTitle(name);
                    }
                    case 2 -> {
                        var title = bookAuthorInput.getInputText();
                        bookServiceImpl.getBookInfoByTitle(title);
                    }
                    case 3 -> {
                        var name = bookNameAndAuthorNameInput.getInputText();
                        var title = bookNameAndAuthorAuthorInput.getInputText();
                        bookServiceImpl.getBookInfoByAuthorAndTitle(title, name);
                    }
                }

            } catch (SQLException ex) {
                popMessageDialog.setVisible(true);
            }

            //获取信息
            resetInputContent();
            //SwingWorker调用中间层
            //得到结果后显示在结果显示区域，并将相同图书信息也显示在结果显示区域
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

        //结果显示区域
//        String[] columnName = new String[]{"图书编号", "书名", "ISBN", "作者", "出版社", "出版时间", "类型"};
        var resultDisplayPanel=new JPanel();
        resultDisplayPanel.setBounds(40,280,720,300);
        resultDisplayPanel.setBackground(Color.WHITE);
        resultDisplayPanel.setLayout(null);
        var resultButtonPanel=new JPanel();
        resultButtonPanel.setBounds(0,0,720,50);
        resultButtonPanel.setBackground(Color.WHITE);
        resultDisplayPanel.add(resultButtonPanel);
        var borrowButton=new JButton("借阅");
        borrowButton.setBounds(50,5,80,40);
        resultButtonPanel.add(borrowButton);
        var resultScrollPane=new JScrollPane(resultTable);
        resultScrollPane.setBounds(0,50,720,250);
        resultDisplayPanel.add(resultScrollPane);
        this.add(resultDisplayPanel);

        //搜索切换选项卡
        var tabNameFont = new Font("宋体", Font.PLAIN, 15);

        var bookIdPanel = new JPanel();
        bookIdPanel.setBounds(150, 5, 80, 25);
        bookIdPanel.setBackground(new Color(164, 232, 255));
        var bookIdPanelText = new JLabel("作者查询");
        bookIdPanelText.setBounds(0, 0, 80, 25);
        bookIdPanelText.setFont(tabNameFont);
        bookIdPanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookIdPanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookIdPanel.add(bookIdPanelText);
        inputBottomPanel.add(bookIdPanel);
        bookIdPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookNameInput);
                inputBottomPanel.repaint();
                nowTabNum = 1;
            }
        });

        var bookKeyWordPanel = new JPanel();
        bookKeyWordPanel.setBounds(230, 5, 80, 25);
        bookKeyWordPanel.setBackground(new Color(164, 232, 255));
        var bookKeyWordPanelText = new JLabel("书名查询");
        bookKeyWordPanelText.setBounds(0, 0, 80, 25);
        bookKeyWordPanelText.setFont(tabNameFont);
        bookKeyWordPanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookKeyWordPanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookKeyWordPanel.add(bookKeyWordPanelText);
        inputBottomPanel.add(bookKeyWordPanel);
        bookKeyWordPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookAuthorInput);
                inputBottomPanel.repaint();
                nowTabNum = 2;
            }
        });

        var bookNameAndAuthorPanel = new JPanel();
        bookNameAndAuthorPanel.setBounds(310, 5, 80, 25);
        bookNameAndAuthorPanel.setBackground(new Color(164, 232, 255));
        var bookStorageTimePanelText = new JLabel("作者和书名");
        bookStorageTimePanelText.setBounds(0, 0, 80, 25);
        bookStorageTimePanelText.setFont(tabNameFont);
        bookStorageTimePanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookStorageTimePanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookNameAndAuthorPanel.add(bookStorageTimePanelText);
        inputBottomPanel.add(bookNameAndAuthorPanel);
        bookNameAndAuthorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookNameAndAuthorNameInput);
                inputBottomPanel.add(bookNameAndAuthorAuthorInput);
                inputBottomPanel.repaint();
                nowTabNum = 3;
            }
        });
    }

    public void resetInputContent() {
        bookNameInput.setInputText("");
        bookAuthorInput.setInputText("");
        bookNameAndAuthorNameInput.setInputText("");
        bookNameAndAuthorAuthorInput.setInputText("");
    }

    public void removeNowTab(JPanel inputBottomPanel) {
        switch (nowTabNum) {
            case 1 -> inputBottomPanel.remove(bookNameInput);
            case 2 -> inputBottomPanel.remove(bookAuthorInput);
            case 3 -> {
                inputBottomPanel.remove(bookNameAndAuthorNameInput);
                inputBottomPanel.remove(bookNameAndAuthorAuthorInput);
            }
        }
    }
}