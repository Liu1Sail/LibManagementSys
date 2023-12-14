package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.imple.BorrowingSQLDaoImpl;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.service.BorrowingService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class BorrowingServiceImpl implements BorrowingService {
    @Override
    public boolean ifHaveByUid(int uid)throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            Borrowing[]receive = use.getAllByUid(uid);
            if(receive == null)
            {
                return false;
            }
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean ifHaveByBid(int bid)throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            Borrowing receive = use.getOneByBid(bid);
            if(receive == null)
            {
                return false;
            }
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }

    @Override
    public Borrowing[] findAll() throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            return use.getAll();
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }

    @Override
    public Borrowing[] findAllByEndTime(LocalDateTime end_time)throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            return use.getAllByEndTime(end_time);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Borrowing[] findAllByUid(int uid)throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            return  use.getAllByUid(uid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Borrowing findOneByBid(int bid)throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            return use.getOneByBid(bid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean insert(Borrowing in)throws SQLException {
        try {
            if(ifHaveByBid(in.getBid())==true)
            {
                return false;
            }
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            use.insert(in);
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean deleteByUid(int uid)throws SQLException {
        try {
            if(ifHaveByUid(uid) == false)
            {
                return false;
            }
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            use.deleteByUid(uid);
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean deleteByBid(int bid)throws SQLException {
        try {
            if(ifHaveByBid(bid) == false)
            {
                return false;
            }
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            use.deleteByBid(bid);
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public Borrowing[] findAllSmallEndTime(LocalDateTime end_time) throws SQLException {
        try {
            BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
            return use.getAllByEndTime(end_time);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }
}
