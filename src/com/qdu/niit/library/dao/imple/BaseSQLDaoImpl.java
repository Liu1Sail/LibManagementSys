package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.BaseSQLDao;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.*;
import java.util.ArrayList;

public class BaseSQLDaoImpl implements BaseSQLDao {
    @Override
    public int executeUpdate(String SQL, Object... args) throws SQLException {
        assert SQL !=null&&args!=null : "SQL 和args不能传入空指针";
        try(Connection connection = SqlConfig.getInstance().getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(SQL)) {
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Object[]> executeUpdateAndGetGeneratedKeys(String SQL, Object... args)  throws SQLException{
        assert SQL !=null&&args!=null : "SQL 和args不能传入空指针";
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
        assert SQL !=null&&args!=null : "SQL 和args不能传入空指针";
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
        assert SQL !=null&&args!=null : "SQL 和args不能传入空指针";
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

    @SuppressWarnings("unchecked")
    public <T> T Cast(Object obj)
    {
        if(null==obj)
            return null;
        return (T)obj;
    }




    /**
     * @param resultSet 当前行有元组的resultSet
     * @return  当前元组的各元素
     */
    private Object[] getRowAsArrayFromResultSet(ResultSet resultSet) throws SQLException {
        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; ++i) {
            row[i - 1] = resultSet.getObject(i);
        }
        return row;
    }

    /**
     * @param resultSet 任意resultSet
     * @return 查询到的各元组的列表
     */
    private ArrayList<Object[]> getRowsFromResultSet(ResultSet resultSet)throws SQLException{
        ArrayList<Object[]> keys = new ArrayList<>();
        while (resultSet.next()) {
            keys.add(getRowAsArrayFromResultSet(resultSet));
        }
        return keys;
    }
}
