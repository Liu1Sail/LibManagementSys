package com.qdu.niit.library.dao;

import java.sql.SQLException;
import java.util.ArrayList;
public interface TransactionalSQLDao extends AutoCloseable{
    void beginTransaction() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void executeUpdate(final String SQL, Object... args) throws SQLException;

    ArrayList<Object[]> executeUpdateAndGetGeneratedKeys(String SQL, Object... args) throws SQLException;

    Object[] getOne(final String SQL, Object... args) throws SQLException;

    ArrayList<Object[]> getMany(final String SQL, Object... args) throws SQLException;

    @Override
    void close() throws SQLException;
}
