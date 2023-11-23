package com.qdu.niit.library.gui.panel;

import javax.swing.*;
import java.awt.*;

/**·
 * @author 李冠良
 * @program LibManagementSys
 * @description 添加图书面板
 * @date 2023/11/21
 */

public class centerPanelModel extends JPanel {
    private JPanel panel=this;
    public centerPanelModel() {
        panel.setLayout(null);
        panel.setBounds(0,0,800,640);
        panel.setBackground(new Color(236,238,245));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
    }
}
