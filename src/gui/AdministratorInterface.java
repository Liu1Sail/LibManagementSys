package gui;

import gui.component.ShapeDeepenPanel;
import gui.frame.ResizeFrame;
import gui.panel.centerAddBookPanel;

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
@SuppressWarnings("unused")
public class AdministratorInterface extends ResizeFrame {
    private final AdministratorInterface frame = this;
    private final JPanel centerBottomPanel;
    private final int initialWidth=1000;
    private final int initialHeight=700;
    private int mouseX;
    private int mouseY;

    public AdministratorInterface() {
        frame.setLayout(new BorderLayout());
        frame.setSize(initialWidth, initialHeight);
        frame.setLocationRelativeTo(null);
        frame.setTitle("管理系统");
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX=e.getX();
                mouseY=e.getY();
            }
        });
        //鼠标拖动窗口
        var titleFont=new Font("宋体",Font.PLAIN,25);
        var bottomPanel=new JPanel();
        bottomPanel.setBounds(0,0,1000,700);
        bottomPanel.setLayout(new BorderLayout());

        var leftPanel=new JPanel();//左侧面板
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLocation(0,0);
        leftPanel.setPreferredSize(new Dimension(200,0));
        leftPanel.setLayout(null);
        var sideBarOptionFont=new Font("宋体",Font.PLAIN,20);
        var bookOption = getBookOption(sideBarOptionFont);
        leftPanel.add(bookOption);
        var accountOption=getAccountOption(sideBarOptionFont);
        leftPanel.add(accountOption);
        bottomPanel.add(leftPanel,BorderLayout.WEST);

        var topBottomPanel=new JPanel();//顶部根面板
        topBottomPanel.setBackground(Color.WHITE);
        topBottomPanel.setPreferredSize(new Dimension(800,60));
        topBottomPanel.setLayout(null);

        var leftTopPanel = getLeftTopPanel(leftPanel, topBottomPanel, titleFont);//左上角标题面板
        topBottomPanel.add(leftTopPanel);

        var topPanel=new JPanel();
        topPanel.setBounds(leftPanel.getPreferredSize().width,0,topBottomPanel.getPreferredSize().width,topBottomPanel.getPreferredSize().height);
        topPanel.setLayout(null);
        topPanel.setBackground(Color.WHITE);
        var buttonClose=getButtonClose();
        buttonClose.setBounds(topPanel.getWidth()-30,10,20,20);
        topPanel.add(buttonClose);
        topBottomPanel.add(topPanel);
        bottomPanel.add(topBottomPanel,BorderLayout.NORTH);

        centerBottomPanel=new JPanel();//中心面板
        centerBottomPanel.setBackground(new Color(236,238,245));
        centerBottomPanel.setLayout(null);
        bottomPanel.add(centerBottomPanel,BorderLayout.CENTER);
        //测试代码区
        //
        frame.add(bottomPanel,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel getBookOption(Font sideBarOptionFont) {
        var bookOption=new JPanel();
        bookOption.setBounds(0,0,400,80);
        bookOption.setLayout(null);
        bookOption.setBackground(Color.WHITE);
        var bookOptionText=new JLabel();
        bookOptionText.setBounds(0,0,200,80);
        bookOptionText.setText("图书管理");
        bookOptionText.setFont(sideBarOptionFont);
        bookOptionText.setVerticalAlignment(SwingConstants.CENTER);
        bookOptionText.setHorizontalAlignment(SwingConstants.CENTER);
        bookOption.add(bookOptionText);
        var bookAddChildOption = getChildOption(sideBarOptionFont,80,"添加图书",4,1);
        bookOption.add(bookAddChildOption);
        var bookDeleteChildOption = getChildOption(sideBarOptionFont,160,"删除图书",4,2);
        bookOption.add(bookDeleteChildOption);
        var bookModifyChildOption = getChildOption(sideBarOptionFont,240,"修改图书信息",4,3);
        bookOption.add(bookModifyChildOption);
        var bookSearchChildOption = getChildOption(sideBarOptionFont,320,"搜索图书信息",4,4);
        bookOption.add(bookSearchChildOption);
        bookOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                bookOption.setBounds(0,0,200,400);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int x=e.getX(),y=e.getY();
                if(x<0||x>=200||y<0||y>=360){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    bookOption.setBounds(0,0,200,80);
                }
            }

        });
        return bookOption;
    }
    private JPanel getAccountOption(Font sideBarOptionFont) {
        var accountOption=new JPanel();
        accountOption.setBounds(0,80,400,80);
        accountOption.setLayout(null);
        accountOption.setBackground(Color.WHITE);
        var accountOptionText=new JLabel();
        accountOptionText.setBounds(0,0,200,80);
        accountOptionText.setText("用户管理");
        accountOptionText.setFont(sideBarOptionFont);
        accountOptionText.setVerticalAlignment(SwingConstants.CENTER);
        accountOptionText.setHorizontalAlignment(SwingConstants.CENTER);
        accountOption.add(accountOptionText);
        var accountAddChildOption = getChildOption(sideBarOptionFont,80,"添加用户",4,1);
        accountOption.add(accountAddChildOption);
        var accountDeleteChildOption = getChildOption(sideBarOptionFont,160,"删除用户",4,2);
        accountOption.add(accountDeleteChildOption);
        var accountModifyChildOption = getChildOption(sideBarOptionFont,240,"修改用户信息",4,3);
        accountOption.add(accountModifyChildOption);
        var accountSearchChildOption = getChildOption(sideBarOptionFont,320,"搜索用户信息",4,4);
        accountOption.add(accountSearchChildOption);
        accountOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                accountOption.setBounds(0,80,200,400);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int x=e.getX(),y=e.getY();
                if(x<0||x>=200||y<0||y>=360){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    accountOption.setBounds(0,80,200,80);
                }
            }

        });

        return accountOption;
    }
    private JLabel getChildOption(Font sideBarOptionFont, int positionY, String text,int OptionAmount,int number) {
        var childOption=new JLabel(text){
            private int panelNumber=1;
        };
        childOption.panelNumber=number;
        childOption.setFont(sideBarOptionFont);
        childOption.setBounds(0,positionY,200,80);
        childOption.setBackground(Color.WHITE);
        childOption.setOpaque(true);
        childOption.setVerticalAlignment(SwingConstants.CENTER);
        childOption.setHorizontalAlignment(SwingConstants.CENTER);
        childOption.setOpaque(true);
        childOption.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getX()<0||e.getX()>=200||(number==1&&e.getY()<0)||(number==OptionAmount&&e.getY()>=80)){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    childOption.getParent().setBounds(0,childOption.getParent().getY(),200,80);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                centerBottomPanel.removeAll();
                switch(childOption.panelNumber){
                    case 1->{centerBottomPanel.add(new centerAddBookPanel());frame.repaint();}
                    case 2->{frame.repaint();}
                    case 3->{frame.repaint();}
                    case 4->{frame.repaint();}
                    case 5->{frame.repaint();}
                    case 6->{frame.repaint();}
                    case 7->{frame.repaint();}
                    case 8->{frame.repaint();}
                    default->{frame.repaint();}
                }
            }
        });
        return childOption;
    }

    private JPanel getLeftTopPanel(JPanel leftPanel, JPanel topBottomPanel, Font titleFont) {
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

    private ShapeDeepenPanel getButtonClose() {
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

    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }
}