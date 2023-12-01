package com.qdu.niit.library.gui.animation;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 实现了文字浮现效果的JLabel
 * @date 2023/11/30
 */
@SuppressWarnings("unused")
public class TextEmergeLabel extends JLabel {
    private String text;
    private Timer timer;
    private double durationTime;
    private int intervalTime;
    private double intervalOpacity;
    //默认透明度从0-255，及完全透明到不透明
    private int startOpacity = 0;
    private int endOpacity = 255;
    private int nowOpacity = 0;
    private Color color = Color.BLACK;
    private int r;
    private int g;
    private int b;

    private void initial() {
        this.setForeground(color);
        nowOpacity = startOpacity;
        intervalTime = 10;
        timer.setDelay(intervalTime);
        intervalOpacity = (endOpacity-startOpacity)/(durationTime*(1000.0/intervalTime));
        System.out.println(intervalOpacity);
        //计时器触发貌似有延迟或者设置字体颜色有延迟
        //如果用触发间隔时间改变浮现速度，会导致实际速度有一个最高速度（1s左右完全显示）
    }

    public TextEmergeLabel(String text, int durationTime) {
        this.text = text;
        this.setText(text);
        this.durationTime = durationTime;
        timer = new Timer(0, e -> performance());
    }

    public TextEmergeLabel(String text, double durationTime, int startOpacity, int endOpacity, Color color) {
        this.text = text;
        this.setText(text);
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        this.color = new Color(r, g, b, startOpacity);
        this.durationTime = durationTime;
        this.startOpacity = startOpacity;
        this.endOpacity = endOpacity;
        timer = new Timer(0, e -> performance());
    }

    private void performance() {
        nowOpacity+= (int) intervalOpacity;
        if(nowOpacity>=255){
            nowOpacity=255;
            this.stop();
        }
        this.setForeground(new Color(r,b,g,nowOpacity));
        if(nowOpacity>=255){
            nowOpacity=255;
            this.stop();
        }
    }

    public void start() {
        initial();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void restart() {
        initial();
        timer.restart();
    }

    public String getTextContent() {
        return text;
    }

    public void setTextContent(String text) {
        this.text = text;
        this.setText(text);
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public double getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
        intervalTime = (int) (durationTime * 1.0 / 255);
    }

    public int getStartOpacity() {
        return startOpacity;
    }

    public void setStartOpacity(int startOpacity) {
        this.startOpacity = startOpacity;
    }

    public int getEndOpacity() {
        return endOpacity;
    }

    public void setEndOpacity(int endOpacity) {
        this.endOpacity = endOpacity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        this.color = new Color(r, g, b, startOpacity);
    }
}