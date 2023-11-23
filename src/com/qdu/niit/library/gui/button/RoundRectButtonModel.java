package com.qdu.niit.library.gui.button;

import com.qdu.niit.library.gui.border.RoundBorder;

import javax.swing.*;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 圆角矩形按钮模板
 * @date 2023/11/22
 */

public class RoundRectButtonModel extends JButton {
    private int FilletRadius =0;
    private Color borderColor=Color.BLACK;
    public RoundRectButtonModel(String s,int FilletRadius)
    {
        super(s);
        this.setMargin(new Insets(0,0,0,0));//去除文字与按钮的边沿
        this.setBorder(new RoundBorder(FilletRadius));//圆角矩形边界
        this.setContentAreaFilled(false);//取消原先画矩形的设置
        //this.setBorderPainted(false);//会导致按钮没有明显边界
        this.setFocusPainted(false);//去除文字周围的虚线框
        this.setOpaque(false);//未知原因，无效，故使用setBackground(null)
        this.setBackground(null);
        this.setFocusPainted(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2d.setColor(Color.GREEN);//按下后按钮变成绿色
        } else {
            g2d.setColor(getBackground());
        }
        g2d.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,FilletRadius,FilletRadius);//填充圆角矩形边界
        // 这个调用会画一个标签和焦点矩形。
//        super.paintComponent(g);
    }
    public int getFilletRadius() {
        return FilletRadius;
    }

    public void setFilletRadius(int filletRadius) {
        FilletRadius = filletRadius;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
}
