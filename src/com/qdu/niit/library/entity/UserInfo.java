package com.qdu.niit.library.entity;

import java.util.Objects;

public class UserInfo {
    public int getID(){
        return m_UID;
    }
    public void setPhone(String phone){
        m_UPhone = phone;
    }

    public String getPhone() {
        return m_UPhone;
    }

    public String getName() {
        return m_UName;
    }

    public void setName(String m_UName) {
        this.m_UName = m_UName;
    }

    public Gender getGender() {
        return m_UGender;
    }

    public void setGender(String gender) {
        for(Gender g : Gender.values()){
            if(Objects.equals(g.toString(), gender)) {
                m_UGender = g;
                return;
            }
        }
        m_UGender = Gender.OTHER;
    }

    public String getEmail() {
        return m_UEmail;
    }

    public void setEmail(String m_UEmail) {
        this.m_UEmail = m_UEmail;
    }

    public enum Gender
    {
        MALE("Male"), FEMALE("Female") , OTHER("Other");
        @Override
        public String toString() {
            return m_Str;
        }
        private final String m_Str;
        Gender(String str){m_Str = str;}
    }
    public UserInfo(int id , String phone , String name , Gender gender , String email){
        m_UID = id ;
        m_UPhone = phone ;
        m_UName = name;
        m_UGender = gender;
        m_UEmail = email;
    }
    private final int m_UID;
    private String m_UPhone;
    private String m_UName;
    private Gender m_UGender;
    private String m_UEmail;
}
