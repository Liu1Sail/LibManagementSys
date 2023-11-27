package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Borrowing;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface BorrowingSQLDao
{
    /**
     * 向表中添加
     * @param in 实体类
     * @return 添加后的受影响行数
     */
    void insert(Borrowing in)throws SQLException;

    void deleteByUid(int uid)throws SQLException;

    void deleteByBid(int bid)throws SQLException;
    void deleteByUidAndBid(int uid,int bid)throws SQLException;

    Borrowing[]getAll()throws SQLException;
    Borrowing[]getAllByUid(int uid)throws SQLException;
    Borrowing[]getAllByBid(int bid)throws SQLException;
    Borrowing getOneByUidAndBid(int uid,int bid)throws SQLException;
    Borrowing[] getAllByEndTime(LocalDateTime end_time)throws SQLException;
}
