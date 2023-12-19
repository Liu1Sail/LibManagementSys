package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.ReturningSQLDao;
import com.qdu.niit.library.entity.Borrowing;
import com.qdu.niit.library.entity.Returning;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReturningSQLDaoImpl extends BaseSQLDaoImpl implements ReturningSQLDao {
    private static final String CREATE_TABLE_Returning_SQL = "CREATE TABLE  IF NOT EXISTS Returning_Information(uid INT NOT NULL,bid INT NOT NULL,back_time DATETIME NOT NULL);";
    private static final String INSERT_Returning_SQL = "INSERT INTO Returning_Information (uid,bid,back_time) VALUES(?,?,?)";
    private static final String SELECT_Returning_SQL = "SELECT * FROM Returning_Information";
    private static final String SELECT_ReturningByUid_SQL = "SELECT * FROM Returning_Information Where uid = ?";
    private static final String SELECT_ReturningByBid_SQL = "SELECT * FROM Returning_Information Where bid = ?";
    private static final String SELECT_ReturningByUidAndBid_SQL = "SELECT * FROM Returning_Information Where uid = ? and bid = ?";
    private static final String SELECT_ReturningByEndTime_SQL = "SELECT * FROM Returning_Information Where ?<end_time;";
    private static final String SELECT_DeleteByTime_SQL = "DELETE FROM  Returning_Information Where ?=end_time;";
    private ReturningSQLDaoImpl() throws SQLException {
        executeUpdate(CREATE_TABLE_Returning_SQL);
    }
    private static ReturningSQLDaoImpl instance;
    /**
     * @return instance of BorrowingManager
     */
    public static ReturningSQLDaoImpl getInstance() throws SQLException {
        if(instance == null)
            instance = new ReturningSQLDaoImpl();
        return instance;
    }
    @Override
    public void insert(Returning in) throws SQLException {
        executeUpdate(INSERT_Returning_SQL,in);
    }

    @Override
    public void deleteByTime(LocalDateTime in) throws SQLException {
        executeUpdate(SELECT_DeleteByTime_SQL,in);
    }

    @Override
    public Returning[] getAll() throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_Returning_SQL);
        if(receive.isEmpty())
        {
            return null;
        }
        Returning[]back = new Returning[receive.size()];
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Returning((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2]);
        }
        return back;
    }

    @Override
    public Returning[] getAllByUid(int uid) throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_ReturningByUid_SQL,uid);
        Returning[]back = new Returning[receive.size()];
        if(receive.isEmpty())
        {
            return null;
        }
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Returning((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2]);
        }
        return back;
    }

    @Override
    public Returning[] getAllByBid(int bid) throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_ReturningByBid_SQL,bid);
        if(receive.isEmpty())
        {
            return null;
        }
        Returning[]back = new Returning[receive.size()];
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Returning((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2]);
        }
        return back;
    }

    @Override
    public Returning[] getAllByUidAndBid(int uid, int bid) throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_ReturningByUidAndBid_SQL,uid,bid);
        if(receive.isEmpty())
        {
            return null;
        }
        Returning[]back = new Returning[receive.size()];
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Returning((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2]);
        }
        return back;
    }

    @Override
    public Returning[] getAllByBackTime(LocalDateTime back_time) throws SQLException {
        ArrayList<Object[]>receive = getMany(SELECT_ReturningByEndTime_SQL,back_time);
        if(receive.isEmpty())
        {
            return null;
        }
        Returning[]back = new Returning[receive.size()];
        for(int i = 0;i<receive.size();i++)
        {
            back[i] = new Returning((int)receive.get(i)[0],(int)receive.get(i)[1],(LocalDateTime) receive.get(i)[2]);
        }
        return back;
    }
}
