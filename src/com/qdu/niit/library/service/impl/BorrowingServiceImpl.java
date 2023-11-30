package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.imple.BorrowingSQLDaoImpl;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.service.BorrowingService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class BorrowingServiceImpl implements BorrowingService {
    @Override
    public boolean ifHaveByUid(int uid)throws SQLException {
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        Borrowing[]receive = use.getAllByUid(uid);
        if(receive == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean ifHaveByBid(int bid)throws SQLException {
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        Borrowing receive = use.getOneByBid(bid);
        if(receive == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public Borrowing[] findAll() throws SQLException {
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        return use.getAll();
    }

    @Override
    public Borrowing[] findAllByEndTime(LocalDateTime end_time)throws SQLException {
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        return use.getAllByEndTime(end_time);
    }

    @Override
    public Borrowing[] findAllByUid(int uid)throws SQLException {
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        return  use.getAllByUid(uid);
    }

    @Override
    public Borrowing findOneByBid(int bid)throws SQLException {
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        return use.getOneByBid(bid);
    }

    @Override
    public boolean insert(Borrowing in)throws SQLException {
        if(ifHaveByBid(in.getBid())==true)
        {
            return false;
        }
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        use.insert(in);
        return true;
    }

    @Override
    public boolean deleteByUid(int uid)throws SQLException {
        if(ifHaveByUid(uid) == false)
        {
            return false;
        }
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        use.deleteByUid(uid);
        return true;
    }

    @Override
    public boolean deleteByBid(int bid)throws SQLException {
        if(ifHaveByBid(bid) == false)
        {
            return false;
        }
        BorrowingSQLDaoImpl use = BorrowingSQLDaoImpl.getInstance();
        use.deleteByBid(bid);
        return true;
    }
}
