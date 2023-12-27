package com.qdu.niit.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TmpTest2 extends JFrame {
    public TmpTest2() {
        this.setBounds(200,50,1000,700);
        this.setLayout(null);
        var bottomPanel=new JPanel();
        bottomPanel.setBounds(300,100,200,100);
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(null);
        this.add(bottomPanel);
        var middlePanel=new JPanel();
        middlePanel.setBounds(0,100,200,300);
        middlePanel.setBackground(Color.ORANGE);
        middlePanel.setLayout(null);
        bottomPanel.add(middlePanel);
        var option1=new JPanel();
        var option2=new JPanel();
        var option3=new JPanel();
        option1.setBounds(0,0,200,100);
        option2.setBounds(0,100,200,100);
        option3.setBounds(0,200,200,100);
        option1.setBackground(Color.PINK);
        option2.setBackground(Color.BLUE);
        option3.setBackground(Color.YELLOW);
        middlePanel.add(option1);
        middlePanel.add(option2);
        middlePanel.add(option3);
        bottomPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bottomPanel.setSize(200,400);
                bottomPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bottomPanel.setSize(200,100);

            }
        });

        var btn1=new JButton("展开");
        btn1.setBounds(600,100,100,50);
        var btn2=new JButton("收回");
        btn2.setBounds(600,200,100,50);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.setSize(200,400);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.setSize(200,100);
            }
        });
        this.add(btn1);
        this.add(btn2);
//        this.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                System.out.println(e.getX()+" "+e.getY());
//            }
//        });
        this.setVisible(true);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(TmpTest2::new);
    }
}