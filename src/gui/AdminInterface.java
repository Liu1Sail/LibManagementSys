package gui;

import gui.component.ShapeDeepenPanel;
import gui.frame.ResizeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 管理员管理界面
 * @date 2023/11/16
 */

public class AdminInterface extends ResizeFrame {
    private final ResizeFrame frame = this;
    private final int initialWidth=1000;
    private final int initialHeight=700;

    //完成阴影边框，并应用到注册和登录窗口
    public AdminInterface() {
        frame.setLayout(new BorderLayout());
        frame.setSize(initialWidth, initialHeight);
        frame.setLocationRelativeTo(null);
        frame.setTitle("管理系统");
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        //鼠标拖动窗口
        var titleFont=new Font("宋体",Font.PLAIN,25);
        var bottomPanel=new JPanel();
        bottomPanel.setBounds(0,0,1000,700);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);

        var leftPanel=new JPanel();//左侧面板
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLocation(0,0);
        leftPanel.setPreferredSize(new Dimension(200,0));
        leftPanel.setLayout(null);
        var sideBarOptionFont=new Font("宋体",Font.PLAIN,20);
        var bookOption=new JPanel();
        bookOption.setBounds(0,0,400,80);
        bookOption.setLayout(null);

        bookOption.setLayout(null);
        var bookOptionText=new JLabel();
        bookOptionText.setBounds(0,0,200,80);
        bookOptionText.setText("图书管理");
        bookOptionText.setFont(sideBarOptionFont);
        bookOptionText.setVerticalAlignment(SwingConstants.CENTER);
        bookOptionText.setHorizontalAlignment(SwingConstants.CENTER);
        bookOption.add(bookOptionText);
        var bookAddChildOption=new JLabel("增加图书");
        bookAddChildOption.setBounds(0,80,200,80);
        bookAddChildOption.setBackground(Color.WHITE.PINK);
        bookAddChildOption.setVerticalAlignment(SwingConstants.CENTER);
        bookAddChildOption.setHorizontalAlignment(SwingConstants.CENTER);
        bookAddChildOption.setOpaque(true);
        bookOption.add(bookAddChildOption);
        //
        bookOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                bookOption.setBounds(0,0,200,360);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                bookOption.setBounds(0,0,200,80);
            }
        });
        leftPanel.add(bookOption);
        bottomPanel.add(leftPanel,BorderLayout.WEST);

        var topBottomPanel=new JPanel();//顶部根面板
        topBottomPanel.setBackground(Color.WHITE);
        topBottomPanel.setPreferredSize(new Dimension(800,80));
        topBottomPanel.setLayout(null);

        var leftTopPanel = getLeftTopPanel(leftPanel, topBottomPanel, titleFont);//左上角标题面板
        topBottomPanel.add(leftTopPanel);

        var topPanel=new JPanel();
        topPanel.setBounds(leftPanel.getPreferredSize().width,0,topBottomPanel.getPreferredSize().width,topBottomPanel.getPreferredSize().height);
        topPanel.setLayout(null);
        topPanel.setBackground(Color.WHITE);
        var buttonClose=getButtonClose(frame);
        buttonClose.setBounds(topPanel.getWidth()-30,10,20,20);
        topPanel.add(buttonClose);
        topBottomPanel.add(topPanel);
        bottomPanel.add(topBottomPanel,BorderLayout.NORTH);

        var centerPanel=new JPanel();//中心面板
        centerPanel.setBackground(new Color(236,238,245));
        bottomPanel.add(centerPanel,BorderLayout.CENTER);
        //测试代码区
        bookOption.setBackground(Color.ORANGE);
        //
        frame.add(bottomPanel,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JPanel getLeftTopPanel(JPanel leftPanel, JPanel topBottomPanel, Font titleFont) {
        var leftTopPanel=new JPanel();//左上角图标面板
        leftTopPanel.setBounds(0,0, leftPanel.getPreferredSize().width, topBottomPanel.getPreferredSize().height);
        leftTopPanel.setBackground(new Color(164, 232, 255));
        leftTopPanel.setLayout(null);
        var leftTopTitle=new JLabel();
        leftTopTitle.setBounds(0,0,leftTopPanel.getWidth(),leftTopPanel.getHeight());
        leftTopTitle.setFont(titleFont);
        leftTopTitle.setText("管理面板");
        leftTopTitle.setVerticalAlignment(SwingConstants.CENTER);
        leftTopTitle.setHorizontalAlignment(SwingConstants.CENTER);
        leftTopPanel.add(leftTopTitle);
        return leftTopPanel;
    }

    private static ShapeDeepenPanel getButtonClose(JFrame frame) {
        var buttonClose = new ShapeDeepenPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0f, 0f, 0f, this.getOpacity()));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, 0, 15, 15);
                g2d.drawLine(15, 0, 0, 15);
            }
        };
        buttonClose.setOpaque(false);
        buttonClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonClose.getTimerEntered().restart();
                if (buttonClose.getTimerExited().isRunning()) {
                    buttonClose.getTimerExited().stop();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonClose.getTimerExited().restart();
                if (buttonClose.getTimerEntered().isRunning()) {
                    buttonClose.getTimerEntered().stop();
                }
            }
        });
        return buttonClose;
    }
}
