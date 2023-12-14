package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.dao.imple.ReadingRoomSQLDaoImpl;
import com.qdu.niit.library.entity.ReadingRoom;
import com.qdu.niit.library.service.ReadingRoomService;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReadingRoomServiceImpl implements ReadingRoomService {
    @Override
    public boolean ifHaveByUid(int uid) throws SQLException {
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            if(use.getOneByUid(uid) == null)
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
    public boolean ifHaveByBid(int rid) throws SQLException {
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            if(use.getOneByBid(rid) == null)
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
    public ReadingRoom[] findAll() throws SQLException{
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            return use.getAll();
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public ReadingRoom[] findAllByEndTime(LocalDateTime back_time) throws SQLException {
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            return use.getAllByEndTime(back_time);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public ReadingRoom[] findAllBySmallEndTime(LocalDateTime end_time) throws SQLException {
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            return use.getAllBySmallEndTime(end_time);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public ReadingRoom findOneByUid(int uid) throws SQLException {
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            return use.getOneByUid(uid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public ReadingRoom findOneByBid(int bid) throws SQLException {
        try {
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            return use.getOneByBid(bid);
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean insert(ReadingRoom in) throws SQLException {
        try {
            if(ifHaveByUid(in.getUid()) == true)
            {
                return false;
            }
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            use.insert(in);
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean deleteByUid(int uid) throws SQLException {
        try {
            if(ifHaveByUid(uid) == false)
            {
                return false;
            }
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            use.deleteByUid(uid);
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }

    @Override
    public boolean deleteByBid(int bid) throws SQLException {
        try {
            if(ifHaveByBid(bid) == false)
            {
                return false;
            }
            ReadingRoomSQLDaoImpl use = ReadingRoomSQLDaoImpl.getInstance();
            use.deleteByBid(bid);
            return true;
        }catch (SQLException e)
        {
            throw new SQLException(e);
        }

    }
}
