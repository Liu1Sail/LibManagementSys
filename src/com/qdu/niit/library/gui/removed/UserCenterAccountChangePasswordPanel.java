package com.qdu.niit.library.gui.removed;

import com.qdu.niit.library.gui.input.InputPasswordPanel;
import com.qdu.niit.library.gui.panel.center.centerPanelModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 已弃用
 * @description 修改密码面板，仅用于用户界面中心面板
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class UserCenterAccountChangePasswordPanel extends centerPanelModel {
    private final JFrame frame;
    public UserCenterAccountChangePasswordPanel(JFrame frame) {
        this.frame=frame;
        var inputPanel=new JPanel();
        inputPanel.setBounds(40,40,720,550);
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setLayout(null);
        this.add(inputPanel);
        var originalPass=new InputPasswordPanel("原密码：");
        var newPass=new InputPasswordPanel("新密码：");
        var newPassRepeat=new InputPasswordPanel("重复密码：");
        newPass.setLocation(50,50);
        inputPanel.add(newPass);
        originalPass.setLocation(50,100);
        inputPanel.add(originalPass);
        newPassRepeat.setLocation(50,150);
        inputPanel.add(newPassRepeat);
        var defineButton=new JButton();
        defineButton.setText("确认");
        defineButton.setFont(new Font("宋体",Font.PLAIN,18));
        defineButton.setBounds(300,200,100,40);
        inputPanel.add(defineButton);
        defineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //比对原密码是否正确
                //若正确，发送新密码
                //弹出修改成功提示
                originalPass.setInputText("");
                newPass.setInputText("");
                newPassRepeat.setInputText("");
            }
        });
    }
}
