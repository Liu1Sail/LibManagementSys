package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.table.NonResultTableModel;
import com.qdu.niit.library.service.impl.BookServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private final InputTextPanel bookLocation;
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl();
    private final JDialog popMessageDialog;
    private static final NonResultTableModel nonResultTableModel = new NonResultTableModel();
    private static final AddSuccessTableModel addSuccessTableModel=new AddSuccessTableModel();

    public AdminCenterBookAddPanel(Frame frame) {
        JTable resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("添加图书信息失败，请检查数据库是否连接");
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
        var titleLabel = new JLabel("新增图书信息");
        name = new InputTextPanel("书名：");
        isbn = new InputTextPanel("ISBN：");
        author = new InputTextPanel("作者：");
        publisher = new InputTextPanel("出版社：");
        pubTime = new InputTextPanel("出版时间：");
        genre = new InputTextPanel("类型：");
        bookLocation = new InputTextPanel("图书书架编号：");
        var resetButton = new JButton("清空已填信息");
        var defineButton = new JButton("确认添加图书");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        name.setLocation(15, 35);
        isbn.setLocation(220, 35);
        author.setLocation(440, 35);
        publisher.setLocation(15, 90);
        pubTime.setLocation(235, 90);
        genre.setLocation(465, 90);
        bookLocation.setLocation(15, 145);
        resetButton.setBounds(410, 145, 130, 35);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570, 145, 130, 35);
        defineButton.setFocusPainted(false);
        defineButton.addActionListener(e -> {
            //获取信息
            Date nowDate = new Date(Calendar.getInstance().getTimeInMillis());
            var bookInfo = new BookInfo(name.getInputText(), isbn.getInputText(), author.getInputText(),
                    publisher.getInputText(), genre.getInputText(), nowDate, nowDate,
                    bookLocation.getInputText(), "301", true);
            var bookList = new ArrayList<BookInfo>();
            bookList.add(bookInfo);
            try {
                bookServiceImpl.BookAdd(bookList);
                resultTable.setModel(addSuccessTableModel);
            } catch (SQLException ex) {
                popMessageDialog.setVisible(true);
            }
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(name);
        inputBottomPanel.add(isbn);
        inputBottomPanel.add(author);
        inputBottomPanel.add(publisher);
        inputBottomPanel.add(pubTime);
        inputBottomPanel.add(genre);
        inputBottomPanel.add(bookLocation);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

        var resultDisplayPanel=new JPanel();
        resultDisplayPanel.setBounds(40,280,720,300);
        resultDisplayPanel.setBackground(Color.WHITE);
        resultDisplayPanel.setLayout(null);
        this.add(resultDisplayPanel);
        var resultTipPanel=new JPanel();
        resultTipPanel.setBounds(0,0,720,50);
        resultTipPanel.setBackground(Color.WHITE);
        resultDisplayPanel.add(resultTipPanel);
        var resultTipLabel=new JLabel("图书添加成功");
        var resultScrollPane=new JScrollPane(resultTable);
        resultScrollPane.setBounds(0,50,720,250);
        resultScrollPane.getViewport().setBackground(Color.WHITE);
        resultDisplayPanel.add(resultScrollPane);
    }

    public void resetInputContent() {
        name.setInputText("");
        isbn.setInputText("");
        author.setInputText("");
        publisher.setInputText("");
        pubTime.setInputText("");
        genre.setInputText("");
        bookLocation.setInputText("");
    }
    private static class AddSuccessTableModel extends DefaultTableModel {
        private static final String[][] rowData =new String[][]{{"添加成功"}};
        private static final String[] columnName=new String[]{"无"};
        public AddSuccessTableModel(){
            super(rowData,columnName);
        }
    }
}
