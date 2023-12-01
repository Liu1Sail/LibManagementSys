package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;
import com.qdu.niit.library.service.ReturningService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReturningServiceImpl implements ReturningService {
    @Override
    public boolean ifHaveByUid(int uid) throws SQLException {
        return false;
    }

    @Override
    public boolean ifHaveByBid(int bid) throws SQLException {
        return false;
    }

    @Override
    public Returning[] findAllByEndTime(LocalDateTime end_time) throws SQLException {
        return new Returning[0];
    }

    @Override
    public Returning[] findAllByUid(int uid) throws SQLException {
        return new Returning[0];
    }

    @Override
    public Returning findOneByBid(int bid) throws SQLException {
        return null;
    }

    @Override
    public boolean insert(Returning in) throws SQLException {
        return false;
    }
}
