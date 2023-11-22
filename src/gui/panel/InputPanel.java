package gui.panel;

import javax.swing.*;
import java.awt.*;

import gui.tool.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 前方带提示文字的输入框
 * @date 2023/11/22
 */

public class InputPanel extends JPanel {
    private final InputPanel panel = this;
    private JLabel textLabel = new JLabel();
    private JTextField inputField = new JTextField();
    private Font defaultFont = new Font("宋体", Font.PLAIN, 18);

    public InputPanel(String text) {
        var chProcess = CharacterProcess.getInstance(text);
        int singleCharLength = 10;
        int textWidth = (int) (chProcess.doubleByteNumber() * singleCharLength * 1.5) + chProcess.singleByteNumber() * singleCharLength;
        System.out.println(textWidth);
        System.out.println(textWidth);
        this.setSize(textWidth + 130, 100);
        this.setOpaque(false);
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

    public String getInputText() {
        return inputField.getText();
    }

    public void setTextFont(Font font) {
        textLabel.setFont(font);
    }
}
