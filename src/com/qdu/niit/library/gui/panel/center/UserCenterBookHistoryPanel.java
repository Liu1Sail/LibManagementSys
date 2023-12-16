package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.service.BorrowingService;
import com.qdu.niit.library.service.ReturningService;
import com.qdu.niit.library.service.impl.BorrowingServiceImpl;
import com.qdu.niit.library.service.impl.ReturningServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书借阅面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterBookHistoryPanel extends centerPanelModel {
    private final User user;
    private final BorrowingService borrowService = new BorrowingServiceImpl();
    private final ReturningService returnService = new ReturningServiceImpl();


    public UserCenterBookHistoryPanel(User user) {
        this.user = user;
        var resultBottomPanel = new JPanel();
        resultBottomPanel.setBounds(40, 40, 720, 560);
        resultBottomPanel.setBackground(Color.WHITE);
        resultBottomPanel.setLayout(null);
        this.add(resultBottomPanel);
        var titleLabel = new JLabel("个人借阅信息");
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        resultBottomPanel.add(titleLabel);
        var borrowHistoryButton=new JButton("显示借书记录");
        borrowHistoryButton.setBounds(10,50,150,40);
        resultBottomPanel.add(borrowHistoryButton);
        var returnHistoryButton=new JButton("显示还书记录");
        returnHistoryButton.setBounds(180,50,150,40);
        resultBottomPanel.add(returnHistoryButton);
        var scrollPane = new JScrollPane();
        scrollPane.setBounds(0,100,720,400);
        DefaultTableModel tableModel = null;
        try {
            tableModel = getBorrowHistory();
        } catch (SQLException e) {
            //弹出错误提示，并可以选择退出程序或者重试
        }
        var resultDisplayTable = new JTable();
        resultDisplayTable.setRowHeight(30);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setViewportView(resultDisplayTable);
        resultBottomPanel.add(scrollPane);
        if(tableModel!=null){
            resultDisplayTable.setModel(tableModel);
        }
        else{
            //显示记录为空
        }
        borrowHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = null;
                try {
                    tableModel = getBorrowHistory();
                } catch (SQLException ex) {
                    //弹出错误提示，并可以选择退出程序或者重试
                }
                if (tableModel != null) {
                    resultDisplayTable.setModel(tableModel);
                }
                else{
                    //显示记录为空
                }
            }
        });
        returnHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = null;
                try {
                    tableModel = getReturnHistory();
                } catch (SQLException ex) {
                    //弹出错误提示，并可以选择退出程序或者重试
                }
                if (tableModel != null) {
                    resultDisplayTable.setModel(tableModel);
                }
                else{
                    //显示记录为空
                }
            }
        });
    }

    /**
     * @return 返回包含借书记录的defaultTable, 返回值为null时表示未查找到记录
     * @description 查找借书记录
     */
    public DefaultTableModel getBorrowHistory() throws SQLException {
        Borrowing[] borrowHistory;
        borrowHistory = borrowService.findAllByUid(user.getUID());
        if (borrowHistory != null) {
            String[][] rowData = new String[borrowHistory.length][3];
            String[] columnName = new String[]{"图书编号", "借阅开始时间", "借阅结束时间"};//暂时不显示书名
            for (int i = 0; i <= borrowHistory.length - 1; i++) {
                rowData[i][0] = String.valueOf(borrowHistory[i].getBid());
                rowData[i][1] = borrowHistory[i].getStart_time().toString().substring(0, 10);
                rowData[i][2] = borrowHistory[i].getEnd_time().toString().substring(0, 10);
            }
            return new DefaultTableModel(rowData, columnName);
        } else return null;
    }

    /**
     * @return 返回包含还书记录的defaultTable, 返回值为null时表示未查找到记录
     * @description 查找还书记录
     */
    public DefaultTableModel getReturnHistory() throws SQLException {
        Returning[] returnHistory;
        returnHistory = returnService.findAllByUid(user.getUID());
        if (returnHistory != null) {
            String[][] rowData = new String[returnHistory.length][2];
            String[] columnName = new String[]{"图书编号", "还书时间"};//暂时不显示书名
            for (int i = 0; i <= returnHistory.length - 1; i++) {
                rowData[i][0] = String.valueOf(returnHistory[i].getbid());
                rowData[i][1] = returnHistory[i].getBack_time().toString().substring(0, 10);
            }
            return new DefaultTableModel(rowData, columnName);
        } else return null;
    }
    private static class nonResultTableModel extends DefaultTableModel{
        public nonResultTableModel(){
//            String[][] rowData =new String[][]{{"未查找到数据"}};
        }
    }
}