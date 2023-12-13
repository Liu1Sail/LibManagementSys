package com.qdu.niit.gui;

import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.gui.UserInterface;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 仅用于测试用户界面
 * @date 2023/11/29
 */

public class UserTest {
    public static void main(String[] args) {
        new UserInterface(new User(1,"123213","11111111@L"));
    }
}
