package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.ReadingRoomSQLDao;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.ReadingRoom;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReadingRoomSQLDaoImpl extends BaseSQLDaoImpl implements ReadingRoomSQLDao {
    private static final String CREATE_TABLE_ReadingRoom_SQL = "CREATE TABLE  IF NOT EXISTS Reading_Room(uid INT NOT NULL,bid INT NOT NULL,start_time DATETIME NOT NULL,end_time DATETIME NOT NULL);";
    private static final String INSERT_ReadingRoom_SQL = "INSERT INTO Reading_Room (uid,bid,start_time,end_time) VALUES(?,?,?,?)";
    private static final String DELETE_ReadingRoomByUid_SQL = "DELETE FROM Reading_Room WHERE uid = ?";
    private static final String DELETE_ReadingRoomByBid_SQL = "DELETE FROM Reading_Room WHERE bid = ?";
    private static final String SELECT_ReadingRoom_SQL = "SELECT * FROM Reading_Room";
    private static final String SELECT_ReadingRoomByUid_SQL = "SELECT * FROM Reading_Room Where uid = ?";
    private static final String SELECT_ReadingRoomByBid_SQL = "SELECT * FROM Reading_Room Where bid = ?";
    private static final String SELECT_ReadingRoomByEndTime_SQL = "SELECT * FROM Reading_Room Where TO_DAYS(?)<TO_DAYS(end_time);";
    private static final String SELECT_ReadingRoomBySmallEndTime_SQL = "SELECT * FROM Reading_Room Where TO_DAYS(end_time)<TO_DAYS(?);";
    private ReadingRoomSQLDaoImpl()throws SQLException
    {
        executeUpdate(CREATE_TABLE_ReadingRoom_SQL);
    }
    private static ReadingRoomSQLDaoImpl instance;
    public static ReadingRoomSQLDaoImpl getInstance() throws SQLException {
        if(instance == null)
            instance = new ReadingRoomSQLDaoImpl();
        return instance;
    }
    @Override
    public void insert(ReadingRoom in) throws SQLException {
        executeUpdate(INSERT_ReadingRoom_SQL,in.getUid(),in.getBid(),in.getEnd_time(),in.getEnd_time());
    }

    @Override
    public void deleteByUid(int uid) throws SQLException {
        executeUpdate(DELETE_ReadingRoomByUid_SQL,uid);
    }

    @Override
    public void deleteByBid(int bid) throws SQLException {
        executeUpdate(DELETE_ReadingRoomByBid_SQL,bid);
    }

    @Override
    public ReadingRoom[] getAll() throws SQLException {
        ArrayList<Object[]> receive = getMany(SELECT_ReadingRoom_SQL);
        if(receive.isEmpty())
        {
            return null;
        }
        ReadingRoom[] back = new ReadingRoom[receive.size()];
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new ReadingRoom((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public ReadingRoom getOneByUid(int uid) throws SQLException {
        Object[] receive = getOne(SELECT_ReadingRoomByUid_SQL,uid);
        if(receive == null)
        {
            return null;
        }
        ReadingRoom back = new ReadingRoom((int)receive[0],(int)receive[1],(LocalDateTime) receive[2],(LocalDateTime) receive[3]);
        return back;
    }

    @Override
    public ReadingRoom getOneByBid(int bid) throws SQLException {
        Object[] receive = getOne(SELECT_ReadingRoomByBid_SQL,bid);
        if(receive == null)
        {
            return null;
        }
        ReadingRoom back = new ReadingRoom((int)receive[0],(int)receive[1],(LocalDateTime) receive[2],(LocalDateTime) receive[3]);
        return back;
    }

    @Override
    public ReadingRoom[] getAllByEndTime(LocalDateTime end_time) throws SQLException {
        ArrayList<Object[]> receive = getMany(SELECT_ReadingRoomByEndTime_SQL,end_time);
        ReadingRoom[] back = new ReadingRoom[receive.size()];
        if(receive.isEmpty())
        {
            return null;
        }
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new ReadingRoom((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public ReadingRoom[] getAllBySmallEndTime(LocalDateTime end_time) throws SQLException {
        ArrayList<Object[]> receive = getMany(SELECT_ReadingRoomBySmallEndTime_SQL);
        ReadingRoom[] back = new ReadingRoom[receive.size()];
        if(receive.isEmpty())
        {
            return null;
        }
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new ReadingRoom((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }
}
