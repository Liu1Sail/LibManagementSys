package gui.panel;

import javax.swing.*;
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
    private final JTable textTable;

    public ResultDisplayArea() {
        this.setBounds(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        buttonPanel = new JPanel();
        textTable = new JTable();
        buttonPanel.setBounds(0, 0, 720, 50);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        this.add(buttonPanel);
        textTable.setBounds(0, 50, 720, 250);
        textTable.setBackground(Color.WHITE);
        textTable.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        textTable.setFont(new Font("宋体",Font.PLAIN,15));
        this.add(textTable);
    }

    public JTable getTextTable() {
        return textTable;
    }
}
