package com.qdu.niit.gui;

import com.qdu.niit.library.gui.removed.RegisterInterfaceSave;
import com.qdu.niit.library.gui.animation.Translation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TmpTest extends JFrame {
    public TmpTest() {
        JFrame frame = new JFrame();
        frame.setBounds(200, 100, 800, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        var panel = new JPanel();
        panel.setBounds(0, 0, 800, 500);
        panel.setLayout(null);
        frame.add(panel);
        var btn1 = new JButton("改变");
        btn1.setBounds(100, 100, 100, 100);
        var btn2 = new JButton("改变");
        btn2.setBounds(100, 200, 100, 100);
        var target = new JPanel();
        target.setBackground(Color.ORANGE);
        var test = new Translation(target, 1, 4, 100, Translation.TO_LEFT);
        test.setBounds(250, 250, 100, 30);
        btn1.addActionListener(new ActionListener() {
            private int flag = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                test.waitOrStart(Translation.TO_RIGHT, 4);
            }
        });
        btn2.addActionListener(new ActionListener() {
            private int flag = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                test.waitOrStart(Translation.TO_LEFT, 4);
            }
        });
        test.start();
        panel.add(btn1);
        panel.add(btn2);
        panel.add(test);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterInterfaceSave();
    }
}

//public class TmpTest extends JFrame {
//    public TmpTest(){
//        JFrame frame=new JFrame();
//        frame.setBounds(200,100,800,500);
//        frame.setLayout(null);
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        var panel=new JPanel();
//        panel.setBounds(0,0,800,500);
//        panel.setLayout(null);
//        frame.add(panel);
//        var text=new TextEmergeLabel("测试",0.2,0,255,Color.BLACK);
//        text.setBounds(200,200,200,200);
//        text.setFont(new Font("宋体",Font.PLAIN,50));
//        panel.add(text);
//        text.start();
//        frame.setVisible(true);
//    }
//    public static void main(String[] args) {
//        TmpTest frame = new TmpTest();
//    }
//}


//
//public class TmpTest extends JFrame {
//    private TmpTest() {
////        JFrame frame=new JFrame();
////        frame.setBounds(200,100,800,500);
////        frame.setLayout(null);
////        var panel1=new JPanel();
////        panel1.setBackground(Color.RED);
////        var panel2=new JPanel();
////        panel2.setBackground(Color.ORANGE);
////        var panel3=new JPanel();
////        panel3.setBackground(Color.BLUE);
////        var list=new LinkedList<JComponent>();
////        list.add(panel1);
////        list.add(panel2);
////        list.add(panel3);
////        var a=new RollDisplay(list,1,2, RollDisplay.DIRECTION_LEFT);
////        a.setBounds(100,100,200,200);
////        frame.add(a);
////        frame.setVisible(true);
////        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//////        try {
//////            Thread.sleep(1000);
//////        } catch (InterruptedException e) {
//////            throw new RuntimeException(e);
//////        }
////        a.start();
//
//
//        JFrame frame=new JFrame();
//        frame.setBounds(200,100,800,500);
//        frame.setLayout(null);
//        var panel1=new JPanel();
//        panel1.setBackground(Color.ORANGE);
//        var a=new Translation(panel1,1,2,100, Translation.TO_BELOW);
//        a.setBounds(100,100,200,200);
//        frame.add(a);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        a.start();
//    }
//    public static void main(String[] args) {
//        TmpTest frame = new TmpTest();
//    }
//}