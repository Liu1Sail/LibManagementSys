package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.ReadingRoom;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ReadingRoomService {
    boolean ifHaveByUid(int uid)throws SQLException;
    boolean ifHaveByBid(int bid)throws SQLException;
    ReadingRoom[] findAll()throws SQLException;
    ReadingRoom[] findAllByEndTime(LocalDateTime back_time)throws SQLException;
    ReadingRoom[] findAllBySmallEndTime(LocalDateTime end_time)throws SQLException;
    ReadingRoom findOneByUid(int uid)throws SQLException;
    ReadingRoom findOneByBid(int bid)throws SQLException;
    boolean  insert(ReadingRoom in)throws SQLException;
    boolean  deleteByUid(int uid)throws SQLException;
    boolean  deleteByBid(int bid)throws SQLException;
}
