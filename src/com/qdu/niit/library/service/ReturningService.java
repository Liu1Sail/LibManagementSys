package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ReturningService {
    boolean ifHaveByUid(int uid)throws SQLException;
    boolean ifHaveByBid(int bid)throws SQLException;
    Returning[] findAllByEndTime(LocalDateTime end_time)throws SQLException;
    Returning[] findAllByUid(int uid)throws SQLException;
    Returning findOneByBid(int bid)throws SQLException;
    boolean  insert(Returning in)throws SQLException;
}
