package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.Book;
import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.table.NonResultTableModel;
import com.qdu.niit.library.service.impl.BookServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书搜索面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class AdminCenterBookSearchPanel extends centerPanelModel {
    private final InputTextPanel copyIdInput;
    private final InputTextPanel bookKeywordInput;
    private final InputTextPanel bookReceiptTimeInput;
    private int nowTabNum = 1;
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl();
    private final JDialog popMessageDialog;
    private static final NonResultTableModel nonResultTableModel = new NonResultTableModel();

    public AdminCenterBookSearchPanel(JFrame frame) {
        JTable resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("搜索图书信息失败，请检查数据库是否连接");
        popMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
        popMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        popMessageLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        popMessageDialog.add(popMessageLabel, BorderLayout.CENTER);
        var popMessageButtonPanel = new JPanel();
        var popMessageButtonExit = new JButton("重试");
        popMessageButtonExit.setPreferredSize(new Dimension(90, 30));
        popMessageButtonExit.setFocusPainted(false);
        popMessageButtonPanel.add(popMessageButtonExit);
        popMessageButtonExit.addActionListener(e->popMessageDialog.setVisible(false));
        popMessageDialog.add(popMessageButtonPanel, BorderLayout.SOUTH);
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        inputBottomPanel.setLayout(null);
        this.add(inputBottomPanel);

        var titleLabel = new JLabel("搜索图书信息");
        copyIdInput = new InputTextPanel("书本编号：");
        copyIdInput.setLocation(100, 50);
        bookKeywordInput = new InputTextPanel("图书全名或关键词：");
        bookKeywordInput.setLocation(100, 50);
        bookReceiptTimeInput = new InputTextPanel("入库时间：");
        bookReceiptTimeInput.setLocation(100, 50);
        inputBottomPanel.add(copyIdInput);

        //搜索切换选项卡
        var tabNameFont = new Font("宋体", Font.PLAIN, 15);

        var copyIdPanel = new JPanel();
        copyIdPanel.setBounds(150, 5, 80, 25);
        copyIdPanel.setBackground(new Color(164, 232, 255));
        var copyIdPanelText = new JLabel("编号查询");
        copyIdPanelText.setBounds(0, 0, 80, 25);
        copyIdPanelText.setFont(tabNameFont);
        copyIdPanelText.setVerticalAlignment(SwingConstants.CENTER);
        copyIdPanelText.setHorizontalAlignment(SwingConstants.CENTER);
        copyIdPanel.add(copyIdPanelText);
        inputBottomPanel.add(copyIdPanel);
        copyIdPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(copyIdInput);
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
                inputBottomPanel.add(bookKeywordInput);
                inputBottomPanel.repaint();
                nowTabNum = 2;
            }
        });

        var bookStorageTimePanel = new JPanel();
        bookStorageTimePanel.setBounds(310, 5, 80, 25);
        bookStorageTimePanel.setBackground(new Color(164, 232, 255));
        var bookStorageTimePanelText = new JLabel("时间查询");
        bookStorageTimePanelText.setBounds(0, 0, 80, 25);
        bookStorageTimePanelText.setFont(tabNameFont);
        bookStorageTimePanelText.setVerticalAlignment(SwingConstants.CENTER);
        bookStorageTimePanelText.setHorizontalAlignment(SwingConstants.CENTER);
        bookStorageTimePanel.add(bookStorageTimePanelText);
        inputBottomPanel.add(bookStorageTimePanel);
        bookStorageTimePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeNowTab(inputBottomPanel);
                inputBottomPanel.add(bookReceiptTimeInput);
                inputBottomPanel.repaint();
                nowTabNum = 3;
            }
        });
        var resetButton = new JButton("清空已填信息");
        var defineButton = new JButton("搜索");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        resetButton.setBounds(410, 145, 130, 35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570, 145, 130, 35);
        defineButton.addActionListener(e -> {
            int isInputEmpty=0;
            try {
                ArrayList<BookInfo> bookList = null;
                ArrayList<Book> bookListBook=null;
                switch (nowTabNum) {
                    case 1 -> {
                        if(!copyIdInput.getInputText().isEmpty()){
                            Integer copyIdInt = Integer.valueOf(copyIdInput.getInputText());
                            bookList = bookServiceImpl.getBookInfoByCopyID(copyIdInt);
                        }
                        else{
                            isInputEmpty=1;
                        }

                    }
                    case 2 -> {
                        String keyword = bookKeywordInput.getInputText();
                        if(!keyword.isEmpty()){
                            bookListBook =  bookServiceImpl.getBookByTitle(keyword);
                        }
                        else{
                            isInputEmpty=1;
                        }

                    }
                    case 3 -> {
                        String receiptTime = bookReceiptTimeInput.getInputText();
                        if(!receiptTime.isEmpty()&& Pattern.matches("^[012][0-9]{3}-[0123]{0,1}[0-9]-[0123]{0,1}[0-9]$",receiptTime)){
                            String[] dateStringSplit = receiptTime.split("-");
                            Date inputDate = new Date(Integer.parseInt(dateStringSplit[0]) - 1900,
                                    Integer.parseInt(dateStringSplit[1]) - 1,
                                    Integer.parseInt(dateStringSplit[2]));
                            bookList = bookServiceImpl.getBookInfoByDate(inputDate);
                        }
                        else{
                            isInputEmpty=1;
                        }
                    }
                }
                if(nowTabNum==1||nowTabNum==3){
                    if(bookList!=null){
                        if(!bookList.isEmpty()){
                            String[][] rowData=new String[bookList.size()][7];
                            String[] column=new String[]{"图书编号","书名","ISBN","作者","出版商","类型","入库时间"};
                            for (int i=0;i<bookList.size();i++){
                                var tmp=bookList.get(i);
                                rowData[i][0]= String.valueOf(tmp.getBook_id());
                                rowData[i][1]=tmp.getTitle();
                                rowData[i][2]=tmp.getIsbn();
                                rowData[i][3]=tmp.getAuthor();
                                rowData[i][4]=tmp.getPublisher();
                                rowData[i][5]=tmp.getGenre();
                                rowData[i][6]= String.valueOf(tmp.getReceipt_date());
                            }
                            resultTable.setModel(new DefaultTableModel(rowData,column));
                        }
                        else{
                            resultTable.setModel(nonResultTableModel);
                        }
                    }
                    else{
                        if(isInputEmpty==1){
                            popMessageLabel.setText("输入为空或不合法，请检查输入信息！");
                            popMessageDialog.setVisible(true);
                        }
                        else{
                            popMessageLabel.setText("搜索图书信息失败，请检查数据库是否连接");
                            popMessageDialog.setVisible(true);
                        }
                    }
                }
                else{
                    if(bookListBook!=null){
                        if(!bookListBook.isEmpty()){
                            String[][] rowData=new String[bookListBook.size()][7];
                            String[] column=new String[]{"图书编号","书名","ISBN","作者","出版商","类型","入库时间"};
                            for (int i=0;i<bookListBook.size();i++){
                                var tmp=bookListBook.get(i);
                                rowData[i][0]= String.valueOf(tmp.getBook_id());
                                rowData[i][1]=tmp.getTitle();
                                rowData[i][2]=tmp.getIsbn();
                                rowData[i][3]=tmp.getAuthor();
                                rowData[i][4]=tmp.getPublisher();
                                rowData[i][5]=tmp.getGenre();
                                rowData[i][6]= String.valueOf(tmp.getReceipt_date());
                            }
                            resultTable.setModel(new DefaultTableModel(rowData,column));
                        }
                        else{
                            resultTable.setModel(nonResultTableModel);
                        }
                        resetInputContent();
                    }
                    else{
                        if(isInputEmpty==1){
                            popMessageLabel.setText("输入为空，请在输入信息后搜索！");
                            popMessageDialog.setVisible(true);
                        }
                        else{
                            popMessageLabel.setText("搜索图书信息失败，请检查数据库是否连接");
                            popMessageDialog.setVisible(true);
                        }
                    }
                }

            } catch (SQLException ex) {
                popMessageLabel.setText("搜索图书信息失败，请检查数据库是否连接");
                popMessageDialog.setVisible(true);
            }
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);
        //结果显示区域
        var resultDisplayPanel = new JPanel();
        resultDisplayPanel.setBounds(40, 280, 720, 300);
        resultDisplayPanel.setBackground(Color.WHITE);
        resultDisplayPanel.setLayout(null);
        this.add(resultDisplayPanel);
        var resultTipPanel = new JPanel();
        resultTipPanel.setBounds(0, 0, 720, 50);
        resultTipPanel.setBackground(Color.WHITE);
        resultDisplayPanel.add(resultTipPanel);
        var resultTipLabel = new JLabel("图书添加成功");
        var resultScrollPane = new JScrollPane(resultTable);
        resultScrollPane.setBounds(0, 50, 720, 250);
        resultScrollPane.getViewport().setBackground(Color.WHITE);
        resultDisplayPanel.add(resultScrollPane);
    }

    public void resetInputContent() {
        copyIdInput.setInputText("");
        bookKeywordInput.setInputText("");
        bookReceiptTimeInput.setInputText("");
    }

    public void removeNowTab(JPanel inputBottomPanel) {
        switch (nowTabNum) {
            case 1 -> inputBottomPanel.remove(copyIdInput);
            case 2 -> inputBottomPanel.remove(bookKeywordInput);
            case 3 -> inputBottomPanel.remove(bookReceiptTimeInput);
        }
    }
}
