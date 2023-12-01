package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.Borrowing;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface BorrowingService {
    boolean ifHaveByUid(int uid)throws SQLException;
    boolean ifHaveByBid(int bid)throws SQLException;
    Borrowing[] findAll()throws SQLException;
    Borrowing[] findAllByEndTime(LocalDateTime end_time)throws SQLException;
    Borrowing[] findAllByUid(int uid)throws SQLException;
    Borrowing findOneByBid(int bid)throws SQLException;
    boolean  insert(Borrowing in)throws SQLException;
    boolean  deleteByUid(int uid)throws SQLException;
    boolean  deleteByBid(int bid)throws SQLException;
}
