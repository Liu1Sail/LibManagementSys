package com.qdu.niit.library.gui.input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 提示文字在输入框内部的输入框
 * @date 2023/12/01
 */
@SuppressWarnings("unused")
public class InputInnerPasswordPanel extends JPasswordField {
    private final JPasswordField textField = this;
    private int arcWidth = 5;
    private int arcHeight = 5;
    private Color borderColor = Color.BLACK;
    private Color innerTextColor = Color.BLACK;
    private Color textColor = Color.BLACK;
    private String innerText;

    public InputInnerPasswordPanel(String innerText) {
        this.setOpaque(false);
        this.innerText = innerText;
        this.setText(innerText);
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(textField.getPassword()).equals(innerText)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(textField.getPassword()).isEmpty()) {
                    textField.setText(innerText);
                }
            }
        });
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(borderColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(1, 0, this.getWidth() - 1, this.getHeight() - 1, arcWidth, arcHeight);
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getArcWidth() {
        return arcWidth;
    }

    public void setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
    }

    public int getArcHeight() {
        return arcHeight;
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
    }

    public Color getInnerTextColor() {
        return innerTextColor;
    }

    public void setInnerTextColor(Color innerTextColor) {
        this.innerTextColor = innerTextColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public String getInnerText() {
        return innerText;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }
}