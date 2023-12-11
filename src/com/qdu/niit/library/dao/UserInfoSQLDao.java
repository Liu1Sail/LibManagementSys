package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.UserInfo;

import java.sql.SQLException;

public interface UserInfoSQLDao extends BaseSQLDao {
    public void insert(UserInfo info)throws SQLException;
    public void updateAll(UserInfo info)throws SQLException;
    public UserInfo getOneById(int id)throws SQLException;
}
