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

    public OutwardShadowBorder(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(top,left,bottom,right);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
