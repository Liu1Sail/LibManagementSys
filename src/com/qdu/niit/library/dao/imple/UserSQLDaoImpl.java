package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.entity.User;
import java.sql.SQLException;
import java.util.List;


/**
 * 管理用户，创建，删除，登录
 */
public class UserSQLDaoImpl extends BaseSQLDaoImpl implements UserSQLDao
{

    @Override
    public User getOneById(int uid) throws SQLException {
        Object[] meta = getOne(SELECT_USERS_SQL , uid);
        if(meta.length<2)return null;
        return new User(uid,(String)meta[0] , (String) meta[1]);
    }

    @Override
    public void updateAll(User user) throws SQLException {
        if(null == user)return;
        executeUpdate(UPDATE_USERS_SQL , user.getUName() , user.getUPassword() , user.getUID());
    }

    @Override
    public void updatePasswordById(int id,String password) throws SQLException {
        executeUpdate(UPDATE_USERS_PASSWORD_SQL , password, id);
    }

    @Override
    public int insert(User user) throws SQLException {
        List<Object[]> keys = executeUpdateAndGetGeneratedKeys(
                INSERT_USERS_SQL,
                user.getUName(),
                user.getUPassword());

        if (keys != null && !keys.isEmpty() && keys.get(0) != null && keys.get(0).length > 0) {
            Object firstKey = keys.get(0)[0];

            if (firstKey instanceof java.math.BigInteger) {
                return ((java.math.BigInteger) firstKey).intValue();
            } else {
                // 处理类型不匹配的情况，抛出异常或者做其他适当的处理
                throw new SQLException("Unexpected type for generated key");
            }
        } else {
            // 处理未生成主键的情况，抛出异常或者做其他适当的处理
            throw new SQLException("No generated key found");
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        executeUpdate(DELETE_USERS_SQL , id);
    }

    /**
     * @return instance of UserManager
     */
    public static UserSQLDaoImpl getInstance() throws SQLException {
        if(instance == null)
            instance = new UserSQLDaoImpl();
        return instance;
    }

    //-------------------------------------------------------------------------------//
    private UserSQLDaoImpl() throws SQLException {/*初始化User表 防止表被删除*/
        executeUpdate(CREATE_TABLE_USERS_SQL);
    }

    private static UserSQLDaoImpl instance;
    private static final String CREATE_TABLE_USERS_SQL =
            "CREATE TABLE IF NOT EXISTS Users(" +
                    "uId INT AUTO_INCREMENT PRIMARY KEY," +
                    "uName varchar(30) NOT NULL UNIQUE," +
                    "uPwd varchar(30) NOT NULL" +
                    ")";
    private static final String INSERT_USERS_SQL =
            "INSERT INTO USERS (uName, uPwd) VALUES (?, ?)";
    private static final String DELETE_USERS_SQL =
            "DELETE FROM USERS WHERE uId = ?";
    private static final String SELECT_USERS_SQL =
            "SELECT uName, uPwd FROM USERS WHERE uId = ?";

    private static final String UPDATE_USERS_SQL ="""
                    UPDATE USERS SET uName = ?,uPwd = ? WHERE uId = ?""";
    private static final String UPDATE_USERS_PASSWORD_SQL ="""
                    UPDATE USERS SET uPwd = ? WHERE uId = ?""";
}
