package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.BaseSQLDao;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.*;
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
    public ArrayList<Object[]> executeUpdateAndGetGeneratedKeys(String SQL, Object... args)  throws SQLException{
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(SQL , PreparedStatement.RETURN_GENERATED_KEYS)) {
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                return getRowsFromResultSet(resultSet);
            }
        }
    }

    @Override
    public Object[] getOne(String SQL, Object... args) throws SQLException {
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next())
                    return getRowAsArrayFromResultSet(resultSet);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Object[]> getMany(String SQL, Object... args) throws SQLException {
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return getRowsFromResultSet(resultSet);
            }
        }
    }





    private Object[] getRowAsArrayFromResultSet(ResultSet resultSet) throws SQLException {
        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; ++i) {
            row[i - 1] = resultSet.getObject(i);
        }
        return row;
    }

    private ArrayList<Object[]> getRowsFromResultSet(ResultSet resultSet)throws SQLException{
        ArrayList<Object[]> keys = new ArrayList<>();
        while (resultSet.next()) {
            keys.add(getRowAsArrayFromResultSet(resultSet));
        }
        return keys;
    }
}
