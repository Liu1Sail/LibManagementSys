package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class SuperUserManagerDaoImpl extends BaseSQLDaoImpl implements UserSQLDao{
    public static SuperUserManagerDaoImpl getInstance()throws SQLException{
        if(instance  == null)
            instance = new SuperUserManagerDaoImpl();
        return instance;
    }
    @Override
    public User getUserByIdAndPassword(int id, String password) throws SQLException {
        assert password != null : "password不能传入空指针";
        Object[] meta = getOne(
                SELECT_SUPERUSERS_BY_ID_AND_PASSWORD_SQL ,
                id ,
                password
        );
        if(meta == null || meta.length<1)return null;
        return new User(id, Cast(meta[0])  ,password);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        assert name != null && password != null  :"name和password不能传入空指针";
        Object[] meta = getOne(
                SELECT_SUPERUSERS_BY_NAME_AND_PASSWORD_SQL ,
                name ,
                password
        );
        if(meta == null || meta.length<1)return null;
        return new User(Cast(meta[0]), name  ,password);
    }

    @Override
    public User[] getAllUsers() throws SQLException {
        List<Object[]> list = getMany(SELECT_SUPERUSERS_ALL_ID_AND_NAME_SQL);
        User[] result = new User[list.size()];
        for(int i = 0; i < list.size() ;++i) {
            Object[] arr = list.get(i);
            if(arr==null||arr.length<2)return null;
            result[i] = new User((int)arr[0] , (String)arr[1],null);
        }
        return result;
    }

    @Override
    public void updateUserAll(User user) throws SQLException {
        assert user != null : "user不能传入空指针";
        executeUpdate(UPDATE_SUPERUSERS_SQL ,
                user.getUName() ,
                user.getUPassword() ,
                user.getUID()
        );
    }

    @Override
    public void updateUserPasswordById(int id, String password) throws SQLException {
        assert password != null : "password不能传入空指针";
        executeUpdate(UPDATE_SUPERUSERS_PASSWORD_SQL , password, id);
    }

    @Override
    public int insert(User user, UserInfo info) throws SQLException {
        return -1;
    }

    @Override
    public int insert(User user) throws SQLException {
        assert user != null : "user不能传入空指针";
        List<Object[]> keys = executeUpdateAndGetGeneratedKeys(
                INSERT_SUPERUSERS_SQL,
                user.getUName(),
                user.getUPassword());
        BigInteger id = Cast(keys.getFirst()[0]);
        if(id!=null)
            return id.intValue();
        return -1;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        executeUpdate(DELETE_SUPERUSERS_SQL , id);
    }

    @Override
    public boolean doesUserExistsByUserName(String userName) throws SQLException {
        assert userName != null : "userName不能传入空指针";
        return  null != getOne(SELECT_SUPERUSERS_COUNT_BY_USERNAME , userName);
    }

    @Override
    public boolean doesUserExistsById(int id) throws SQLException {
        return  null != getOne(SELECT_SUPERUSERS_COUNT_BY_ID , id);
    }


    private SuperUserManagerDaoImpl() throws SQLException {
        executeUpdate(CREATE_TABLE_SUPERUSERS_SQL);
    }
    private static SuperUserManagerDaoImpl instance;
    private static final String CREATE_TABLE_SUPERUSERS_SQL =
            "CREATE TABLE IF NOT EXISTS SUPERUSERS(" +
                    "uId INT AUTO_INCREMENT PRIMARY KEY," +
                    "uName varchar(30) NOT NULL UNIQUE," +
                    "uPwd varchar(30) NOT NULL" +
                    ")";
    /*增*/
    private static final String INSERT_SUPERUSERS_SQL = "INSERT INTO SUPERUSERS (uName, uPwd) VALUES (?, ?)";
    /*删*/
    private static final String DELETE_SUPERUSERS_SQL = "DELETE FROM SUPERUSERS WHERE uId = ?";
    /*查*/
    private static final String SELECT_SUPERUSERS_BY_ID_AND_PASSWORD_SQL = "SELECT uName FROM SUPERUSERS WHERE uId = ? AND uPwd = ?";

    private static final String SELECT_SUPERUSERS_BY_NAME_AND_PASSWORD_SQL= "SELECT uId FROM SUPERUSERS WHERE uName = ? AND uPwd = ?";
    private static final String SELECT_SUPERUSERS_ALL_ID_AND_NAME_SQL="SELECT uId  ,uName FROM SUPERUSERS";
    /*改*/
    private static final String UPDATE_SUPERUSERS_SQL ="""
                    UPDATE SUPERUSERS SET uName = ? , uPwd = ? WHERE uId = ?""";
    private static final String UPDATE_SUPERUSERS_PASSWORD_SQL ="""
                    UPDATE SUPERUSERS SET uPwd = ? WHERE uId = ?""";

    private static final String SELECT_SUPERUSERS_COUNT_BY_ID="SELECT uId  FROM SUPERUSERS WHERE uId = ?";

    private static final String SELECT_SUPERUSERS_COUNT_BY_USERNAME="SELECT uName FROM SUPERUSERS WHERE uName = ?";
}
