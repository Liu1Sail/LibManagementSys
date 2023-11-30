package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.User;
import user.UserInfoManager;

import java.sql.SQLException;

public interface UserSQLDao extends BaseSQLDao{


    public User getOneById(int uid)throws SQLException;
    public void updateAll(User user)throws SQLException;
    public void updatePasswordById(int id,String password)throws SQLException;
    public int insert(User user)throws SQLException;
    public void deleteById(int id)throws SQLException;

}
