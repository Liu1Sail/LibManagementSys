package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.BookInfo;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.exception.ObjectHaveNoAttribute;
import com.qdu.niit.library.gui.input.InputTextPanel;
import com.qdu.niit.library.gui.table.NonResultTableModel;
import com.qdu.niit.library.service.impl.BookServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书删除面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */

public class AdminCenterBookDeletePanel extends centerPanelModel {
    private final InputTextPanel copyId;
    private final JDialog popMessageDialog;
    private final JLabel resultTipLabel = new JLabel("注册成功！");
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl();
    private final NonResultTableModel nonResultTableModel = new NonResultTableModel();


    public AdminCenterBookDeletePanel(JFrame frame) {
        JTable resultTable = new JTable();
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("出现系统错误，删除失败，请重试！");
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
        var titleLabel = new JLabel("删除图书信息");
        copyId = new InputTextPanel("待删除书本编号：");
        var resetButton = new JButton("清空已填信息");
        var defineButton = new JButton("搜索图书");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        copyId.setLocation(100, 50);
        resetButton.setBounds(410, 145, 130, 35);
        resetButton.addActionListener(e -> resetInputContent());
        defineButton.setBounds(570, 145, 130, 35);
        defineButton.addActionListener(e -> {
            //获取信息，搜索图书具体信息，显示，进行确认
            String[] columnName = new String[]{"图书编号", "书名", "ISBN", "作者", "出版商", "入库日期", "类型"};
            try {
                Integer copyIdInt = Integer.parseInt(copyId.getInputText());
                try {
                    ArrayList<BookInfo> bookInfoList = bookServiceImpl.getBookInfoByCopyID(copyIdInt);
                    if (bookInfoList != null) {
                        String[][] rowData = new String[bookInfoList.size()][7];
                        for (int i = 0; i < bookInfoList.size(); i++) {
                            var tmp = bookInfoList.get(i);
                            rowData[i][0] = String.valueOf(tmp.getBook_id());
                            rowData[i][1] = tmp.getTitle();
                            rowData[i][2] = tmp.getIsbn();
                            rowData[i][3] = tmp.getAuthor();
                            rowData[i][4] = tmp.getPublisher();
                            rowData[i][5] = String.valueOf(tmp.getReceipt_date());
                            rowData[i][6] = tmp.getGenre();
                        }
                        resultTable.setModel(new DefaultTableModel(rowData, columnName));
                    } else {
                        resultTable.setModel(nonResultTableModel);
                    }
                } catch (SQLException ex) {
                    popMessageLabel.setText("出现系统错误，删除失败，请重试！");
                    popMessageDialog.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                popMessageLabel.setText("图书编号为空，无法搜索");
            }
        });
        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(copyId);
        inputBottomPanel.add(resetButton);
        inputBottomPanel.add(defineButton);

        var resultDisplayPanel = new JPanel();
        resultDisplayPanel.setBounds(40, 280, 720, 300);
        resultDisplayPanel.setBackground(Color.WHITE);
        resultDisplayPanel.setLayout(null);
        this.add(resultDisplayPanel);
        var resultButtonPanel = new JPanel();
        resultButtonPanel.setBounds(0, 0, 720, 50);
        resultButtonPanel.setBackground(Color.WHITE);
        resultDisplayPanel.add(resultButtonPanel);
        resultTipLabel.setVisible(false);
        resultTipLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        resultButtonPanel.add(resultTipLabel);
        var resultScrollPane = new JScrollPane(resultTable);
        resultScrollPane.setBounds(0, 50, 720, 250);
        resultScrollPane.getViewport().setBackground(Color.WHITE);
        resultDisplayPanel.add(resultScrollPane);
        var borrowButton = new JButton("确认删除");
        borrowButton.setBounds(50, 5, 80, 40);
        borrowButton.addActionListener(e -> {
            Integer copyIdInt = Integer.parseInt(copyId.getInputText());
            var deleteList = new ArrayList<Integer>();
            deleteList.add(copyIdInt);
            try {
                bookServiceImpl.BookDelete(deleteList);

            } catch (SQLException ex) {
                popMessageLabel.setText("出现系统错误，删除失败，请重试！");
                popMessageDialog.setVisible(true);
            }
            resetInputContent();
        });
        resultButtonPanel.add(borrowButton);
        //测试代码区域

        //
    }

    public void resetInputContent() {
        copyId.setInputText("");
    }
    private static class DeleteSuccessTableModel extends DefaultTableModel{
        private static final String[][] rowData =new String[][]{{"删除成功"}};
        private static final String[] columnName=new String[]{"无"};
        public DeleteSuccessTableModel(){
            super(rowData,columnName);
        }
    }
}
