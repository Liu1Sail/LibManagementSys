package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.UserInfoSQLDao;
import com.qdu.niit.library.entity.UserInfo;

import java.sql.SQLException;

public class UserInfoSQLDaoImpl extends BaseSQLDaoImpl implements UserInfoSQLDao {

    public static UserInfoSQLDaoImpl getInstance() throws SQLException {
        if(null == instance){
            instance = new UserInfoSQLDaoImpl();
        }
        return instance;
    }
    public void insert(UserInfo info)throws SQLException{
        executeUpdate(INSERT_USERINFO_SQL ,
                info.getID(),
                info.getName(),
                JavaDateToSqlDate(info.getBirthday()),
                JavaGenderToSqlGender(info.getGender()),
                info.getPhone(),
                info.getEmail());
    }
    @Override
    public void updateAll(UserInfo info) throws SQLException {
        executeUpdate(UPDATE_USERINFO_SQL ,
                info.getName() ,
                JavaGenderToSqlGender(info.getGender()),
                info.getPhone() ,
                info.getEmail(),
                info.getID() );
    }
    @Override
    public UserInfo getOneById(int id)throws SQLException {
        Object[] obj = getOne(SELECT_USERINFO_SQL , id);
        if(null == obj)return null;
        return new UserInfo(
                Cast(obj[0]),
                Cast(obj[1]) ,
                SqlDateToJavaDate(Cast(obj[2])) ,
                SqlGenderToJavaGender(Cast(obj[3])) ,
                Cast(obj[4]) ,
                Cast(obj[5])
        );
    }
    /*-----------------------------------------------------------------------------------*/
    private UserInfoSQLDaoImpl()throws SQLException
    {
        executeUpdate(CREATE_TABLE_USERINFO_SQL);
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
    private static final String SELECT_USERINFO_SQL = "SELECT*FROM USERINFO WHERE uId = ?;";
    private static UserInfoSQLDaoImpl instance;
}
