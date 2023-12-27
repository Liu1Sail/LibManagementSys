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
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书修改面板，仅用于管理员界面中心面板。
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class AdminCenterBookModifyPanel extends centerPanelModel {
    private final InputTextPanel copyId;
    private final InputTextPanel name;
    private final InputTextPanel isbn;
    private final InputTextPanel author;
    private final InputTextPanel publisher;
    private final InputTextPanel receiptDate;
    private final InputTextPanel genre;
    private final JDialog popMessageDialog;
    private final NonResultTableModel nonResultTableModel = new NonResultTableModel();
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl();

    public AdminCenterBookModifyPanel(JFrame frame) {
        JTable resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(350, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("查询图书信息失败，请检查数据库是否连接！");
        popMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
        popMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        popMessageLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        popMessageDialog.add(popMessageLabel, BorderLayout.CENTER);
        var popMessageButtonPanel = new JPanel();
        var popMessageButtonExit = new JButton("重试");
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
        var titleLabel = new JLabel("修改图书信息");
        copyId =new InputTextPanel("图书编号：");
        name = new InputTextPanel("书名：");
        isbn = new InputTextPanel("ISBN：");
        author = new InputTextPanel("作者：");
        publisher = new InputTextPanel("出版社：");
        receiptDate = new InputTextPanel("出版时间：");
        genre = new InputTextPanel("类型：");
        var resetButton=new JButton("清空已填信息");
        var defineButton=new JButton("确认修改图书");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        copyId.setLocation(15,35);
        name.setLocation(240,35);
        isbn.setLocation(440,35);
        author.setLocation(15,90);
        publisher.setLocation(235,90);
        receiptDate.setLocation(465,90);
        genre.setLocation(15,145);
        resetButton.setBounds(410,145,130,35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570,145,130,35);
        resultTable.setModel(nonResultTableModel);
        defineButton.addActionListener(e -> {
            //显示修改后信息，点击确认修改按钮，确认修改
            String[][] rowData=new String[][]{{copyId.getInputText(),name.getInputText(),isbn.getInputText(),
            author.getInputText(),publisher.getInputText(), receiptDate.getInputText(),genre.getInputText()}};
            String[] columnName=new String[]{"图书编号","书名","ISBN","作者","出版社","出版时间","类型"};
            resultTable.setModel(new DefaultTableModel(rowData,columnName));
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(copyId);
        inputBottomPanel.add(name);
        inputBottomPanel.add(isbn);
        inputBottomPanel.add(author);
        inputBottomPanel.add(publisher);
        inputBottomPanel.add(receiptDate);
        inputBottomPanel.add(genre);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

        var resultDisplayPanel = new JPanel();
        resultDisplayPanel.setBounds(40, 280, 720, 300);
        resultDisplayPanel.setBackground(Color.WHITE);
        resultDisplayPanel.setLayout(null);
        this.add(resultDisplayPanel);
        var resultButtonPanel = new JPanel();//未设置layout
        resultButtonPanel.setBounds(0, 0, 720, 50);
        resultButtonPanel.setBackground(Color.WHITE);
        resultDisplayPanel.add(resultButtonPanel);
        var confirmButton = new JButton("确认修改");
        confirmButton.setBounds(50, 5, 80, 40);
        resultButtonPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            //确认修改
            String copyIdString=this.copyId.getInputText();
            if(!copyIdString.isEmpty()){
                Integer copyId= Integer.valueOf(copyIdString);
                try {
                    ArrayList<BookInfo> bookInfoList= bookServiceImpl.getBookInfoByCopyID(copyId);
                    Integer bookId=bookInfoList.getFirst().getBook_id();
                    if(!author.getInputText().isEmpty()){
                        bookServiceImpl.updateAuthorByBookID(bookId,author.getInputText());
                    }
                    if(!publisher.getInputText().isEmpty()){
                        bookServiceImpl.updatePublisherByBookID(bookId, publisher.getInputText());
                    }
                    String receiptTime = receiptDate.getInputText();
                    if(!receiptTime.isEmpty()&& Pattern.matches("^[012][0-9]{3}-[0123]{0,1}[0-9]-[0123]{0,1}[0-9]$",receiptTime)){
                        String[] dateStringSplit = receiptTime.split("-");
                        Date inputDate = new Date(Integer.parseInt(dateStringSplit[0]) - 1900,
                                Integer.parseInt(dateStringSplit[1]) - 1,
                                Integer.parseInt(dateStringSplit[2]));
                        bookServiceImpl.updateReceiptDateByBookID(bookId,inputDate);
                    }
                    if(!genre.getInputText().isEmpty()){
                        bookServiceImpl.updateGenreByBookID(bookId,genre.getInputText());
                    }
                } catch (SQLException ex) {
                    popMessageLabel.setText("修改图书信息失败，请检查数据库是否连接！");
                    popMessageDialog.setVisible(true);
                }
                //SwingWorker调用中间层
                //得到结果后显示在结果显示区域，并将相同图书信息也显示在结果显示区域
                resetInputContent();
            }
            else{
                popMessageLabel.setText("未输入图书编号，无法修改，请输入图书编号！");
                popMessageDialog.setVisible(true);
            }
        });
        var resultScrollPane = new JScrollPane(resultTable);
        resultScrollPane.setBounds(0, 50, 720, 250);
        resultScrollPane.getViewport().setBackground(Color.WHITE);
        resultDisplayPanel.add(resultScrollPane);
    }
    public void resetInputContent(){
        name.setInputText("");
        isbn.setInputText("");
        author.setInputText("");
        publisher.setInputText("");
        receiptDate.setInputText("");
        genre.setInputText("");
    }
}
