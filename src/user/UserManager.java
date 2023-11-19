package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import utils.SqlConfig;


/**
 * 管理用户，创建，删除，登录
 */
public class UserManager
{
    /**
     * @return instance of UserManager
     */
    public static UserManager getInstance() throws SQLException {
        if(instance == null)
            instance = new UserManager();
        return instance;
    }

    /**
     * @return 本地线上用户
     */
    public User getLocalUser(){
        return localUser;
    }

    /**
     *
     * @param uname 昵称
     * @param password 密码
     */
    public int userSignUp(String uname, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = SqlConfig.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Users (uname, upwd) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1 , uname);
            preparedStatement.setString(2 , password);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * @param uid 要删除uid
     * @throws SQLException
     */
    public void userLogout(int uid) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            connection = SqlConfig.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE uid = ?;");
            preparedStatement.setInt(1 , uid);
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param uid 用户id
     * @param password 用户密码
     * @return 一个User对象 或者 null(如果不成功)
     */
    public User userLogin(int uid , String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = SqlConfig.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT uname , upwd FROM Users WHERE uid = ?;");
            preparedStatement.setInt(1 , uid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String pwd = resultSet.getString("upwd");
                String name = resultSet.getString("uname");
                if(password.equals(pwd)){
                    localUser = new User(uid , name);
                    return localUser;
                }
            }
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------------//
    private static UserManager instance;
    private static User localUser= null;
    private UserManager() throws SQLException {
        /*
          初始化User表 防止表被删除
         */
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        //尝试建立连接并初始化表
        try {
            connection = SqlConfig.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Users(" +
                            "uid INT AUTO_INCREMENT PRIMARY KEY," +
                            "uname varchar(30) NOT NULL," +
                            "upwd varchar(30) NOT NULL" +
                            ")");
            preparedStatement.executeUpdate();
        }
        //抛出链接建立失败异常
        finally {//处理完关闭链接
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
