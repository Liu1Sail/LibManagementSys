package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.ReadingRoomSQLDao;
import com.qdu.niit.library.dao.ReturningSQLDao;
import com.qdu.niit.library.dao.imple.ReadingRoomSQLDaoImpl;
import com.qdu.niit.library.dao.imple.ReturningSQLDaoImpl;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;
import com.qdu.niit.library.service.ReturningService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReturningServiceImpl implements ReturningService {
    @Override
    public boolean ifHaveByUid(int uid) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            if(use.getAllByUid(uid)!=null)
            {
                return true;
            }
            return false;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean ifHaveByBid(int bid) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            if(use.getAllByBid(bid)!=null)
            {
                return true;
            }
            return false;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Returning[] findAllByEndTime(LocalDateTime end_time) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            return use.getAllByBackTime(end_time);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Returning[] findAllByUid(int uid) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            return use.getAllByUid(uid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Returning[] findAllByBid(int bid) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            return use.getAllByBid(bid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public void insert(Returning in) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            use.insert(in);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Returning[] findAllByUidAndBid(int uid, int bid) throws SQLException {
        try {
            ReturningSQLDaoImpl use = new ReturningSQLDaoImpl();
            return use.getAllByUidAndBid(uid,bid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }
}
