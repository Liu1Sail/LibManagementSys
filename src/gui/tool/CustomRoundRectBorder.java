package gui.tool;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @author 李冠良
 * @program Chat
 * @description 创建圆角矩形边框
 * @date 2023/11/05
 */

public class CustomRoundRectBorder implements Border {
    private Color color;
    private int thickness;

    public CustomRoundRectBorder(Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillRect(x, y, width, thickness); // 顶部边框
        g.fillRect(x, y + height - thickness, width, thickness); // 底部边框
        g.fillRect(x, y, thickness, height); // 左侧边框
        g.fillRect(x + width - thickness, y, thickness, height); // 右侧边框
    }
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, thickness, thickness, thickness);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
}
