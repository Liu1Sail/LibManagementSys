package com.qdu.niit.library.gui.tool;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 各种用于文字处理的方法
 * @date 2023/11/22
 */

public class CharacterProcess {
    private final String text;

    private CharacterProcess(String text) {
        this.text = text;
    }

    public static CharacterProcess getInstance(String text){
        return new CharacterProcess(text);
    }
    public boolean isAllEnglish(){
        return text.getBytes().length==text.length();
    }
    public int doubleByteNumber(){
        return text.getBytes().length-text.length();
    }
    public int singleByteNumber(){
        return text.length()*2-text.getBytes().length;
    }
}
