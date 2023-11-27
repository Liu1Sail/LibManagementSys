package com.qdu.niit.library.gui.border;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 圆角矩形边框
 * @date 2023/11/22
 */

public class RoundBorder implements Border {
    private int FilletRadius = 0;

    public RoundBorder(int filletRadius) {
        FilletRadius = filletRadius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //使用黑色在组件的外边缘绘制一个圆角矩形
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 1, c.getWidth() - 1, c.getHeight() - 2, FilletRadius, FilletRadius);
    }

    public int getFilletRadius() {
        return FilletRadius;
    }

    public void setFilletRadius(int filletRadius) {
        FilletRadius = filletRadius;
    }
}
