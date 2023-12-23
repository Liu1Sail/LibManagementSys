package com.qdu.niit.library.application;

import com.qdu.niit.library.gui.LoginInterface;
import com.qdu.niit.library.utils.SqlConfig;

import java.awt.*;
import java.io.IOException;

public class LibraryManagementApplication {
    public void init() throws IOException {
        SqlConfig.getInstance().load("src/resources/config.property");
    }
    public void run() {
        EventQueue.invokeLater(LoginInterface::new);
    }

    public static void main(String []args) throws IOException {
        LibraryManagementApplication app = new LibraryManagementApplication();
        app.init();
        app.run();
    }
}
