package com.qdu.niit.library.gui.component;
import javax.swing.*;
import java.awt.*;

/**
 * @author 李冠良
 * @program Chat
 * @description
 * @date 
 */
public class ImagePanel extends JPanel {
    private int imageHeight = 100;
    private int imageWidth = 100;
    private int imageX = 100;
    private int imageY = 100;
    private ImageIcon image;

    /**
     * @param image       图片，ImageIcon对象
     * @param imageHeight 图片高度
     * @param imageWidth  图片宽度
     * @param imageX      图片坐标X
     * @param imageY      图片坐标Y
     */
    public ImagePanel(ImageIcon image, int imageX, int imageY, int imageHeight, int imageWidth) {
        this.setLayout(null);
        this.setOpaque(true);
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.imageX = imageX;
        this.imageY = imageY;
        this.image = image;
        this.setBounds(imageX, imageY, imageWidth, imageHeight);
    }

    /**
     * 重写paintComponent函数，绘制图片
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        //此处的x，y是图片在ImagePanel里面的相对坐标，为了使图片填满ImagePanel均设为0。
        g.drawImage(image.getImage(), 0, 0, this.imageWidth, this.imageHeight, null);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageX() {
        return imageX;
    }

    public void setImageX(int imageX) {
        this.imageX = imageX;
    }

    public int getImageY() {
        return imageY;
    }

    public void setImageY(int imageY) {
        this.imageY = imageY;
    }
}
