package com.qdu.niit.library.entity;

/**
 * 用户对象
 * 存储uid 和 uname
 */
public class User {
    public int getUID() {return m_UId;}
    public String getUName(){return m_UName;}
    public String getUPassword(){return m_UPassword;}
    public void setUname(String newUname){
        m_UName = newUname;
    }
    public void setUPassword(String newPassword){m_UPassword = newPassword;}


    public User(Integer uId , String uName , String uPassword) {
        m_UId = uId ;
        m_UName = uName;
        m_UPassword = uPassword;
    }

    @Override
    public String toString() {
        return "[" + "  "+ m_UId+ "  " + m_UName +"  " +m_UPassword + "  "+"]";
    }

    //---------------------------------------------------------------------------------//
    private final Integer  m_UId;
    private String m_UName;
    private String m_UPassword;
}
