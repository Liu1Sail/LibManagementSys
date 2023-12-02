package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.BaseSQLDao;
import com.qdu.niit.library.dao.BorrowingSQLDao;
import com.qdu.niit.library.entity.Borrowing;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BorrowingSQLDaoImpl extends BaseSQLDaoImpl implements BorrowingSQLDao {
    private static final String CREATE_TABLE_Borrowing_SQL = "CREATE TABLE  IF NOT EXISTS Borrowing_Information(uid INT NOT NULL,bid INT NOT NULL,start_time DATETIME NOT NULL,end_time DATETIME NOT NULL);";
    private static final String INSERT_Borrowing_SQL = "INSERT INTO Borrowing_Information (uid,bid,start_time,end_time) VALUES(?,?,?,?)";
    private static final String DELETE_BorrowingByUid_SQL = "DELETE FROM Borrowing_Information WHERE uid = ?";
    private static final String DELETE_BorrowingByBid_SQL = "DELETE FROM Borrowing_Information WHERE bid = ?";
    private static final String DELETE_BorrowingByUidAndBid_SQL = "DELETE FROM Borrowing_Information WHERE uid = ? and bid = ?";
    private static final String SELECT_Borrowing_SQL = "SELECT * FROM Borrowing_Information";
    private static final String SELECT_BorrowingByUid_SQL = "SELECT * FROM Borrowing_Information Where uid = ?";
    private static final String SELECT_BorrowingByBid_SQL = "SELECT * FROM Borrowing_Information Where bid = ?";
    private static final String SELECT_BorrowingByUidAndBid_SQL = "SELECT * FROM Borrowing_Information Where uid = ? and bid = ?";
    private static final String SELECT_BorrowingByEndTime_SQL = "SELECT * FROM borrowing_information Where TO_DAYS(?)<TO_DAYS(NOW());";
    /**
     * 生成表防止表不存在
     */
    private BorrowingSQLDaoImpl() throws SQLException {
        executeUpdate(CREATE_TABLE_Borrowing_SQL);
    }
    private static BorrowingSQLDaoImpl instance;
    /**
     * @return instance of BorrowingManager
     */
    public static BorrowingSQLDaoImpl getInstance() throws SQLException {
        if(instance == null)
            instance = new BorrowingSQLDaoImpl();
        return instance;
    }
    @Override
    public void insert(Borrowing in)throws SQLException
    {
        executeUpdate(INSERT_Borrowing_SQL,in.getUid(),in.getBid(),String.valueOf(in.getStart_time()),String.valueOf(in.getEnd_time()));
    }

    @Override
    public void deleteByUid(int uid)throws SQLException {
        executeUpdate(DELETE_BorrowingByUid_SQL,uid);
    }


    @Override
    public void deleteByBid(int bid)throws SQLException {
        executeUpdate(DELETE_BorrowingByBid_SQL,bid);
    }

    @Override
    public void deleteByUidAndBid(int uid, int bid)throws SQLException {
        executeUpdate(DELETE_BorrowingByUidAndBid_SQL,uid,bid);
    }

    @Override
    public Borrowing[] getAll()throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_Borrowing_SQL);
        Borrowing[] back = new Borrowing[receive.size()];
        if(receive.isEmpty())
        {
            return null;
        }
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public Borrowing[] getAllByUid(int uid)throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_BorrowingByUid_SQL,uid);
        Borrowing[]back = new Borrowing[receive.size()];
        if(receive.isEmpty())
        {
            return null;
        }
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public Borrowing getOneByBid(int bid)throws SQLException {
        Object[] receive = getOne(SELECT_BorrowingByBid_SQL,bid);
        if(receive == null)
        {
            return null;
        }
        Borrowing back = new Borrowing((int)receive[0],(int)receive[1],(LocalDateTime) receive[2],(LocalDateTime) receive[3]);
        return back;
    }

    @Override
    public Borrowing getOneByUidAndBid(int uid,int bid)throws SQLException {
        Object[] receive = getOne(SELECT_BorrowingByUidAndBid_SQL,uid,bid);
        if(receive == null)
        {
            return null;
        }
        Borrowing back = new Borrowing((int)receive[0],(int)receive[1],(LocalDateTime) receive[2],(LocalDateTime) receive[3]);
        return back;
    }

    @Override
    public Borrowing[] getAllByEndTime(LocalDateTime end_time)throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_BorrowingByEndTime_SQL,end_time);
        Borrowing[]back = new Borrowing[receive.size()];
        if(receive.isEmpty())
        {
            return null;
        }
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }
}
