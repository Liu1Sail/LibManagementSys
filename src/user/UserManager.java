package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
     * @param uname 昵称
     * @param password 密码
     * @return uid 失败则返回-1
     */
    public int userSignUp(String uname, String password) throws SQLException {
        try(Connection connection=SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL,PreparedStatement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1 , uname);
            preparedStatement.setString(2 , password);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if(resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * @param uid 要删除uid
     */
    public void userLogout(int uid) throws SQLException {
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)
        ){
            preparedStatement.setInt(1 , uid);
            preparedStatement.executeUpdate();
        }
    }

    /**
     *
     * @param uid 用户id
     * @param password 用户密码
     * @return 一个User对象 或者 null(如果不成功)
     */
    public User userLogin(int uid , String password)throws SQLException{
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL))
        {
            preparedStatement.setInt(1 , uid);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next())
                {
                    String pwd = resultSet.getString("upwd");
                    String name = resultSet.getString("uname");
                    if(password.equals(pwd)) {
                        localUser = new User(uid , name);
                        return localUser;
                    }
                }
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------------//
    private UserManager() throws SQLException {/*初始化User表 防止表被删除*/
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_USERS_SQL)) {
            preparedStatement.executeUpdate();
        }
    }

    private static UserManager instance;
    private static User localUser= null;
    private static final String CREATE_TABLE_USERS_SQL =
            "CREATE TABLE IF NOT EXISTS Users(" +
                    "uid INT AUTO_INCREMENT PRIMARY KEY," +
                    "uname varchar(30) NOT NULL," +
                    "upwd varchar(30) NOT NULL" +
                    ")";
    private static final String INSERT_USERS_SQL =
            "INSERT INTO Users (uname, upwd) VALUES (?, ?)";
    private static final String DELETE_USERS_SQL =
            "DELETE FROM Users WHERE uid = ?";
    private static final String SELECT_USERS_SQL =
            "SELECT uname, upwd FROM Users WHERE uid = ?";
}
