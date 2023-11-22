package com.qdu.niit.library.entity;

/**
 * 用户对象
 * 存储uid 和 uname
 */
public class User {
    /**
     * @return uid
     */
    public int getUID() {return m_UID;}

    /**
     * @return uname
     */
    public String getUname(){return m_Uname;}

    /**
     * @param newUname 要改的新名称
     */
    public void setUname(String newUname){
        m_Uname = newUname;
    }
    public User(int uid , String uname) {
        m_UID = uid ;
        m_Uname = uname;
    }
//---------------------------------------------------------------------------------//
    private final int  m_UID;
    private String m_Uname;
}
