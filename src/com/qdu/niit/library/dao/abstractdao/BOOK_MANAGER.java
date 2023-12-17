package com.qdu.niit.library.dao.abstractdao;

import com.qdu.niit.library.dao.imple.TransactionalSQLDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract public class BOOK_MANAGER extends TransactionalSQLDaoImpl {
    Connection connection = null;
    PreparedStatement statement = null;

    public BOOK_MANAGER() throws SQLException {    //connect MySQL server
        createTable();
    }

    abstract protected String getCreateTableStatement();

    //    //别忘了在子类调用setTableName
//    @Override
    public void createTable() throws SQLException {
        executeUpdate(getCreateTableStatement());
    }
}