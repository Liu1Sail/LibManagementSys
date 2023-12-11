package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ReturningSQLDao {
    void insert(Returning in)throws SQLException;

    Returning[]getAll()throws SQLException;

    Returning[]getAllByUid(int uid)throws SQLException;

    Returning[] getAllByBid(int bid)throws SQLException;

    Returning[] getAllByUidAndBid(int uid,int bid)throws SQLException;

    Returning[] getAllByBackTime(LocalDateTime back_time)throws SQLException;
}
