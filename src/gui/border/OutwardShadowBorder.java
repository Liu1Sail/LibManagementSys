package gui.border;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @author 作者姓名
 * @program LibManagementSys
 * @description 外扩阴影边框，阴影由组件范围向外扩展
 * @date 2023/11/15
 */

public class OutwardShadowBorder implements Border {
    private int top;
    private int bottom;
    private int left;
    private int right;
    private int pixel;

    public OutwardShadowBorder(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public OutwardShadowBorder(int pixel) {
        this.pixel = pixel;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int i;
//        for (i = 0; i < pixel; i++) {
//            g.setColor(new Color(0, 0, 0, 1));
//            g.fillRoundRect(i,i,width-1-i,height-1-i,2,2);
//        }
        g.setColor(new Color(0, 0, 0, 10));
        g.fillRoundRect(0,0,width-1,height-1,5,5);
        g.getClip();
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRoundRect(10,10,width-1-20,height-1-20,10,10);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(top, left, bottom, right);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
