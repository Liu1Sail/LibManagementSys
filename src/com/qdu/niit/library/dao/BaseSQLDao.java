package com.qdu.niit.library.dao;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BaseSQLDao {
    public int executeUpdate(final String SQL , Object... args) throws SQLException;
    public ArrayList<Object[]> executeUpdateAndGetGeneratedKeys(String SQL, Object... args)  throws SQLException;
    public Object[] getOne(final String SQL , Object... args) throws SQLException;
    public ArrayList<Object[]> getMany(final String SQL , Object... args) throws SQLException;

    public <T> T Cast(Object obj);
}
