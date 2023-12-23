package com.qdu.niit.library.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 输出对象函数，可以把对象所有的 变量名 和 对应的取值 全部保存在目标文件中
 */
public class Export implements Runnable{
    /**
     *
     * @param name 目标输出文件名
     * @param objs 要输出的对象
     */
    Export(String name, Object[] objs){
        this.exportName = Optional.ofNullable(name);
        this.objs = objs;
    }
    public Optional<String> exportName = Optional.empty();
    public Object[] objs;
    @Override
    public void run() {
        Class<?> example = objs[0].getClass();
        String name = this.exportName.orElse(example.getName());
        String path = "LibManagementSys\\out\\" + name + ".txt";

        //保存objs到文件,反射获得变量名和变量值
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            for(Object obj : objs) {
                Class<?> clazz = obj.getClass();
                Field[] fields = clazz.getFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(obj);
                    bw.write(fieldName + " = " + value);
                    bw.newLine();
                }
                bw.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing field", e);
        }
    }
}
