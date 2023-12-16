package com.qdu.niit.library.gui.table;

import javax.swing.table.DefaultTableModel;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 用于在搜索结果为空时显示在JTable中
 * @date 2023/12/16
 */

public class NonResultTableModel extends DefaultTableModel {
    private static final String[][] rowData =new String[][]{{"未查找到数据"}};
    private static final String[] columnName=new String[]{"无"};
    public NonResultTableModel(){
        super(rowData,columnName);
    }
}