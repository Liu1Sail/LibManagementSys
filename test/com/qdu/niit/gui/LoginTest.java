package com.qdu.niit.gui;

import com.qdu.niit.library.gui.LoginInterface;
import com.qdu.niit.library.utils.SqlConfig;

import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 测试登录页面
 * @date 2023/11/12
 */

public class LoginTest {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library","root","12345678");
        EventQueue.invokeLater(()->{
            new LoginInterface();
        });
    }
}
