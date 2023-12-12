package com.qdu.niit.library.Exception;

public class ObjectHaveNoAttribute extends Exception{
    public ObjectHaveNoAttribute() {
        super("该对象没有此属性!");
    }

    public ObjectHaveNoAttribute(String message) {
        super(message);
    }
}
