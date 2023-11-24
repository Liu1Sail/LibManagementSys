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
    public int insert(Borrowing in)throws SQLException
    {
        executeUpdate(INSERT_Borrowing_SQL,in.getUid(),in.getBid(),in.getStart_time(),in.getEnd_time());
        return 0;
    }

    @Override
    public int deleteByUid(int uid)throws SQLException {
        executeUpdate(DELETE_BorrowingByUid_SQL,uid);
        return 0;
    }


    @Override
    public int deleteByBid(int bid)throws SQLException {
        executeUpdate(DELETE_BorrowingByBid_SQL,bid);
        return 0;
    }

    @Override
    public int deleteByUidAndBid(int uid, int bid)throws SQLException {
        executeUpdate(DELETE_BorrowingByUidAndBid_SQL,uid,bid);
        return 0;
    }

    @Override
    public ArrayList<Borrowing> getAll()throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_Borrowing_SQL);
        ArrayList<Borrowing>back = new ArrayList<>();
        for(int i = 0;i<receive.size();i++)
        {
            Borrowing bg = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public ArrayList<Borrowing> getAllByUid(int uid)throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_BorrowingByUid_SQL,uid);
        ArrayList<Borrowing>back = new ArrayList<>();
        for(int i = 0;i<receive.size();i++)
        {
            Borrowing bg = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public ArrayList<Borrowing> getAllByBid(int bid)throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_BorrowingByBid_SQL,bid);
        ArrayList<Borrowing>back = new ArrayList<>();
        for(int i = 0;i<receive.size();i++)
        {
            Borrowing bg = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }

    @Override
    public ArrayList<Borrowing> getOneByUidAndBid(int uid,int bid)throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_BorrowingByUidAndBid_SQL,uid,bid);
        ArrayList<Borrowing>back = new ArrayList<>();
        for(int i = 0;i<receive.size();i++)
        {
            Borrowing bg = new Borrowing((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2],(LocalDateTime) receive.get(i)[3]);
        }
        return back;
    }
}
