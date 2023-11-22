package gui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 图书面板，仅用于管理员界面中心面板
 * @date 2023/11/22
 */

public class centerAddBookPanel extends centerPanelModel {
    private centerAddBookPanel frame = this;

    public centerAddBookPanel() {
        var inputBottomPanel = new JPanel();
        inputBottomPanel.setBounds(40, 40, 720, 210);
        inputBottomPanel.setBackground(Color.WHITE);
        this.add(inputBottomPanel);
        var titleLabel = new JLabel("新增图书信息");
        var name = new InputPanel("书名：");
        var isbn = new InputPanel("ISBN：");
        var author = new InputPanel("作者：");
        var publisher = new InputPanel("出版社：");
        var pubTime = new InputPanel("出版时间：");
        var genre = new InputPanel("类型：");
        var resetButton=new JButton();
        var defineButton=new JButton();
        titleLabel.setBounds(10, 5, 120, 30);
        titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        name.setLocation(15, 35);
        isbn.setLocation(220, 35);
        author.setLocation(440, 35);
        publisher.setLocation(15, 90);
        pubTime.setLocation(235, 90);
        genre.setLocation(465, 90);

        inputBottomPanel.add(titleLabel);
        inputBottomPanel.add(name);
        inputBottomPanel.add(isbn);
        inputBottomPanel.add(author);
        inputBottomPanel.add(publisher);
        inputBottomPanel.add(pubTime);
        inputBottomPanel.add(genre);
    }
}
