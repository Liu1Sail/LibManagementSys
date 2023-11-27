package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.ReadingRoom;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReadingRoomSQLDao
{
    void insert(ReadingRoom in)throws SQLException;

    void deleteByUid(int uid)throws SQLException;

    void deleteByRid(int bid)throws SQLException;

    ReadingRoom[]getAll()throws SQLException;
    ReadingRoom getOneByUid(int uid)throws SQLException;
    ReadingRoom getOneByRid(int bid)throws SQLException;
    ReadingRoom[]getAllByEndTime(LocalDateTime end_time)throws SQLException;
}
