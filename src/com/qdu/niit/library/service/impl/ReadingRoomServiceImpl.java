package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.imple.ReadingRoomSQLDaoImpl;
import com.qdu.niit.library.entity.ReadingRoom;
import com.qdu.niit.library.service.ReadingRoomService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReadingRoomServiceImpl implements ReadingRoomService {
    @Override
    public boolean ifHaveByUid(int uid) throws SQLException {
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        if(use.getOneByUid(uid) == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean ifHaveByBid(int rid) throws SQLException {
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        if(use.getOneByBid(rid) == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public ReadingRoom[] findAll() throws SQLException{
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        return use.getAll();
    }

    @Override
    public ReadingRoom[] findAllByEndTime(LocalDateTime back_time) throws SQLException {
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        return use.getAllByEndTime(back_time);
    }

    @Override
    public ReadingRoom[] findAllBySmallEndTime(LocalDateTime end_time) throws SQLException {
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        return use.getAllBySmallEndTime(end_time);
    }

    @Override
    public ReadingRoom findOneByUid(int uid) throws SQLException {
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        return use.getOneByUid(uid);
    }

    @Override
    public ReadingRoom findOneByBid(int bid) throws SQLException {
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        return use.getOneByBid(bid);
    }

    @Override
    public boolean insert(ReadingRoom in) throws SQLException {
        if(ifHaveByUid(in.getUid()) == true)
        {
            return false;
        }
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        use.insert(in);
        return true;
    }

    @Override
    public boolean deleteByUid(int uid) throws SQLException {
        if(ifHaveByUid(uid) == false)
        {
            return false;
        }
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        use.deleteByUid(uid);
        return true;
    }

    @Override
    public boolean deleteByBid(int bid) throws SQLException {
        if(ifHaveByBid(bid) == false)
        {
            return false;
        }
        ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
        use.deleteByBid(bid);
        return true;
    }
}
