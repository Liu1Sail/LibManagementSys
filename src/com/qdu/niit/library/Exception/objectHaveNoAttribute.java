package com.qdu.niit.library.Exception;

public class objectHaveNoAttribute extends Exception{
    public objectHaveNoAttribute() {
        super("该对象没有此属性!");
    }

    public objectHaveNoAttribute(String message) {
        super(message);
    }
}
