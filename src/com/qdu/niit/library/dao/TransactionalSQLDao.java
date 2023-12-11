package com.qdu.niit.library.dao;

import java.sql.SQLException;
import java.util.ArrayList;
public interface TransactionalSQLDao extends BaseSQLDao{
    void beginTransaction() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void executeTransactionUpdate(final String SQL, Object... args) throws SQLException;

    ArrayList<Object[]> executeTransactionUpdateAndGetKeys(String SQL, Object... args) throws SQLException;

}
