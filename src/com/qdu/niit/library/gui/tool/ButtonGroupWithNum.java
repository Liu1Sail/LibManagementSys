package com.qdu.niit.library.gui.tool;

import javax.swing.*;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description
 * @date 2023/12/22
 */
public class ButtonGroupWithNum extends ButtonGroup {
    private int selectedButtonNum = 1;

    public int getSelectedButtonNum() {
        return selectedButtonNum;
    }

    public void setSelectedButtonNum(int selectedButtonNum) {
        this.selectedButtonNum = selectedButtonNum;
    }
}