package com.qdu.niit.gui;

import com.qdu.niit.library.gui.animation.RollDisplay;
import com.qdu.niit.library.gui.animation.TextEmergeLabel;
import com.qdu.niit.library.gui.animation.Translation;
import com.qdu.niit.library.gui.input.InputInnerTextPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class TmpTest extends JFrame {
    public TmpTest(){
        JFrame frame=new JFrame();
        frame.setBounds(200,100,800,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        var panel=new JPanel();
        panel.setBounds(0,0,800,500);
        panel.setLayout(null);
        frame.add(panel);
        var text=new InputInnerTextPanel("测试");
        text.setFont(new Font("宋体",Font.PLAIN,40));
        text.setBounds(200,200,200,200);
        panel.add(text);
        var text1=new InputInnerTextPanel("测试");
        text1.setFont(new Font("宋体",Font.PLAIN,40));
        text1.setBounds(400,400,200,200);
        panel.add(text1);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        TmpTest frame = new TmpTest();
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