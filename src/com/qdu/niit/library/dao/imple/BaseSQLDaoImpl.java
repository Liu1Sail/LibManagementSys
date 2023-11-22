package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.BaseSQLDao;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseSQLDaoImpl implements BaseSQLDao {
    @Override
    public void executeUpdate(String SQL, Object... args) throws SQLException {
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(SQL)) {
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Object[] getOne(String SQL, Object... args) throws SQLException {
        List<Object> result= new ArrayList<>();
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
                    // 获取列数
                    int columnCount = metaData.getColumnCount();
                    for(int i = 1 ; i <= columnCount ; ++i) {
                        result.add(resultSet.getObject(i));
                    }
                }
            }
        }
        return result.toArray();
    }

    @Override
    public ArrayList<Object[]> getMany(String SQL, Object... args) throws SQLException {
        ArrayList<Object[]> result = new ArrayList<>();
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
                    // 获取列数
                    int columnCount = metaData.getColumnCount();
                    List<Object> meta= new ArrayList<>();
                    for(int i = 1 ; i <= columnCount ; ++i) {
                        meta.add(resultSet.getObject(i));
                    }
                    result.add(meta.toArray());
                }
            }
        }
        return result;
    }
}
