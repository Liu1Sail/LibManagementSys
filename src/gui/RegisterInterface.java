package gui;

import gui.border.OutwardShadowBorder;
import gui.component.ShapeDeepenJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description
 * @date 2023/11/14
 */

public class RegisterInterface extends JFrame {
    private final JFrame frame = this;
    private Point offsetMouseToFrame = new Point();
    //完成阴影边框，并应用到注册和登录窗口
    public RegisterInterface() {
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 239, 223));
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setTitle("注册");
        //测试
        //frame.getRootPane().setBorder(new OutwardShadowBorder(5,5,5,5));
//        frame.getRootPane().setBorder(new OutwardShadowBorder(10));
        //鼠标拖动窗口
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offsetMouseToFrame.x = e.getXOnScreen() - frame.getLocationOnScreen().x;
                offsetMouseToFrame.y = e.getYOnScreen() - frame.getLocationOnScreen().y;
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - offsetMouseToFrame.x;
                int newY = e.getYOnScreen() - offsetMouseToFrame.y;
                frame.setLocation(newX, newY);
            }
        });
        var buttonClose=getButtonClose(frame);
        //添加注册信息输入JPanel
        var bodyPanel = getBodPanel();
        frame.add(bodyPanel);
        frame.add(buttonClose);
        frame.setVisible(true);
    }

    private JPanel getBodPanel() {
        var bodyPanel=new JPanel();
        bodyPanel.setBounds(0,40,400,400);
        bodyPanel.setOpaque(false);
        bodyPanel.setLayout(null);
        var titleText=new JLabel();
        titleText.setBounds(100,10,200,50);
        titleText.setFont(new Font("宋体",Font.PLAIN,40));
        titleText.setText("注册");
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setVerticalAlignment(SwingConstants.TOP);
        bodyPanel.add(titleText);
        var nameLabel=new JLabel();
        var nameInput=new JTextField();
        var passwordLabel=new JLabel();
        var passwordInput=new JTextField();
        var genderLabel=new JLabel();
        var genderInput=new JTextField();
        var phoneLabel=new JLabel();
        var phoneInput=new JTextField();
        var emailLabel=new JLabel();
        var emailInput=new JTextField();
        var font=new Font("宋体",Font.PLAIN,18);
        nameLabel.setBounds(30,60,80,50);
        nameLabel.setFont(font);
        nameLabel.setText("用户名：");
        nameLabel.setOpaque(false);
        nameInput.setBounds(120,70,200,30);
        passwordLabel.setBounds(30,110,80,50);
        passwordLabel.setFont(font);
        passwordLabel.setText("密码：");
        passwordLabel.setOpaque(false);
        passwordInput.setBounds(120,120,200,30);
        genderLabel.setBounds(30,160,80,50);
        genderLabel.setFont(font);
        genderLabel.setText("性别：");
        genderLabel.setOpaque(false);
        genderInput.setBounds(120,170,200,30);
        phoneLabel.setBounds(30,210,80,50);
        phoneLabel.setFont(font);
        phoneLabel.setText("手机号：");
        phoneLabel.setOpaque(false);
        phoneInput.setBounds(120,220,200,30);
        emailLabel.setBounds(30,260,80,50);
        emailLabel.setFont(font);
        emailLabel.setText("邮箱：");
        emailLabel.setOpaque(false);
        emailInput.setBounds(120,270,200,30);
        bodyPanel.add(nameLabel);
        bodyPanel.add(nameInput);
        bodyPanel.add(passwordLabel);
        bodyPanel.add(passwordInput);
        bodyPanel.add(genderLabel);
        bodyPanel.add(genderInput);
        bodyPanel.add(phoneLabel);
        bodyPanel.add(phoneInput);
        bodyPanel.add(emailLabel);
        bodyPanel.add(emailInput);
        var defineButton=new JButton();
        var cancelButton=new JButton();
        defineButton.setBounds(110,320,80,30);
        defineButton.setText("取消");
        cancelButton.setBounds(210,320,80,30);
        cancelButton.setText("确认");
        defineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
                //获取输入信息，向数据库提交信息
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
            }
        });
        bodyPanel.add(defineButton);
        bodyPanel.add(cancelButton);
        return bodyPanel;
    }
    private static ShapeDeepenJPanel getButtonClose(JFrame frame) {
        var buttonClose = new ShapeDeepenJPanel() {
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
        buttonClose.setBounds(370, 14, 16, 16);
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