package gui.border;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @author 李冠良
 * @program Chat
 * @description 创建圆形边框
 * @date 2023/11/05
 */

public class CustomRoundBorder implements Border {
    private Color color;

    public CustomRoundBorder(Color color) {
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(x,y,x+width,y+height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
