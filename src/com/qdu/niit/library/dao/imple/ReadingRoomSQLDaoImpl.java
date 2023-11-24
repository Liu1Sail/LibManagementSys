package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.ReadingRoomSQLDao;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.ReadingRoom;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReadingRoomSQLDaoImpl extends BaseSQLDaoImpl implements ReadingRoomSQLDao {
    private static final String CREATE_TABLE_ReadingRoom_SQL = "CREATE TABLE  IF NOT EXISTS Reading_Room(rid INT NOT NULL,uid INT NOT NULL,start_time DATETIME NOT NULL,end_time DATETIME NOT NULL);";
    private static final String INSERT_ReadingRoom_SQL = "INSERT INTO Reading_Room (uid,bid,start_time,end_time) VALUES(?,?,?,?)";
    private static final String DELETE_ReadingRoomByUid_SQL = "DELETE FROM Reading_Room WHERE uid = ?";
    private static final String DELETE_ReadingRoomByBid_SQL = "DELETE FROM Reading_Room WHERE bid = ?";
    private static final String SELECT_ReadingRoom_SQL = "SELECT * FROM Reading_Room";
    private static final String SELECT_ReadingRoomByUid_SQL = "SELECT * FROM Reading_Room Where uid = ?";
    private static final String SELECT_ReadingRoomByBid_SQL = "SELECT * FROM Reading_Room Where bid = ?";
    private static final String SELECT_ReadingRoomThanEndTime_SQL = "SELECT * FROM Reading_Room Where EndTime <= ?";
    private ReadingRoomSQLDaoImpl() throws SQLException {
        executeUpdate(CREATE_TABLE_ReadingRoom_SQL);
    }
    private static ReadingRoomSQLDaoImpl instance;
    /**
     * @return instance of BorrowingManager
     */
    public static ReadingRoomSQLDaoImpl getInstance() throws SQLException {
        if(instance == null)
            instance = new ReadingRoomSQLDaoImpl();
        return instance;
    }
    @Override
    public int insert(ReadingRoom in) throws SQLException {
        executeUpdate(INSERT_ReadingRoom_SQL,in.getBid(),in.getUid(),String.valueOf(in.getStart_time()),String.valueOf(in.getEnd_time()));
        return 0;
    }

    @Override
    public int deleteByUid(int uid) throws SQLException {
        executeUpdate(DELETE_ReadingRoomByUid_SQL,uid);
        return 0;
    }

    @Override
    public int deleteByRid(int bid) throws SQLException {
        executeUpdate(DELETE_ReadingRoomByBid_SQL,bid);
        return 0;
    }

    @Override
    public ArrayList<ReadingRoom> getAll() throws SQLException {
        getMany(SELECT_ReadingRoom_SQL);
        return null;
    }

    @Override
    public ArrayList<ReadingRoom> getOneByUid(int uid) throws SQLException {
        getMany(SELECT_ReadingRoomByUid_SQL,uid);
        return null;
    }

    @Override
    public ArrayList<ReadingRoom> getOneByRid(int bid) throws SQLException {
        executeUpdate(SELECT_ReadingRoomByBid_SQL,bid);
        return null;
    }

    @Override
    public ArrayList<ReadingRoom> getAllByEndTime(LocalDateTime end_time) throws SQLException {
        executeUpdate(SELECT_ReadingRoomByBid_SQL,String.valueOf(end_time));
        return null;
    }
}
