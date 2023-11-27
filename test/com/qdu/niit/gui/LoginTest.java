package com.qdu.niit.gui;

import com.qdu.niit.library.gui.LoginInterface;

import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 测试登录页面
 * @date 2023/11/12
 */

public class LoginTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            new LoginInterface();
        });
    }
}
