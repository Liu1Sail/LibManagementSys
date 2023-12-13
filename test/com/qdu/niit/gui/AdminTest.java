package com.qdu.niit.gui;

import com.qdu.niit.library.gui.AdministratorInterface;
import com.qdu.niit.library.utils.SqlConfig;

import java.awt.*;

/**
 * @author 作者姓名
 * @program LibManagementSys
 * @description 仅测试用
 * @date 2023/11/16
 */

public class AdminTest {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library","root","12345678");
        EventQueue.invokeLater(()->{
            new AdministratorInterface();
        });
    }
}
