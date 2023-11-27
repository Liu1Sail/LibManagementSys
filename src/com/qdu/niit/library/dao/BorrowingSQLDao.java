package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Borrowing;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowingSQLDao
{
    /**
     * 向表中添加
     * @param in 实体类
     * @return 添加后的受影响行数
     */
    int insert(Borrowing in)throws SQLException;

    int deleteByUid(int uid)throws SQLException;

    int deleteByBid(int bid)throws SQLException;
    int deleteByUidAndBid(int uid,int bid)throws SQLException;

    ArrayList<Borrowing>getAll()throws SQLException;
    ArrayList<Borrowing>getAllByUid(int uid)throws SQLException;
    ArrayList<Borrowing>getAllByBid(int bid)throws SQLException;
    ArrayList<Borrowing>getOneByUidAndBid(int uid,int bid)throws SQLException;
}
