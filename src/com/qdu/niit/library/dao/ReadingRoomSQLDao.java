package com.qdu.niit.library.dao;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.ReadingRoom;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReadingRoomSQLDao
{
    int insert(ReadingRoom in)throws SQLException;

    int deleteByUid(int uid)throws SQLException;

    int deleteByRid(int bid)throws SQLException;

    ArrayList<ReadingRoom>getAll()throws SQLException;
    ArrayList<ReadingRoom>getOneByUid(int uid)throws SQLException;
    ArrayList<ReadingRoom>getOneByRid(int bid)throws SQLException;
    ArrayList<ReadingRoom>getAllByEndTime(LocalDateTime end_time)throws SQLException;
}
