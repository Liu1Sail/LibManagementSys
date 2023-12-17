package com.qdu.niit.library.gui.panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description SQL执行结果显示区域
 * @date 2023/11/23
 */

public class ResultDisplayArea extends JPanel {
    private final static int DEFAULT_X = 40;
    private final static int DEFAULT_Y = 280;
    private final static int DEFAULT_WIDTH = 720;
    private final static int DEFAULT_HEIGHT = 300;
    private final JPanel buttonPanel;
    private final JScrollPane scrollPane;

    private final DefaultTableModel tableModel;
    private final JTable textTable;

    public ResultDisplayArea(Object[][] rowData, Object[] columnName) {
        this.setBounds(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        buttonPanel = new JPanel();
        tableModel = new DefaultTableModel(rowData, columnName);
        textTable = new JTable(tableModel);
        scrollPane = new JScrollPane();
        buttonPanel.setBounds(0, 0, 720, 50);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(buttonPanel);
        textTable.setBounds(0, 50, 720, 250);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setBounds(0, 50, 720, 250);
        scrollPane.setViewportView(textTable);
        this.add(scrollPane);
    }

    public DefaultTableModel getTextTableModel() {
        return tableModel;
    }

//    @Override
//    public void setBounds(int x, int y, int width, int height) {
//        super.setBounds(x, y, width, height);
//        scrollPane.setBounds(0, 50, width, height - 50);
//    }
}