package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.User;
import user.UserInfoManager;

import java.sql.SQLException;

public interface UserSQLDao {
    public User selectUserById(int uid)throws SQLException;
    public void updateUser(User user)throws SQLException;
    public int insertUser(String userName , String password)throws SQLException;
    public void dropUserById(int id)throws SQLException;


    public void setLocalUserByIdAndPassword(int id , String password)throws SQLException;
    public User getLocalUser();
}
