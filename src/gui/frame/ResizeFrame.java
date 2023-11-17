package gui.frame;

import javax.swing.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description JFrame的子类，可用鼠标在窗口边界调整窗口大小
 * @date 2023/11/16
 */

public class ResizeFrame extends JFrame {
    private boolean isTopLeft;// 是否处于左上角调整窗口状态
    private boolean isTop;// 是否处于上边界调整窗口状态
    private boolean isTopRight;// 是否处于右上角调整窗口状态
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    private boolean isBottomLeft;// 是否处于左下角调整窗口状态
    private boolean isLeft;// 是否处于左边界调整窗口状态
    private final static int RESIZE_WIDTH = 5;// 判定是否为调整窗口状态的范围与边界距离
    private int min_width = 250;// 窗口最小宽度
    private int min_height = 175;// 窗口最小高度

    public ResizeFrame() {

    }
}