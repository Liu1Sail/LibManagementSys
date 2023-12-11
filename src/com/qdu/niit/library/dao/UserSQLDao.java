package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.User;

import java.sql.SQLException;

public interface UserSQLDao extends BaseSQLDao{
    public User getOneByIdAndPassword(int id,String password)throws SQLException;
    public User getOneByNameAndPassword(String name , String password)throws SQLException;
    public User[] getAll()throws SQLException;
    public void updateAll(User user)throws SQLException;
    public void updatePasswordById(int id,String password)throws SQLException;
    public int insert(User user)throws SQLException;
    public void deleteById(int id)throws SQLException;
}
