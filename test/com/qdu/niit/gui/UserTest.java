package com.qdu.niit.gui;

import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.gui.UserInterface;
import com.qdu.niit.library.service.impl.UserServiceImpl;
import com.qdu.niit.library.utils.SqlConfig;

import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 仅用于测试用户界面
 * @date 2023/11/29
 */

public class UserTest {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library","root","12345678");
        EventQueue.invokeLater(()->{
            new UserInterface(UserServiceImpl.getInstance().login(20,"111111@L"));
        });
    }
}
