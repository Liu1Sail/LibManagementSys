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
public class InputInnerPasswordField extends JPasswordField {
    private final InputInnerPasswordField passwordField = this;
    private int arcWidth = 5;
    private int arcHeight = 5;
    private Color borderColor = Color.BLACK;
    private Color innerTextColor = Color.BLACK;
    private Color textColor = Color.BLACK;
    private String innerText;
    private final char defaultChar = passwordField.getEchoChar();
    public void gainFocusMovement(InputInnerPasswordField passwordField) {}

    public void lostFocusMovement(InputInnerPasswordField passwordField) {}

    public InputInnerPasswordField(String innerText) {
        this.innerText = innerText;
        this.setEchoChar('\0');
        this.setText(innerText);
        addListener();
    }

    public InputInnerPasswordField(String innerText, Color borderColor, Color backgroundColor, Color innerTextColor, Color textColor, int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderColor = borderColor;
        this.setBackground(backgroundColor);
        this.innerTextColor = innerTextColor;
        this.textColor = textColor;
        this.innerText = innerText;
        this.setEchoChar('\0');
        this.setText(innerText);
        addListener();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(borderColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(1, 1, this.getWidth() - 2, this.getHeight() - 2, arcWidth, arcHeight);
    }

    private void addListener() {
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(innerText)) {
                    passwordField.setEchoChar(defaultChar);
                    passwordField.setText("");
                }
                gainFocusMovement(passwordField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar('\0');
                    passwordField.setText(innerText);
                }
                lostFocusMovement(passwordField);
            }
        });
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