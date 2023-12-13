package com.qdu.niit.library.exception;

public class ObjectHaveNoAttribute extends Exception{
    public ObjectHaveNoAttribute() {
        super("该对象没有此属性!");
    }

    public ObjectHaveNoAttribute(String message) {
        super(message);
    }
}
