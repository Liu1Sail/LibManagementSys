package com.qdu.niit.library.dao.imple;

import com.qdu.niit.library.dao.TransactionalSQLDao;
import com.qdu.niit.library.utils.SqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionalSQLDaoImpl extends BaseSQLDaoImpl implements TransactionalSQLDao  {
    @Override
    public void beginTransaction() throws SQLException {
        connection = SqlConfig.getInstance().getConnection();
        connection.setAutoCommit(false);
    }

    @Override
            public void commit() throws SQLException {
                try {
                    if (connection != null) {
                        connection.commit();
                    }
                } finally {
                    if(null!=connection && !connection.isClosed()){
                        connection.close();
                        connection = null;
                    }
        }
    }


    @Override
    public void rollback() throws SQLException {
        if(null != connection)
            connection.rollback();
    }

    @Override
    public void executeTransactionUpdate(String SQL, Object... args) throws SQLException {
        assert SQL !=null&&args!=null : "SQL 和args不能传入空指针";
        if(null == connection)
            throw new SQLException("链接未打开");
        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL)) {
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public ArrayList<Object[]> executeTransactionUpdateAndGetKeys(String SQL, Object... args) throws SQLException {
        assert SQL !=null&&args!=null : "SQL 和args不能传入空指针";
        if(null == connection)
            throw new SQLException("链接未打开");

        try(PreparedStatement preparedStatement =connection.prepareStatement(SQL , PreparedStatement.RETURN_GENERATED_KEYS)) {
            for(int i = 0; i < args.length ; ++i) {
                preparedStatement.setObject(i+1 , args[i]);
            }

            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
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


    private Connection connection  = null;
}
