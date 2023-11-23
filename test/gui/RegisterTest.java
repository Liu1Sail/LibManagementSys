package gui;

import gui.LoginInterface;
import gui.RegisterInterface;

import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description
 * @date 2023/11/14
 */

public class RegisterTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            new RegisterInterface();
        });

    }
}
