package gui;

import gui.tool.CustomRoundRectBorder;
import gui.tool.ImagePanel;
import gui.tool.ShapeDeepenJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 登陆界面
 * @date 2023/11/12
 */

public class LoginInterface extends JFrame {
    private JFrame frame = this;
    private Dimension initialDimension;
    private int initialX;
    private int initialY;
    private Point offsetMouseToFrame = new Point();

    public LoginInterface() {
        initialDimension = Toolkit.getDefaultToolkit().getScreenSize();
        initialX = initialDimension.width / 4;
        initialY = initialDimension.height / 6;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(initialX, initialY, 800, 600);
        frame.setUndecorated(true);
        //鼠标拖动窗口
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offsetMouseToFrame.x = e.getXOnScreen() - frame.getLocationOnScreen().x;
                offsetMouseToFrame.y = e.getYOnScreen() - frame.getLocationOnScreen().y;
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            private Point mousePos;

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePos = e.getLocationOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point offset = new Point(e.getXOnScreen() - mousePos.x, e.getYOnScreen() - mousePos.y);
                int newX = e.getXOnScreen() - offsetMouseToFrame.x;
                int newY = e.getYOnScreen() - offsetMouseToFrame.y;
                frame.setLocation(newX, newY);
                mousePos = e.getLocationOnScreen();
            }
        });
        //左侧主面板
        var leftPanel = getLeftPanel();
        leftPanel.setBounds(0, 0, 500, 600);
        leftPanel.setOpaque(false);
        frame.add(leftPanel);
        //右侧主面板
        var rightPanel = new JPanel();
        rightPanel.setBounds(500, 0, 300, 600);
        rightPanel.setLayout(null);
        var rightTitleToolBar = getRightTitleToolBar(frame);
        rightPanel.add(rightTitleToolBar);
        //密码登录输入框Panel
        var passLoginInputPanel = new JPanel();
        passLoginInputPanel.setBounds(25, 200, 250, 120);
        passLoginInputPanel.setLayout(null);
        passLoginInputPanel.setOpaque(false);
        //账号输入TextField
        var textBorderColor = new Color(234, 237, 237);
        var textColor = new Color(127, 127, 127);
        var textFont = new Font("宋体", Font.PLAIN, 15);
        var userInputTextBorder = new JPanel();
        userInputTextBorder.setBounds(0, 0, 250, 60);
        userInputTextBorder.setLayout(null);
        userInputTextBorder.setOpaque(false);
        userInputTextBorder.setBorder(new CustomRoundRectBorder(textBorderColor, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(this.getColor());
                g.fillRect(x, y + height - this.getThickness() - 5, width, this.getThickness()); // 底部边框
            }
        });
        var userInputTextField = new JTextField();
        userInputTextField.setBounds(0, 0, 220, 50);
        userInputTextField.setBorder(null);
        userInputTextField.setFont(textFont);
        userInputTextField.setForeground(textColor);
        userInputTextField.setText("账号");
        userInputTextField.setOpaque(false);
        userInputTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userInputTextField.getText().equals("账号")) {
                    userInputTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userInputTextField.getText().equals("")) {
                    userInputTextField.setText("账号");
                }
            }
        });
        //添加下拉框
        var userListJpanel = new JPanel();
        userInputTextBorder.add(userInputTextField);
        //密码输入TextField
        var passInputPasswordBorder = new JPanel();
        passInputPasswordBorder.setBounds(0, 60, 250, 60);
        passInputPasswordBorder.setLayout(null);
        passInputPasswordBorder.setOpaque(false);
        passInputPasswordBorder.setBorder(new CustomRoundRectBorder(textBorderColor, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(this.getColor());
                g.fillRect(x, y + height - this.getThickness() - 5, width, this.getThickness()); // 顶部边框
            }
        });
        var passInputPasswordField = new JPasswordField() {
            private char defaultChar = this.getEchoChar();
        };
        passInputPasswordField.setBounds(0, 0, 180, 50);
        passInputPasswordField.setBorder(null);
        passInputPasswordField.setFont(textFont);
        passInputPasswordField.setForeground(textColor);
        passInputPasswordField.setOpaque(false);
        passInputPasswordField.setEchoChar('\0');
        passInputPasswordField.setText("密码");
        passInputPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passInputPasswordField.getText().equals("密码")) {
                    passInputPasswordField.setEchoChar(passInputPasswordField.defaultChar);
                    passInputPasswordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passInputPasswordField.getText().equals("")) {
                    passInputPasswordField.setEchoChar('\0');
                    passInputPasswordField.setText("密码");
                }
            }
        });
        passInputPasswordBorder.add(passInputPasswordField);
        //密码输入框中的登录按钮
        var loginButton = new JPanel() {
            private final Color color = new Color(230, 230, 230);
            private final Color firstColor = new Color(245, 247, 249);
            private final Color arrorFirstColor = new Color(129, 134, 143);
            private final Color arrorSecondColor = new Color(70, 77, 90);
            private boolean hasBeenReleased = false;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(color);
                g2d.fillOval(0, 0, this.getWidth(), this.getHeight());
                if (hasBeenReleased) {
                    g2d.setColor(firstColor);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillOval(2, 2, this.getWidth() - 4, this.getHeight() - 4);
                g2d.setColor(Color.BLACK);
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                var arrowStroke = new BasicStroke(2f);
                g2d.setStroke(arrowStroke);
                if (hasBeenReleased) {
                    g2d.setColor(arrorSecondColor);
                } else {
                    g2d.setColor(arrorFirstColor);
                }
                g2d.drawLine(13, 20, 28, 20);
                g2d.drawLine(22, 13, 28, 20);
                g2d.drawLine(22, 27, 28, 20);
            }

            public void changeColor(boolean hasBeenReleased) {
                this.hasBeenReleased = hasBeenReleased;
            }
        };
        loginButton.setBorder(null);
        loginButton.setOpaque(false);
        loginButton.setBounds(200, 5, 40, 40);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.changeColor(true);
                loginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.changeColor(false);
                loginButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //登陆操作
            }
        });
        passInputPasswordBorder.add(loginButton);
        frame.add(rightPanel);
        //测试代码区
        leftPanel.setBackground(new Color(212, 239, 223));
        rightPanel.setBackground(new Color(218, 247, 166));

        frame.setVisible(true);
    }

    private JPanel getLeftPanel() {
        var leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 600));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null);
        var leftImageIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/login/LeftImage.jpg"));
        var leftImagePanel = new ImagePanel(leftImageIcon, 0, 0, 600, 500);
        leftPanel.add(leftImagePanel);
        return leftPanel;
    }

    private static JPanel getRightTitleToolBar(JFrame frame) {
        var rightTitleToolBar = new JPanel();
        rightTitleToolBar.setBounds(10, 10, 280, 35);
        rightTitleToolBar.setBackground(Color.WHITE);
        rightTitleToolBar.setLayout(null);
        rightTitleToolBar.setOpaque(false);
        //创建右上角关闭按钮
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
        //x位置为rightToolBar宽度-(15-10),-10是减去Bar右侧与边界的间隔，以保证图标距离上侧和右侧距离相同
        buttonClose.setBounds(255, 10, 16, 16);
        buttonClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.dispose();
                System.exit(0);
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
        //创建右上角设置(六边形齿轮)按钮
        var settingButton = new ShapeDeepenJPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0f, 0f, 0f, this.getOpacity()));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setStroke(new BasicStroke(1));
                int[] xPoints = {10, 18, 18, 10, 2, 2};//从上面中间的点开始，顺时针
                int[] yPoints = {2, 6, 15, 19, 15, 6};//横向20px，纵向22px，纵向一条边10px，斜着的边在纵向投影6px，在横向投影5px
                //drawPolygon若最后一个点不同，自动闭合，drawPolyline不自动闭合
                g.drawPolygon(xPoints, yPoints, xPoints.length);
                g.drawOval(5, 6, 10, 9);
            }
        };
        settingButton.setOpaque(false);
        settingButton.setBounds(225, 7, 20, 20);//坐标从0开始算，那宽度21时，坐标为0-20
        //未添加设置面板跳转
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingButton.getTimerEntered().restart();
                if (settingButton.getTimerExited().isRunning()) {
                    settingButton.getTimerExited().stop();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.getTimerExited().restart();
                if (settingButton.getTimerEntered().isRunning()) {
                    settingButton.getTimerEntered().stop();
                }
            }
        });
        rightTitleToolBar.add(settingButton);
        rightTitleToolBar.add(buttonClose);
        return rightTitleToolBar;
    }
}
