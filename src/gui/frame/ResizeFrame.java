package gui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description JFrame的子类，可用鼠标在窗口边界调整窗口大小
 * @date 2023/11/16
 */

public class ResizeFrame extends JFrame {
    private final JFrame frame=this;
    private boolean isTopLeft;// 是否处于左上角调整窗口状态
    private boolean isTop;// 是否处于上边界调整窗口状态
    private boolean isTopRight;// 是否处于右上角调整窗口状态
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    private boolean isBottomLeft;// 是否处于左下角调整窗口状态
    private boolean isLeft;// 是否处于左边界调整窗口状态
    private final static int RESIZE_WIDTH = 5;// 判定是否为调整窗口状态的范围与边界距离
    private final int min_width = 250;// 窗口最小宽度
    private final int min_height = 175;// 窗口最小高度
    private Point offsetMouseToFrame = new Point();

    public ResizeFrame() {
        frame.setUndecorated(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                offsetMouseToFrame.x = e.getXOnScreen() - frame.getLocationOnScreen().x;
                offsetMouseToFrame.y = e.getYOnScreen() - frame.getLocationOnScreen().y;
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Cursor cursorType= Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
                int rightResizeX=frame.getWidth()-RESIZE_WIDTH;//窗口拉伸区域X坐标最小值
                int rightResizeY=frame.getHeight()-RESIZE_WIDTH;//窗口拉伸区域Y坐标最小值
                int nowX=e.getX();
                int nowY=e.getY();
                isTopLeft=isTop=isTopRight=isRight=isBottomRight=isBottom=isBottomLeft=isLeft=false;
                if (nowY<RESIZE_WIDTH){
                    if(nowX<RESIZE_WIDTH){
                        isTopLeft=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
                    }
                    else if(nowX>=rightResizeX){
                        isTopRight=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
                    }
                    else{
                        isTop=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
                    }
                }
                else if(nowY>=rightResizeY){
                    if(nowX<RESIZE_WIDTH){
                        isBottomLeft=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
                    }
                    else if(nowX>=rightResizeX){
                        isBottomRight=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
                    }
                    else{
                        isBottom=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
                    }
                }
                else{
                    if(nowX<RESIZE_WIDTH){
                        isLeft=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
                    }
                    else if(nowX>=rightResizeX){
                        isRight=true;
                        cursorType= Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
                    }
                }
                frame.setCursor(cursorType);
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                int nowX = e.getX();
                int nowY = e.getY();
                if(isTopLeft||isTop||isTopRight||isRight||isBottomRight||isBottom||isBottomLeft||isLeft){
                    return ;
                }
                int newX = e.getXOnScreen() - offsetMouseToFrame.x;
                int newY = e.getYOnScreen() - offsetMouseToFrame.y;
                frame.setLocation(newX, newY);
            }
        });
    }
}