package com.qdu.niit.library.gui.input;

import com.qdu.niit.library.gui.tool.CharacterProcess;

import javax.swing.*;
import java.awt.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 前方带提示文字的输入框
 * @date 2023/11/22
 */
@SuppressWarnings("unused")
public class InputPasswordPanel extends JPanel {
    private final JLabel textLabel = new JLabel();
    private final JPasswordField inputField = new JPasswordField();
    private final Font defaultFont = new Font("宋体", Font.PLAIN, 18);

    public InputPasswordPanel(String text) {
        var chProcess = CharacterProcess.getInstance(text);
        int singleCharLength = 10;
        int textWidth = (int) (chProcess.doubleByteNumber() * singleCharLength * 1.5) + chProcess.singleByteNumber() * singleCharLength;
        if(textWidth==0){
            textWidth=150;
        }
        this.setSize(textWidth + 130, 100);
        this.setOpaque(false);
        this.setLayout(null);
        this.textLabel.setText(text);
        textLabel.setBounds(0, 0, textWidth, 50);
        textLabel.setFont(defaultFont);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        inputField.setBounds(textWidth - 10, 10, 130, 30);
        inputField.setFont(defaultFont);
        this.add(textLabel);
        this.add(inputField);
        //
    }

    public String getText() {
        return textLabel.getText();
    }

    public void setText(String text) {
        textLabel.setText(text);
    }
    public void setInputText(String text) {
        inputField.setText(text);
    }
    public String getInputText() {
        return new String(inputField.getPassword());
    }

    public void setTextFont(Font font) {
        textLabel.setFont(font);
    }
}
