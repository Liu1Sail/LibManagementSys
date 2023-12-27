package com.qdu.niit.library.gui.panel.center;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.gui.table.NonResultTableModel;
import com.qdu.niit.library.service.BorrowingService;
import com.qdu.niit.library.service.ReturningService;
import com.qdu.niit.library.service.impl.BookServiceImpl;
import com.qdu.niit.library.service.impl.BorrowingServiceImpl;
import com.qdu.niit.library.service.impl.ReturningServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书借阅面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterBookReturnPanel extends centerPanelModel {
    private final User user;
    private final BorrowingService borrowService = new BorrowingServiceImpl();
    private final ReturningService returnService = new ReturningServiceImpl();
    private final NonResultTableModel nonResultTableModel = new NonResultTableModel();
    private final JDialog popMessageDialog;
    private final BorrowingServiceImpl borrowingServiceImpl = new BorrowingServiceImpl();
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl();

    public UserCenterBookReturnPanel(Frame frame, User user) {
        popMessageDialog = new JDialog(frame, true);
        popMessageDialog.setLocationRelativeTo(null);
        popMessageDialog.setSize(300, 200);
        popMessageDialog.setTitle("错误提示");
        popMessageDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popMessageDialog.setLayout(new BorderLayout());
        var popMessageLabel = new JLabel("读取借书记录失败，请检查数据库是否连接");
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
        var scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 100, 720, 460);
        DefaultTableModel tableModel = null;
        var resultDisplayTable = new JTable();
        resultDisplayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultDisplayTable.setRowHeight(30);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setViewportView(resultDisplayTable);
        resultBottomPanel.add(scrollPane);
        resultDisplayTable.setModel(getBorrowHistory());

        var returnBookButton = new JButton("还书");
        returnBookButton.setBounds(10, 50, 150, 40);
        resultBottomPanel.add(returnBookButton);
        returnBookButton.addActionListener(e -> {
            int selectedRow=resultDisplayTable.getSelectedRow();
            System.out.println(selectedRow);
            int selectedCopyId= Integer.parseInt((String) resultDisplayTable.getValueAt(selectedRow,0));
            try {
                //删除借书记录，修改在架状态，添加还书记录
                borrowingServiceImpl.deleteByBid(selectedCopyId);
                Calendar nowCal=Calendar.getInstance();
                LocalDateTime localDateTime=LocalDateTime.now();
                bookServiceImpl.backBook(selectedCopyId);
            } catch (SQLException ex) {
                popMessageDialog.setVisible(true);
            }
        });
        var refreshPage = new JButton("刷新列表");
        refreshPage.setBounds(180, 50, 150, 40);
        resultBottomPanel.add(refreshPage);
        refreshPage.addActionListener(e -> resultDisplayTable.setModel(getBorrowHistory()));
    }

    public DefaultTableModel getBorrowHistory() {
        Borrowing[] borrowHistory;
        try {
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
            } else return nonResultTableModel;
        }
        catch (SQLException e){
            popMessageDialog.setVisible(true);
        }
        return nonResultTableModel;
    }
}