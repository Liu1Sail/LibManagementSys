package com.qdu.niit.gui;

import javax.swing.*;
import java.awt.*;

public class TmpTest extends JFrame {
    private JTable table;

    private TmpTest() {
        super();
        setTitle( "操作表格示例" );
        setBounds( 100, 100, 500, 375 );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add( scrollPane, BorderLayout.CENTER );
        String[] columnNames = {"A", "B", "C", "D", "E", "F", "G"};     // 定义表格列
        String[][] tableValues = new String[20][columnNames.length];    // 定义数组，用于存储表格单元数据
        // 初始化表格数据
//        for (int row = 0; row < 20; row++) {
//            for (int column = 0; column < len; column++) {
//                tableValues[row][column] = columnNames[column] + row;
//            }
//        }
        table = new JTable( tableValues, columnNames );
        scrollPane.setViewportView( table );
        // 输出表格的行数、列数
    }

    public static void main(String[] args) {
        TmpTest frame = new TmpTest();
        frame.setVisible( true );
    }
}