package com.qdu.niit.gui;

import com.qdu.niit.library.gui.animation.Translation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class TmpTest extends JFrame {
    private TmpTest() {
        JFrame frame=new JFrame();
        frame.setBounds(200,100,800,500);
        frame.setLayout(null);
        var panel1=new JPanel();
        panel1.setBackground(Color.RED);
        var panel2=new JPanel();
        panel2.setBackground(Color.ORANGE);
        var panel3=new JPanel();
        panel3.setBackground(Color.BLUE);
        var list=new LinkedList<JComponent>();
        list.add(panel1);
        list.add(panel2);
        list.add(panel3);
        var a=new Translation(list,1,10,Translation.DIRECTION_UPPER);
        a.setBounds(100,100,200,200);
        frame.add(a);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.start();
    }

    public static void main(String[] args) {
        TmpTest frame = new TmpTest();
    }
}