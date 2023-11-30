package com.qdu.niit.library.gui.tool;

/**
 * @author 李冠良
 * @program LibManagementSys
 * @description 实体类。保存一个用户的所有信息
 * @date 2023/11/29
 */

public class UserGui {
    private String id;
    private String name;
    private String password;
    private String age;
    private String gender;
    private String phone;
    private String email;

    public UserGui(String id, String name, String password, String age, String gender, String phone, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public UserGui(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserGui(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
