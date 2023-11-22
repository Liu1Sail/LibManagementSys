package com.qdu.niit.library.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtil {


    /**
     * @param SQL 要执行的SQL语句
     * @param args SQL语句的参数
     */
    public static void executeUpdate(final String SQL , Object... args) throws SQLException {
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(SQL)) {
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            preparedStatement.executeUpdate();
        }
    }


    /**
     * @param SQL 要执行的SQL语句
     * @param args SQL语句的参数
     * @return 结果集
     */
    public static ResultSet executeQuery(final String SQL , Object... args) throws SQLException{
        try(Connection connection = SqlConfig.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            return preparedStatement.executeQuery();
        }
    }
}
