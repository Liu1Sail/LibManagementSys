package com.qdu.niit.gui;

import com.qdu.niit.library.gui.RegisterInterface;
import com.qdu.niit.library.utils.SqlConfig;

import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description
 * @date 2023/11/14
 */

public class RegisterTest {
    public static void main(String[] args) {
        SqlConfig.getInstance().init("jdbc:mysql://localhost:3306/library","root","12345678");
        EventQueue.invokeLater(()->{
            RegisterInterface.getInstance();
        });

    }
}
