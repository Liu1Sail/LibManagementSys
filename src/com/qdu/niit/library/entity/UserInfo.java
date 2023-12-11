package com.qdu.niit.library.entity;

import java.util.Date;
import java.util.Objects;

public class UserInfo {
    public int getID(){
        return m_UID;
    }
    public String getName() {
        return m_UName;
    }

    public Date getBirthday(){return m_UBirthday;}
    public void setBirthday(Date date){m_UBirthday = date;}
    public Gender getGender() {
        return m_UGender;
    }
    public void setPhone(String phone){
        m_UPhone = phone;
    }

    public String getPhone() {
        return m_UPhone;
    }



    public void setName(String m_UName) {
        this.m_UName = m_UName;
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
    public UserInfo(int id , String name, Date birthday , Gender gender , String phone, String email){
        m_UID = id ;m_UName = name;
        m_UBirthday = birthday;
        m_UGender = gender;
        m_UEmail = email; m_UPhone = phone ;
    }

    @Override
    public String toString() {
        return m_UID+" "+m_UName+" "+m_UBirthday + " "+m_UEmail+" "+m_UPhone + " "+m_UGender;
    }

    /*-----------------------------------------------------------------------------------*/
    private final int m_UID;

    private String m_UName;

    private Date m_UBirthday;
    private Gender m_UGender;
    private String m_UPhone;
    private String m_UEmail;
}
