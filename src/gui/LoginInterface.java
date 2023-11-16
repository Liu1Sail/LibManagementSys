package gui;

import gui.border.CustomRoundRectBorder;
import gui.component.ImagePanel;
import gui.component.ShapeDeepenJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 登陆界面
 * @date 2023/11/12
 */

public class LoginInterface extends JFrame {
    private final JFrame frame = this;
    private RegisterInterface registerFrame;
    private final Dimension initialDimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final int initialX = initialDimension.width / 4;
    private final int initialY = initialDimension.height / 6;
    private Point offsetMouseToFrame = new Point();
    private boolean userAndAdminSwitch = false;
    public LoginInterface() {
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(initialX, initialY, 800, 600);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("图书管理系统");
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
        //左侧主面板
        var leftPanel = getLeftPanel();
        leftPanel.setBounds(0, 0, 500, 600);
        leftPanel.setOpaque(false);
        frame.add(leftPanel);
        //右侧主面板
        var rightPanel = new JPanel();
        rightPanel.setBounds(500, 0, 300, 600);
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.WHITE);
        var rightTitleToolBar = getRightTitleToolBar(frame);
        rightPanel.add(rightTitleToolBar);
        //添加右侧主面板
        var rightBodyPanel = new JPanel();
        rightBodyPanel.setBounds(0, 80, 300, 350);
        rightBodyPanel.setOpaque(false);
        rightBodyPanel.setLayout(null);
        rightBodyPanel.setBackground(Color.WHITE);
        rightPanel.add(rightBodyPanel);
        //用户登录按钮
        var userLoginPanel = new JPanel();
        userLoginPanel.setLayout(null);
        userLoginPanel.setBounds(60, 50, 80, 100);
        userLoginPanel.setOpaque(false);
        userLoginPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        var userLoginRound = new JPanel() {
            private final Color unPressedColor = new Color(245, 245, 245);
            private final Color pressedColor = new Color(17, 145, 255);
            private boolean colorChangeSwitch = false;

            public void setColorChangeSwitch(boolean colorChangeSwitch) {
                this.colorChangeSwitch = colorChangeSwitch;
                this.repaint();
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (colorChangeSwitch) {
                    g2d.setColor(pressedColor);
                } else {
                    g2d.setColor(unPressedColor);
                }
                g2d.fillOval(0, 0, 79, 79);
                try {
                    var imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("img/login/UserLoginIcon.png")));
                    g2d.drawImage(imageIcon.getImage(), 20, 20, 40, 40, null);
                }
                catch(NullPointerException e){
                    throw new RuntimeException(e);
                }
            }
        };
        userLoginRound.setBounds(0, 0, 80, 80);
        userLoginRound.setOpaque(false);
        userLoginPanel.add(userLoginRound);
        var userLoginText = new JLabel();
        userLoginText.setOpaque(false);
        userLoginText.setBounds(0, 80, 80, 20);
        userLoginText.setFont(new Font("宋体", Font.PLAIN, 13));
        userLoginText.setForeground(new Color(166, 166, 166));
        userLoginText.setText("账号登录");
        userLoginText.setHorizontalAlignment(JLabel.CENTER);
        userLoginPanel.add(userLoginText);
        rightBodyPanel.add(userLoginPanel);
        //用户密码登录输入框Panel
        var userLoginInputPanel = new JPanel();
        userLoginInputPanel.setBounds(25, 200, 250, 120);
        userLoginInputPanel.setLayout(null);
        userLoginInputPanel.setOpaque(false);
        //用户账号输入TextField
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
                if (userInputTextField.getText().isEmpty()) {
                    userInputTextField.setText("账号");
                }
            }
        });
        userInputTextBorder.add(userInputTextField);
        //用户密码输入TextField
        var userPassInputPasswordBorder = new JPanel();
        userPassInputPasswordBorder.setBounds(0, 60, 250, 60);
        userPassInputPasswordBorder.setLayout(null);
        userPassInputPasswordBorder.setOpaque(false);
        userPassInputPasswordBorder.setBorder(new CustomRoundRectBorder(textBorderColor, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(this.getColor());
                g.fillRect(x, y + height - this.getThickness() - 5, width, this.getThickness()); // 顶部边框
            }
        });
        var userPassInputPasswordField = getUserInputPasswordField(textFont, textColor);
        userPassInputPasswordBorder.add(userPassInputPasswordField);
        //用户密码输入框中的登录按钮
        var userLoginButton = getUserLoginButton(userInputTextField, userPassInputPasswordField,frame );
        userPassInputPasswordBorder.add(userLoginButton);
        userLoginInputPanel.add(userInputTextBorder);
        userLoginInputPanel.add(userPassInputPasswordBorder);
        rightBodyPanel.add(userLoginInputPanel);
        //管理员登录按钮
        var adminLoginPanel = new JPanel();
        adminLoginPanel.setLayout(null);
        adminLoginPanel.setBounds(160, 50, 80, 100);
        adminLoginPanel.setOpaque(false);
        adminLoginPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        var adminLoginRound = new JPanel() {
            private final Color unPressedColor = new Color(245, 245, 245);
            private final Color pressedColor = new Color(23, 196, 68);
            private boolean colorChangeSwitch = false;

            public void setColorChangeSwitch(boolean colorChangeSwitch) {
                this.colorChangeSwitch = colorChangeSwitch;
                this.repaint();
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (colorChangeSwitch) {
                    g2d.setColor(pressedColor);
                } else {
                    g2d.setColor(unPressedColor);
                }
                g2d.fillOval(0, 0, 79, 79);

                try{
                    var imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("img/login/AdminLoginIcon.png")));
                    g2d.drawImage(imageIcon.getImage(), 20, 20, 40, 40, null);
                }
                catch(NullPointerException e){
                    throw new RuntimeException(e);
                }
            }
        };
        adminLoginRound.setBounds(0, 0, 80, 80);
        adminLoginRound.setOpaque(false);
        adminLoginPanel.add(adminLoginRound);
        adminLoginPanel.add(adminLoginRound);
        rightBodyPanel.add(adminLoginPanel);
        var adminLoginText = new JLabel();
        adminLoginText.setOpaque(false);
        adminLoginText.setBounds(0, 80, 80, 20);
        adminLoginText.setFont(new Font("宋体", Font.PLAIN, 13));
        adminLoginText.setForeground(new Color(166, 166, 166));
        adminLoginText.setText("账号登录");
        adminLoginText.setHorizontalAlignment(JLabel.CENTER);
        adminLoginPanel.add(adminLoginText);
        rightBodyPanel.add(adminLoginPanel);
        //管理员密码登录输入框Panel
        var adminLoginInputPanel = getAdminLoginInputPanel(textBorderColor, textFont, textColor, userPassInputPasswordField, userInputTextField,frame );
        //用户登录和管理员登陆切换
        userLoginPanel.addMouseListener(new MouseAdapter() {
            private final Color firstColor = new Color(166, 166, 166);
            private final Color secondColor = new Color(17, 145, 255);

            @Override
            public void mousePressed(MouseEvent e) {
                if (userAndAdminSwitch) {
                    rightBodyPanel.remove(adminLoginInputPanel);
                    rightBodyPanel.add(userLoginInputPanel);
                    userAndAdminSwitch = false;
                    rightBodyPanel.repaint();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                userLoginRound.setColorChangeSwitch(true);
                userLoginText.setForeground(secondColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                userLoginRound.setColorChangeSwitch(false);
                userLoginText.setForeground(firstColor);
            }
        });
        adminLoginPanel.addMouseListener(new MouseAdapter() {
            private final Color firstColor = new Color(166, 166, 166);
            private final Color secondColor = new Color(23, 196, 68);

            @Override
            public void mousePressed(MouseEvent e) {
                if (!userAndAdminSwitch) {
                    rightBodyPanel.remove(userLoginInputPanel);
                    rightBodyPanel.add(adminLoginInputPanel);
                    userAndAdminSwitch = true;
                    rightBodyPanel.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                adminLoginRound.setColorChangeSwitch(true);
                adminLoginText.setForeground(secondColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                adminLoginRound.setColorChangeSwitch(false);
                adminLoginText.setForeground(firstColor);
            }
        });
        //将右侧Body,Below面板添加到右侧主面板
        var rightBelowPanel = getRightBelowPanel();
        rightPanel.add(rightBelowPanel);
        rightPanel.add(rightBodyPanel);
        //将右侧主面板添加到右侧主面板
        frame.add(rightPanel);
        frame.setVisible(true);
    }

    private JPanel getRightBelowPanel() {
        var rightBelowPanel=new JPanel();
        rightBelowPanel.setBackground(Color.WHITE);
        rightBelowPanel.setLayout(null);
        rightBelowPanel.setOpaque(false);
        rightBelowPanel.setBounds(0,430,300,150);
        var registerLabel=new JLabel();
        registerLabel.setBounds(0,0,100,50);
        registerLabel.setFont(new Font("宋体", Font.PLAIN,14));
        registerLabel.setForeground(new Color(117, 117, 117));
        registerLabel.setText("注册账号");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                registerFrame =new RegisterInterface();
            }
        });
        rightBelowPanel.add(registerLabel);
        return rightBelowPanel;
    }

    private static JPasswordField getUserInputPasswordField(Font textFont, Color textColor) {
        var userPassInputPasswordField = new JPasswordField() {
            private final char defaultChar = this.getEchoChar();
        };
        userPassInputPasswordField.setBounds(0, 0, 180, 50);
        userPassInputPasswordField.setBorder(null);
        userPassInputPasswordField.setFont(textFont);
        userPassInputPasswordField.setForeground(textColor);
        userPassInputPasswordField.setOpaque(false);
        userPassInputPasswordField.setEchoChar('\0');
        userPassInputPasswordField.setText("密码");
        userPassInputPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(userPassInputPasswordField.getPassword()).equals("密码")) {
                    userPassInputPasswordField.setEchoChar(userPassInputPasswordField.defaultChar);
                    userPassInputPasswordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(userPassInputPasswordField.getPassword()).isEmpty()) {
                    userPassInputPasswordField.setEchoChar('\0');
                    userPassInputPasswordField.setText("密码");
                }
            }
        });
        return userPassInputPasswordField;
    }

    private static JPanel getAdminLoginInputPanel(Color textBorderColor, Font textFont, Color textColor, JPasswordField userPassInputPasswordField, JTextField userInputTextField, JFrame frame) {
        var adminLoginInputPanel = new JPanel();
        adminLoginInputPanel.setBounds(25, 200, 250, 120);
        adminLoginInputPanel.setLayout(null);
        adminLoginInputPanel.setOpaque(false);
        //管理员账号输入TextField
        var adminInputTextBorder = new JPanel();
        adminInputTextBorder.setBounds(0, 0, 250, 60);
        adminInputTextBorder.setLayout(null);
        adminInputTextBorder.setOpaque(false);
        adminInputTextBorder.setBorder(new CustomRoundRectBorder(textBorderColor, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(this.getColor());
                g.fillRect(x, y + height - this.getThickness() - 5, width, this.getThickness()); // 底部边框
            }
        });
        var adminInputTextField = new JTextField();
        adminInputTextField.setBounds(0, 0, 220, 50);
        adminInputTextField.setBorder(null);
        adminInputTextField.setFont(textFont);
        adminInputTextField.setForeground(textColor);
        adminInputTextField.setText("账号");
        adminInputTextField.setOpaque(false);
        adminInputTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (adminInputTextField.getText().equals("账号")) {
                    adminInputTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (adminInputTextField.getText().isEmpty()) {
                    adminInputTextField.setText("账号");
                }
            }
        });
        adminInputTextBorder.add(adminInputTextField);
        //管理员密码输入TextField
        var adminPassInputPasswordBorder = new JPanel();
        adminPassInputPasswordBorder.setBounds(0, 60, 250, 60);
        adminPassInputPasswordBorder.setLayout(null);
        adminPassInputPasswordBorder.setOpaque(false);
        adminPassInputPasswordBorder.setBorder(new CustomRoundRectBorder(textBorderColor, 1) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(this.getColor());
                g.fillRect(x, y + height - this.getThickness() - 5, width, this.getThickness()); // 顶部边框
            }
        });
        var adminPassInputPasswordField = getAdminPassInputPasswordField(textFont, textColor);
        adminPassInputPasswordBorder.add(adminPassInputPasswordField);
        //管理员密码输入框中的登录按钮
        var adminLoginButton = getAdminLoginButton(userInputTextField, userPassInputPasswordField,frame );
        adminPassInputPasswordBorder.add(adminLoginButton);
        adminLoginInputPanel.add(adminInputTextBorder);
        adminLoginInputPanel.add(adminPassInputPasswordBorder);
        return adminLoginInputPanel;
    }

    private static JPasswordField getAdminPassInputPasswordField(Font textFont, Color textColor) {
        var adminPassInputPasswordField = new JPasswordField() {
            private final char defaultChar = this.getEchoChar();
        };
        adminPassInputPasswordField.setBounds(0, 0, 180, 50);
        adminPassInputPasswordField.setBorder(null);
        adminPassInputPasswordField.setFont(textFont);
        adminPassInputPasswordField.setForeground(textColor);
        adminPassInputPasswordField.setOpaque(false);
        adminPassInputPasswordField.setEchoChar('\0');
        adminPassInputPasswordField.setText("密码");
        adminPassInputPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(adminPassInputPasswordField.getPassword()).equals("密码")) {
                    adminPassInputPasswordField.setEchoChar(adminPassInputPasswordField.defaultChar);
                    adminPassInputPasswordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(adminPassInputPasswordField.getPassword()).isEmpty()) {
                    adminPassInputPasswordField.setEchoChar('\0');
                    adminPassInputPasswordField.setText("密码");
                }
            }
        });
        return adminPassInputPasswordField;
    }

    private static JPanel getAdminLoginButton(JTextField userInputTextField, JPasswordField userPassInputPasswordField, JFrame frame) {
        var adminLoginButton = new JPanel() {
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
        adminLoginButton.setBorder(null);
        adminLoginButton.setOpaque(false);
        adminLoginButton.setBounds(200, 5, 40, 40);
        adminLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                adminLoginButton.changeColor(true);
                adminLoginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                adminLoginButton.changeColor(false);
                adminLoginButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //获取用户名、密码字段
                String userId= userInputTextField.getText();
                String password= new String(userPassInputPasswordField.getPassword());
//                System.out.println(userId);
//                System.out.println(password);
                //从数据库比较用户用户名，密码
                //若检测通过，跳转到用户开始界面
                //dispose登录窗口时，登录窗口对象会被销毁，所以使用界面的对象应从外部传入，保证不会在dispose当前窗口时，关闭其他窗口
                frame.dispose();

            }
        });
        return adminLoginButton;
    }

    private static JPanel getUserLoginButton(JTextField userInputTextField, JPasswordField userPassInputPasswordField, JFrame frame) {
        var userLoginButton = new JPanel() {
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
        userLoginButton.setBorder(null);
        userLoginButton.setOpaque(false);
        userLoginButton.setBounds(200, 5, 40, 40);
        userLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                userLoginButton.changeColor(true);
                userLoginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userLoginButton.changeColor(false);
                userLoginButton.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //获取用户名、密码字段
                String userId= userInputTextField.getText();
                String password= new String(userPassInputPasswordField.getPassword());
//                System.out.println(userId);
//                System.out.println(password);
                //从数据库比较用户用户名，密码
                //若检测通过，跳转到用户开始界面
                //dispose登录窗口时，登录窗口对象会被销毁，所以使用界面的对象应从外部传入，保证不会在dispose当前窗口时，关闭其他窗口
                frame.dispose();
            }
        });
        return userLoginButton;
    }

    private JPanel getLeftPanel() {
        var leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 600));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null);
        try {
            var leftImageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("img/login/LeftImage.jpg")));
            var leftImagePanel = new ImagePanel(leftImageIcon, 0, 0, 600, 500);
            leftPanel.add(leftImagePanel);
        }
        catch(NullPointerException e){
            throw new RuntimeException(e);
        }
        return leftPanel;
    }

    private static JPanel getRightTitleToolBar(JFrame frame) {
        var rightTitleToolBar = new JPanel();
        rightTitleToolBar.setBounds(10, 10, 280, 35);
        rightTitleToolBar.setBackground(Color.WHITE);
        rightTitleToolBar.setLayout(null);
        rightTitleToolBar.setOpaque(false);
        //创建右上角关闭按钮
        var buttonClose = getButtonClose(frame);
        //创建右上角设置(六边形齿轮)按钮
        var settingButton = getSettingButton();
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

    private static ShapeDeepenJPanel getSettingButton() {
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
        return settingButton;
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
        return buttonClose;
    }
}