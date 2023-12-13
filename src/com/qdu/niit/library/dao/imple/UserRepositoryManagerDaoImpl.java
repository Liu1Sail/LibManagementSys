package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.UserInfoSQLDao;
import com.qdu.niit.library.dao.UserSQLDao;
import com.qdu.niit.library.entity.User;
import com.qdu.niit.library.entity.UserInfo;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
/**
 * 管理用户，创建，删除，登录
 */
public class UserRepositoryManagerDaoImpl extends TransactionalSQLDaoImpl implements UserSQLDao , UserInfoSQLDao
{
    //===========================||   User    ||==============================//
    @Override
    public User getUserByIdAndPassword(int uid, String password) throws SQLException {
        Object[] meta = getOne(
                SELECT_USERS_BY_ID_AND_PASSWORD_SQL ,
                uid ,
                password
        );
        if(meta == null || meta.length<1)return null;
        return new User(uid, Cast(meta[0])  ,password);
    }
    @Override
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        Object[] meta = getOne(
                SELECT_USER_BY_NAME_AND_PASSWORD_SQL ,
                name ,
                password
        );
        if(meta == null || meta.length<1)return null;
        return new User(Cast(meta[0]), name  ,password);
    }
    @Override
    public User[] getAllUsers()throws SQLException {
        List<Object[]> list = getMany(SELECT_USERS_ALL_ID_AND_NAME_SQL);
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
        if(null == user)return;
        executeUpdate(UPDATE_USERS_SQL ,
                user.getUName() ,
                user.getUPassword() ,
                user.getUID()
        );
    }
    @Override
    public void updateUserPasswordById(int id, String password) throws SQLException {
        executeUpdate(UPDATE_USERS_PASSWORD_SQL , password, id);
    }
    @Override
    public int insert(User user,UserInfo info) throws SQLException {
        try {
            beginTransaction();
                List<Object[]> keys = executeTransactionUpdateAndGetKeys(
                        INSERT_USERS_SQL,
                        user.getUName(),
                        user.getUPassword());
                BigInteger id = Cast(keys.getFirst()[0]);
                executeTransactionUpdate(INSERT_USERINFO_SQL ,
                        id.intValue(),
                        info.getName(),
                        JavaDateToSqlDate(info.getBirthday()),
                        JavaGenderToSqlGender(info.getGender()),
                        info.getPhone(),
                        info.getEmail());
            commit();
                return id.intValue();
        }catch (SQLException e){rollback();}
        return -1;
    }
    @Override
    public int insert(User user) throws SQLException {
        try {
            beginTransaction();
                List<Object[]> keys = executeTransactionUpdateAndGetKeys(
                        INSERT_USERS_SQL,
                        user.getUName(),
                        user.getUPassword());
                BigInteger id = Cast(keys.getFirst()[0]);
                executeTransactionUpdate(INSERT_USERINFO_SQL ,
                       id, null, null, null, null, null);
            commit();
                return id.intValue();
        }catch (SQLException e){rollback();}
        return -1;
    }
    @Override
    public void deleteById(int id) throws SQLException {
        executeUpdate(DELETE_USERS_SQL , id);
    }
    /**
     * @return instance of UserManager
     */
    public static UserRepositoryManagerDaoImpl getInstance() throws SQLException {
        if(instance == null)
            instance = new UserRepositoryManagerDaoImpl();
        return instance;
    }
    @Override
    public boolean doesUserExistsByUserName(String userName)throws  SQLException
    {
        Integer count = this.<Integer>Cast(
                getOne(SELECT_USERS_COUNT_BY_USERNAME , userName)
        );
        return  null != count && count > 0;
    }
    @Override
    public boolean doesUserExistsById(int id)throws SQLException
    {
        Integer count = this.<Integer>Cast(
                getOne(SELECT_USERS_COUNT_BY_ID , id)
        );
        return  null != count && count > 0;
    }

    //=========================||   UserInfo    ||===========================//
    @Override
    public void updateUserInfoAll(UserInfo info) throws SQLException {
        executeUpdate(UPDATE_USERINFO_SQL ,
                info.getName() ,
                JavaGenderToSqlGender(info.getGender()),
                info.getPhone() ,
                info.getEmail(),
                info.getID() );
    }
    @Override
    public UserInfo getUserInfoById(int id)throws SQLException {
        return assembleUserInfo(
                getOne(SELECT_USERINFO_SQL , id)
        );
    }

    public UserInfo getUserInfoByUserName(String userName) throws SQLException {
       return assembleUserInfo(
               getOne(SELECT_USERINFO_BY_USER_NAME_SQL , userName)
       );
    }
    /*====================================================================*/
    /*======================||     PRIVATE        ||==========================*/
    /*====================================================================*/
    private UserRepositoryManagerDaoImpl() throws SQLException {/*初始化User表 防止表被删除*/
        executeUpdate(CREATE_TABLE_USERS_SQL);
        executeUpdate(CREATE_TABLE_USERINFO_SQL);
    }
    /*======================||        USER        ||===========================*/
    private static UserRepositoryManagerDaoImpl instance;
    private static final String CREATE_TABLE_USERS_SQL =
            "CREATE TABLE IF NOT EXISTS USERS(" +
                    "uId INT AUTO_INCREMENT PRIMARY KEY," +
                    "uName varchar(30) NOT NULL UNIQUE," +
                    "uPwd varchar(30) NOT NULL" +
                    ")";
    /*增*/
    private static final String INSERT_USERS_SQL = "INSERT INTO USERS (uName, uPwd) VALUES (?, ?)";
    /*删*/
    private static final String DELETE_USERS_SQL = "DELETE FROM USERS WHERE uId = ?";
    /*查*/
    private static final String SELECT_USERS_BY_ID_AND_PASSWORD_SQL = "SELECT uName FROM USERS WHERE uId = ?AND uPwd = ?";

    private static final String SELECT_USER_BY_NAME_AND_PASSWORD_SQL= "SELECT uId FROM USERS WHERE uName = ?AND uPwd = ?";
    private static final String SELECT_USERS_ALL_ID_AND_NAME_SQL="SELECT uId,uName FROM USERS";
    /*改*/
    private static final String UPDATE_USERS_SQL ="""
                    UPDATE USERS SET uName = ?,uPwd = ? WHERE uId = ?""";
    private static final String UPDATE_USERS_PASSWORD_SQL ="""
                    UPDATE USERS SET uPwd = ? WHERE uId = ?""";

    private static final String SELECT_USERS_COUNT_BY_ID="SELECT COUNT(uId) WHERE uId = ?";

    private static final String SELECT_USERS_COUNT_BY_USERNAME="SELECT COUNT(uName) WHERE uName = ?";

    /*======================||    USERINFO    ||===========================*/

    /**
     * 组装UserInfo
     * @param resultSet 数据库元组 ,  可以为null
     * @return UserInfo对象  ， 如果元组为null则返回null
     */
    private UserInfo assembleUserInfo(Object[] resultSet)
    {
        if(resultSet ==null || resultSet.length < 6)return null;
        return new UserInfo(
                Cast(resultSet[0]),
                Cast(resultSet[1]) ,
                SqlDateToJavaDate(Cast(resultSet[2])) ,
                SqlGenderToJavaGender(Cast(resultSet[3])) ,
                Cast(resultSet[4]) ,
                Cast(resultSet[5])
        );
    }
    private UserInfo.Gender SqlGenderToJavaGender(String gender)
    {
        if(gender == null)return null;
        return switch (gender) {
            case "M"-> UserInfo.Gender.MALE;
            case "F" -> UserInfo.Gender.FEMALE;
            default -> UserInfo.Gender.OTHER;
        };
    }

    private java.util.Date SqlDateToJavaDate(java.sql.Date date)
    {
        if(null == date)return null;
        return new java.util.Date( date.getTime() );
    }

    private String JavaGenderToSqlGender(UserInfo.Gender gender)
    {
        if(gender == null)return null;
        return switch (gender){
            case UserInfo.Gender.MALE -> "M";
            case UserInfo.Gender.FEMALE -> "F";
            default -> "O";
        };
    }
    private java.sql.Date JavaDateToSqlDate(java.util.Date date)
    {
        if(null == date)return null;
        return new java.sql.Date(date.getTime());
    }
    private static final String CREATE_TABLE_USERINFO_SQL= """
            CREATE TABLE IF NOT EXISTS USERINFO(
                uId INT PRIMARY KEY,
                uName varchar(20),
                uBirthday Date,
                uGender char(1),
                uPhone char(11),
                uEmail varchar(30),
                FOREIGN KEY (uId) REFERENCES USERS(uId) ON UPDATE NO ACTION ON DELETE CASCADE
            );
            """;
    private static final String INSERT_USERINFO_SQL= "INSERT INTO USERINFO (uId,uName,uBirthday,uGender, uPhone , uEmail) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USERINFO_SQL="UPDATE USERINFO SET uName = ?,uBirthday = ?,uGender = ?,uPhone = ?,uEmail = ?where uId = ?;";
    private static final String SELECT_USERINFO_SQL = "SELECT * FROM USERINFO WHERE uId = ?;";
    private static final String SELECT_USERINFO_BY_USER_NAME_SQL= "SELECT USERINFO.* FROM USERINFO , USER WHERE USER.uId = ? AND USER.uId = USERINFO.uId";
}
